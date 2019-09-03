package pers.guojiaming.shopping.dao;
import pers.guojiaming.shopping.entity.CheckAdminLogin;
import pers.guojiaming.shopping.entity.Admin;
import pers.guojiaming.shopping.util.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @Description: 执行mysql的管理员表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
public class AdminDao {
    /**
     * 检查登录，判断输入的密码和用户名是否匹配 ,username,password从login.jsp传过来的
     * @param adminName 管理员名字
     * @param adminPassword  管理员密码
     * @return 如果相匹配，则返回管理员的id
     */
    public static CheckAdminLogin checkLogin(String adminName, String adminPassword) {
        CheckAdminLogin checkAdminLoginDTO = new CheckAdminLogin();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from admin where admin_name = ?";
        try {
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给member_name 赋值
            stmt.setString(1, adminName);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库中获取用户名name及其对应的密码pwd
                String name = rs.getString("admin_name");
                String pwd = rs.getString("admin_password");
                //判断name和pwd是否和login. jsp传过来的username和password对应
                if (name.equals(adminName)) {
                    if (pwd.equals(adminPassword)) {
                        //验证成功
                         int adminId = rs.getInt("admin_id");
                         checkAdminLoginDTO.setAdminId(adminId);
                        return checkAdminLoginDTO;
                    } else {
                        //验证失败,flag为标志，若为1，则是密码和用户名不匹配
                        checkAdminLoginDTO.setAdminId(0);
                        checkAdminLoginDTO.setAdminFlag(1);
                        return checkAdminLoginDTO;
                    }

                }
            }
            //验证失败,flag为标志，若为2，则是未注册
            checkAdminLoginDTO.setAdminId(0);
            checkAdminLoginDTO.setAdminFlag(2);
            return checkAdminLoginDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        checkAdminLoginDTO.setAdminId(0);
        return checkAdminLoginDTO;
    }

    /**
     * 在数据库注册新管理员
     * @param adminName 管理员名字
     * @param adminPassword  管理员密码
     * @param adminProvince 管理员省份
     * @param adminCity  管理员城市
     * @param adminSex   管理员性别
     * @param adminAge   管理员年龄
     * @param headPicture  管理员的头像
     */
    public static void registe(String adminName, String adminPassword,String adminProvince,String adminCity,String adminSex,String adminAge,String headPicture) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int adminFlag = 0;
        //在表中查找用户名
        String sql = "select admin_name from admin";
        try {
            //获取数据库连接对象
            conn = JdbcUtils.getConnection();
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()){
                //从数据库中获取用户名name
                String name = rs.getString("admin_name");
                if(name.equals(adminName)){
                    //用户名已存在，标志为3
                    adminFlag = 3;
                }
            }
            //用户名不存在,插入语句，并返回count，若count>0,则插入成功，否则插入失败
            int three = 3;
            if(adminFlag != three) {
                String sqlnameandpassword = "insert into admin(admin_name,admin_password,admin_province,admin_city,admin_sex,admin_age,head_picture) values (?,?,?,?,?,?,?)";
                //获取数据库连接对象
                conn = JdbcUtils.getConnection();
                //获取执行sql语句对象Statement
                stmt = conn.prepareStatement(sqlnameandpassword);
                //给admin_name，admin_password赋值
                stmt.setString(1, adminName);
                stmt.setString(2, adminPassword);
                stmt.setString(3, adminProvince);
                stmt.setString(4, adminCity);
                stmt.setString(5, adminSex);
                stmt.setString(6, adminAge);
                stmt.setString(7,headPicture);

                int count = stmt.executeUpdate();
                if (count > 0) {
                    System.out.println("数据添加成功");
                } else {
                    System.out.println("数据添加失败");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查看一个卖家用户的信息
     * @param adminId  管理员id
     * @return  每个管理员的信息
     */

    public Admin selectOneSellerInfo(int adminId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "select * from admin where admin_id = "+adminId;
        try {
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while(rs.next()){
                //从数据库中获取每个用户的信息存在admin中
                admin = new Admin();
                admin.setAdminName(rs.getString("admin_name"));
                admin.setAdminPassword(rs.getInt("admin_password"));
                admin.setAdminProvince(rs.getString("admin_province"));
                admin.setAdminCity(rs.getString("admin_city"));
                admin.setAdminSex(rs.getString("admin_sex"));
                admin.setAdminAge(rs.getInt("admin_age"));
                admin.setHeadPicture(rs.getString("head_picture"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return admin;
    }

    /**
     * 管理员更新个人资料包含头像
     * @param adminName  管理员名字
     * @param adminPassword 管理员密码
     * @param adminProvince 管理员省份
     * @param adminCity  管理员城市
     * @param adminSex 管理员性别
     * @param adminAge  管理员年龄
     * @param adminId  管理员id
     * @param headPicture  管理员头像
     * @return
     */
    public static boolean updateSellers(String adminName,String adminPassword,String adminProvince,String adminCity,String adminSex,int adminAge,int adminId,String headPicture){
        //将数据库中图片路径的\转换为/
        headPicture=headPicture.replace("\\","/");
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update admin set admin_name='"+adminName+"',admin_password='"+adminPassword+"',admin_province='"+adminProvince+"',admin_city='"+adminCity+"',admin_sex='"+adminSex+"',admin_age='"+adminAge+"',head_picture='"+headPicture+"'where admin_id ='"+adminId+"'";
        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //若修改成功，返回true，否则返回false
        if (count > 0) {
            f = true;
        }
        return f;
    }

    /**
     *  通过管理员的id删除账号
     * @param adminId  管理员id
     * @return  判断是否删除成功
     */
    public static boolean deleteByAdminId(int adminId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from admin where  admin_id = '"+adminId+" '";
        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //若删除成功，返回true，否则返回false
        if (count > 0) {
            f = true;
        }
        return f;
    }

    /**
     * 更新管理员个人资料
     * @param adminName  管理员名字
     * @param adminPassword  管理员密码
     * @param adminProvince  管理员省份
     * @param adminCity 管理员城市
     * @param adminSex  管理员性别
     * @param adminAge  管理员年龄
     * @param adminId  管理员id
     * @return  判断是否更新成功
     */
    public static boolean updateSellersExceptPicture(String adminName,String adminPassword,String adminProvince,String adminCity,String adminSex,int adminAge,int adminId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update admin set admin_name='"+adminName+"',admin_password='"+adminPassword+"',admin_province='"+adminProvince+"',admin_city='"+adminCity+"',admin_sex='"+adminSex+"',admin_age='"+adminAge+"'where admin_id ='"+adminId+"'";
        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //若修改成功，返回true，否则返回false
        if (count > 0) {
            f = true;
        }
        return f;
    }
}
