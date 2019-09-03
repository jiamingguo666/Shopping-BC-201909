package pers.guojiaming.shopping.servlet.servletmember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @Description: 将会员商品存进数据库
 * @Author: jiaming.guo
 * @Date: 2019/8/19 11:34
 */
@WebServlet("/MemberShowImgServlet")
public class MemberShowImgServlet extends HttpServlet {
    /**
     * 设定输出的类型
     */
    private static final String GIF = "image/gif;charset=GB2312";
    private static final String JPG = "image/jpeg;charset=GB2312";
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //从memberGoodsShow.jsp获得图片地址
            String imgPath = request.getParameter("imgPath");
            //以byte流方式打开文件
            FileInputStream hFile = new FileInputStream("D:\\memberpicture\\"+imgPath);
            //得到文件大小
            int i = hFile.available();
            byte[] data = new byte[i];
            //读数据
            hFile.read(data);
            hFile.close();
            //设置返回的文件类型
            response.setContentType("image/jpeg");
            //得到客户端输出的二进制数据对象
            OutputStream toClient = response.getOutputStream();
            //输出数据
            toClient.write(data);
            toClient.flush();
            toClient.close();
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
