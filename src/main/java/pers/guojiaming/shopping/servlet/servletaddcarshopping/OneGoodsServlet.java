package pers.guojiaming.shopping.servlet.servletaddcarshopping;

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
 * @Description: 每个商品的资料
 * @Author: jiaming.guo
 * @Date: 2019/8/19 17:34
 */
@WebServlet("/oneGoodsServlet")
public class OneGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //从商品详情界面获得商品的id
        String  id = request.getParameter("id");
        GoodsDao goodsDao = new GoodsDao();
        //调用getGoodsById()方法获取该商品的信息，存在session里
        Goods goods = goodsDao.getGoodsById(Integer.parseInt(id));
        HttpSession session = request.getSession();
        session.setAttribute("goods",goods);
        request.getRequestDispatcher("/jsp/carshopping/addCarShopping.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
