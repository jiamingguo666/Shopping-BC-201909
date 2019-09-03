package pers.guojiaming.shopping.servlet.servletadmin;

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
 * @Description: 管理员修改商品信息时先将商品信息展示出来
 * @Author: jiaming.guo
 * @Date: 2019/8/16 16:34
 */
@WebServlet("/updateShowGoodsServlet")
public class UpdateShowGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            String  goodsId = request.getParameter("id");
            GoodsDao goodsDao = new GoodsDao();
            Goods unitGoods = goodsDao.getGoodsById(Integer.parseInt(goodsId));
            session.setAttribute("unitGoods",unitGoods);
            session.setAttribute("goodsId",goodsId);
            request.getRequestDispatcher("/jsp/admin/updategoods.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
