package pers.guojiaming.shopping.servlet.servletadmin;
import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.entity.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pers.guojiaming.shopping.util.Upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 管理员修改会员商品信息
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/UpdateMemberGoodsServlet")
public class UpdateMemberGoodsServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String blankMessage = "输入不能为空";
        request.getInputStream();
        //创建磁盘文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建核心上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 存放非文件参数列表
        List<String> plist = new ArrayList<String>();
        // 存放非文件参数列表
        List<FileItem> list = null;
        String fileName = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item : list) {
            // 如果fileitem封装的是普通输入项数据
            if (item.isFormField()) {
                String value = item.getString("UTF-8");
                //参数校验
                if(value == null || "".equals(value)){
                    request.setAttribute("blankMessage", blankMessage);
                    request.getRequestDispatcher("/jsp/admin/updateMemberGoods.jsp").forward(request, response);
                }else {
                    //将非文件参数放在plist，后面可以顺序取到
                    plist.add(value);
                }
                continue;
            } else {
                String name = plist.get(0);
                String city = plist.get(1);
                String price = plist.get(2);
                int goodsPrice = Integer.parseInt(price);
                String memberPrice = plist.get(3);
                double goodsMemberPrice = Double.parseDouble(memberPrice);
                String discountPrice = plist.get(4);
                double goodsDiscountPrice  = Double.parseDouble(discountPrice);
                String number = plist.get(5);
                int goodsNumber = Integer.parseInt(number);
                HttpSession session = request.getSession();
                String goodId = (String) session.getAttribute("memberGoodId");
                int memberGoodId = Integer.parseInt(goodId);
                //如果fileitem中封装的是上传文件,获取上传文件的文件流参数和文件参数
                InputStream stream = item.getInputStream();
                fileName = item.getName();
                if (fileName == null || "".equals(fileName.trim())) {
                    if (GoodsDao.updateGoodsExceptPicture(name,city,goodsPrice,goodsMemberPrice,goodsDiscountPrice,goodsNumber,memberGoodId)) {
                        request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/jsp/admin/updateMemberGoods.jsp").forward(request, response);
                    }
                }else{
                    //获取上传文件目录
                    String savePath = "D:\\memberpicture";
                    File path = new File(savePath);
                    //调用工具类办法
                    Upload.uploadFile(stream, path, fileName);
                    if (fileName == null || "".equals(fileName.trim())) {
                        //判空处理
                        continue;
                    }else {
                        String picture = fileName;
                        //判断是否修改成功，成功则眺转到管理员首页，不成功则眺转到修改商品页面
                        if (GoodsDao.updateGoods(name,city,goodsPrice,goodsMemberPrice,goodsDiscountPrice,goodsNumber,memberGoodId,picture)) {
                            request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/jsp/admin/updateMemberGoods.jsp").forward(request, response);
                        }
                    }
                }
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
