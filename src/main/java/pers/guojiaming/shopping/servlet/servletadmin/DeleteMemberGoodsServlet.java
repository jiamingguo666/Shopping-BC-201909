package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.dao.AddShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员删除会员商品
 * @Author: jiaming.guo
 * @Date: 2019/8/11 16:34
 */
@WebServlet("/DeleteMemberGoodsServlet")
public class DeleteMemberGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            //获取会员商品界面获得的商品id信息
            String  buyGoodsId = request.getParameter("buyGoodsId");
            //GoodsDao层向数据库删除数据,判断是否删除成功，成功则眺转到商品展示页面，不成功则眺转到删除商品页面
            if(GoodsDao.deleteById(Integer.parseInt(buyGoodsId))){
                //删除会员商品成功后，判断购物车是否有商品，有则删除
                int deleteMemberGoodsId = Integer.parseInt(buyGoodsId);
                int memberBuyGoodsId = AddShoppingDao.checkIfDeleteGoodsExistCarShopping(deleteMemberGoodsId);
                if(memberBuyGoodsId != 0){
                    if (AddShoppingDao.deleteCarShooppingGoodsByBuyGoodsId(memberBuyGoodsId)){
                        request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request,response);
                    }
                }else {
                    request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request,response);
                }
            }
        }
    }

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
