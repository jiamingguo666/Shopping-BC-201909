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
 * @Description: 管理员修改会员商品时先将会员商品资料展示出来
 * @Author: jiaming.guo
 * @Date: 2019/8/16 16:34
 */
@WebServlet("/UpdateShowMemberGoodsServlet")
public class UpdateShowMemberGoodsServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            String updateFailMessage = "无法修改";
            String  memberGoodId = request.getParameter("id");
            if(memberGoodId == null || "".equals(memberGoodId)){
                request.setAttribute("updateFailMessage",updateFailMessage);
                request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request,response);
            }else{
                GoodsDao goodsDao = new GoodsDao();
                Goods unitMembergoods = goodsDao.getGoodsById(Integer.parseInt(memberGoodId));
                session.setAttribute("unitMembergoods",unitMembergoods);
                session.setAttribute("memberGoodId",memberGoodId);
                request.getRequestDispatcher("/jsp/admin/updateMemberGoods.jsp").forward(request,response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
