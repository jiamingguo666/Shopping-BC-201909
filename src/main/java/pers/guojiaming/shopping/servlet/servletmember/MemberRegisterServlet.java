package pers.guojiaming.shopping.servlet.servletmember;

import pers.guojiaming.shopping.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @Description: 获取会员注册资料。并存进数据库
 * @Author: jiaming.guo
 * @Date: 2019/8/20 16:34
 */
@WebServlet("/MemberRegisterServlet")
public class MemberRegisterServlet extends HttpServlet {
    public static final int THREE_HUNDRED = 300;
    public static final int FIVE_HUNDRED = 500;
    public static final int NINE_HUNDRED = 900;
    public static final int ADD_THREE_MONTH = 3;
    public static final int ADD_SIX_MONTH = 6;
    public static final int ADD_ONE_YEAR = 1;
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        String errorNameMessage = "名字不能为空";
        String errorPhoneMessage = "号码不能为空";
        String errorPriceMessage = "请选择会员类别";
        String notEnoughMemberPrice = "G币不足,快用积分兑换吧！";
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        int userId = (Integer) id;
        //从会员注册界面获得参数，并进行校验
        String memberName = request.getParameter("memberName");
        String memberPhone = request.getParameter("memberPhone");
        String memberPrice1 = request.getParameter("memberPrice");
        double money = 0;
        if(memberName == null || "".equals(memberName)){
            request.setAttribute("errorNameMessage",errorNameMessage);
            request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
        }
        if(memberPhone == null || "".equals(memberPhone)){
            request.setAttribute("errorPhoneMessage",errorPhoneMessage);
            request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
        }
        if(memberPrice1 == null || "".equals(memberPrice1) ){
            session.setAttribute("money",money);
            request.setAttribute("errorPriceMessage",errorPriceMessage);
            request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
        }else{
            int memberPrice = Integer.parseInt(memberPrice1);
            //判断余额是否足够支付会员费
            money = MemberDao.moneyCompareMemberPrice(userId,memberPrice);
            if(money < 0){
                session.setAttribute("money",money);
                request.setAttribute("notEnoughMemberPrice",notEnoughMemberPrice);
                request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
            }else {
                //先扣除会员费，再将数据插入数据库
                if(MemberDao.updateUserMoney(money,userId)){
                    //获取会员注册时间
                    Date date = new Date();
                    String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    Timestamp startDate = Timestamp.valueOf(nowTime);
                    //获取会员到期日期，在注册时间基础上加上会员时长
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    if(memberPrice == THREE_HUNDRED){
                        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+ ADD_THREE_MONTH);
                    }else if(memberPrice == FIVE_HUNDRED){
                        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+ ADD_SIX_MONTH);
                    }else if(memberPrice == NINE_HUNDRED){
                        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+ ADD_ONE_YEAR);
                    }
                    Date lastDate =  calendar.getTime();
                    String deadline1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastDate);
                    Timestamp deadline = Timestamp.valueOf(deadline1);
                    //注册会员信息
                    MemberDao.registeMember(userId, memberName, memberPhone, memberPrice,startDate,deadline);
                    request.getRequestDispatcher("/jsp/my.jsp").forward(request,response);
                }

            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
