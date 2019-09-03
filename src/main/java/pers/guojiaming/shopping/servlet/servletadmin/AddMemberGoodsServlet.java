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
 * @Description: 管理员发布会员商品
 * @Author: jiaming.guo
 * @Date: 2019/8/5 16:34
 */
@WebServlet("/AddMemberGoodsServlet")
public class AddMemberGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)) {
            request.getRequestDispatcher("/AdminFirstLogin").forward(request, response);
        }else{
            int adminId = (Integer) id;
            String message = "输入不能为空";
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
            //表单共有8项，必须全填，否则返回提示
            int listNumber = 8;
            if(list.size() != listNumber){
                request.setAttribute("message",message);
                request.getRequestDispatcher("/jsp/admin/addMemerGoods.jsp").forward(request,response);

            }else {
                for (FileItem item : list) {
                    // 如果fileitem封装的是普通输入项数据
                    if (item.isFormField()) {
                        String value = item.getString("UTF-8");
                        //参数校验
                        if (value == null || "".equals(value)) {
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("/jsp/admin/addMemerGoods.jsp").forward(request, response);
                        } else {
                            //将非文件参数放在plist，后面可以顺序取到
                            plist.add(value);
                        }
                        continue;
                    } else {
                        //如果fileitem中封装的是上传文件,获取上传文件的文件流参数和文件参数
                        InputStream stream = item.getInputStream();
                        fileName = item.getName();
                        //参数校验
                        if (fileName == null || "".equals(fileName.trim())) {
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("/jsp/admin/addMemerGoods.jsp").forward(request, response);
                        } else {
                            //获取上传文件目录
                            String savePath = "D:\\memberpicture";
                            File path = new File(savePath);
                            //调用工具类办法
                            Upload.uploadFile(stream, path, fileName);
                            if (fileName == null || "".equals(fileName.trim())) {
                                //判空处理
                                continue;
                            }else {
                                //开始顺序获取非文件参数
                                String name = plist.get(0);
                                String city = plist.get(1);
                                String price = plist.get(2);
                                String number = plist.get(3);
                                String category = plist.get(4);
                                String memberPrice = plist.get(5);
                                String discountPrice = plist.get(6);
                                String picture = fileName;
                                Goods goods = new Goods();
                                // 将获得的商品信息存到对象goods中
                                goods.setName(name);
                                goods.setCity(city);
                                goods.setPrice(Integer.parseInt(price));
                                goods.setNumber(Integer.parseInt(number));
                                goods.setCategory(category);
                                goods.setMemberPrice(Double.parseDouble(memberPrice));
                                goods.setDiscountPrice(Double.parseDouble(discountPrice));
                                goods.setPicture(picture);
                                //判断是否添加成功，成功则眺转到会员商品展示页面，不成功则眺转到添加会员商品页面
                                if (GoodsDao.addGoods(goods)) {
                                    request.getRequestDispatcher("/jsp/admin/memberGoodsPage.jsp").forward(request, response);
                                } else {
                                    request.getRequestDispatcher("/jsp/admin/addMemberGoods.jsp").forward(request, response);
                                }
                            }
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
