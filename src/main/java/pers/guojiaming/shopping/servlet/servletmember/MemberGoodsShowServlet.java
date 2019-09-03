package pers.guojiaming.shopping.servlet.servletmember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Description: 会员商品展示
 * @Author: jiaming.guo
 * @Date: 2019/8/23 16:34
 */
@WebServlet("/MemberGoodsShow")
public class MemberGoodsShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //设置编码格式
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/jsp/member/memberGoodsShow.jsp").forward(request,response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
