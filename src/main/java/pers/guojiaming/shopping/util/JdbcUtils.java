package pers.guojiaming.shopping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @Description: 该类用于建立与数据库的连接，为得到Connection服务
 * @Author: jiaming.guo
 * @Date: 2019/8/1 9:34
 */
public class JdbcUtils {
     static Connection conn;
    /**
     *  获取数据库连接对象
     * @return 数据库连接对象
     */
    public static Connection getConnection(){
        org.apache.commons.fileupload.FileUploadException fileUploadException;
         String driver = "com.mysql.cj.jdbc.Driver";
        //数据库存放地址，用户名及密码
         String url = "jdbc:mysql:///userinformation?serverTimezone=GMT%2B8";
         String username = "root";
         String password = "0801";
        try {
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            try {
                //建立连接数据库
                conn = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return conn;
    }
}
