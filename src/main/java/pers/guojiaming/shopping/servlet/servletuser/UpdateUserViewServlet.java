package pers.guojiaming.shopping.servlet.servletuser;
import pers.guojiaming.shopping.dao.UserDao;
import pers.guojiaming.shopping.entity.UserVo;
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
 * @Description: 用户修改自己的个人资料
 * @Author: jiaming.guo
 * @Date: 2019/8/3 16:34
 */
@WebServlet("/updateUserViewServlet")
public class UpdateUserViewServlet extends HttpServlet {
  @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String message = "输入不能为空";
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute("id");
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
                    request.setAttribute("message",message);
                    request.getRequestDispatcher("/jsp/updateUserView.jsp").forward(request,response);
                }else {
                    //将非文件参数放在plist，后面可以顺序取到
                    plist.add(value);
                }
                continue;
            } else {
                String userName = plist.get(0);
                String password = plist.get(1);
                String province = plist.get(2);
                String city = plist.get(3);
                String sex = plist.get(4);
                String age = plist.get(5);
                int userAge = Integer.parseInt(age);
                //如果fileitem中封装的是上传文件,获取上传文件的文件流参数和文件参数
                InputStream stream = item.getInputStream();
                fileName = item.getName();
                if (fileName == null || "".equals(fileName.trim())) {
                    //判断是否修改成功，成功则眺转到所有用户页面，不成功则眺转到修改用户页面
                    if (UserDao.updateUsersExceptPicture(userName,password,province,city,sex,userAge,id)) {
                        request.getRequestDispatcher("/jsp/userview.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/jsp/updateUserView.jsp").forward(request, response);
                    }
                }else{
                    //头像也修改，则更新全部数据,获取上传文件目录
                    String savePath = "D:\\headpicture";
                    File path = new File(savePath);
                    //调用工具类办法
                    Upload.uploadFile(stream, path, fileName);
                    if (fileName == null || "".equals(fileName.trim())) {
                        //判空处理
                        continue;
                    }else {
                        String headPicture = fileName;
                        //判断是否修改成功，成功则眺转到所有用户页面，不成功则眺转到修改用户页面
                        if (UserDao.updateUsers(userName,password,province,city,sex,userAge,id,headPicture)) {
                            request.getRequestDispatcher("/jsp/userview.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/jsp/updateUserView.jsp").forward(request, response);
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
