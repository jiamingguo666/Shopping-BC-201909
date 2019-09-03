package pers.guojiaming.shopping.servlet.servletmember;

import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 会员商品资料
 * @Author: jiaming.guo
 * @Date: 2019/8/20 9:34
 */
@WebServlet("/memberOneGoodsServlet")
public class MemberOneGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //从商品详情界面获得商品的id
        String  id = request.getParameter("id");
        GoodsDao goodsDao = new GoodsDao();
        //调用getGoodsById()方法获取该商品的信息，存在sessio里
        Goods goods = goodsDao.getGoodsById(Integer.parseInt(id));
        HttpSession session = request.getSession();
        session.setAttribute("goods",goods);
        request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request,response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
