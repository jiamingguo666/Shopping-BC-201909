package pers.guojiaming.shopping.dao;
import pers.guojiaming.shopping.entity.CheckLogin;
import pers.guojiaming.shopping.entity.UserVo;
import pers.guojiaming.shopping.util.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @Description: 执行mysql的用户表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/2 10:34
 */
public class UserDao {
    /**
     *  检查登录，判断输入的密码和用户名是否匹配 ，username,password从login.jsp传过来的
     * @param userName 用户名
     * @param password 用户名
     * @return
     */
    public static CheckLogin checkLogin(String userName, String password) {
        CheckLogin resp = new CheckLogin();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from user where username = ?";
        try {
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给username 赋值
            stmt.setString(1, userName);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库中获取用户名name及其对应的密码pwd
                String name = rs.getString("username");
                String pwd = rs.getString("password");
                //判断name和pwd是否和login. jsp传过来的username和password对应
                if (name.equals(userName)) {
                    if (pwd.equals(password)) {
                        //验证成功
                        resp.setResult(true);
                        return resp;
                    } else {
                        //验证失败,flag为标志，若为1，则是密码和用户名不匹配
                        resp.setFlag(1);
                        resp.setResult(false);
                        return resp;
                    }
                }
            }
            //验证失败,flag为标志，若为2，则是未注册
            resp.setFlag(2);
            resp.setResult(false);
            return resp;
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
        resp.setResult(false);
        return resp;
    }

    /**
     *  在数据库注册新用户
     * @param userName 用户名
     * @param password 用户密码
     * @param province 用户省份
     * @param city 用户城市
     * @param sex 用户性别
     * @param age 用户年龄
     * @param headPicture 用户头像
     */
    public static void registe(String userName, String password, String province, String city, String sex, String age, String headPicture) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //在表中查找用户名
        String sql = "select username from user";
        try {
            //获取数据库连接对象
            conn = JdbcUtils.getConnection();
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()){
                //从数据库中获取用户名name
                String name = rs.getString("username");
               if(name.equals(userName)){
                   //用户名已存在，标志为3
                   flag = 3;
               }
            }
            //用户名不存在,插入语句，并返回count，若count>0,则插入成功，否则插入失败
            int three = 3;
            if(flag != three) {
                String sqlnameandpassword = "insert into user (username,password,province,city,sex,age,head_picture) values (?,?,?,?,?,?,?)";
                //获取数据库连接对象
                conn = JdbcUtils.getConnection();
                //获取执行sql语句对象Statement
                stmt = conn.prepareStatement(sqlnameandpassword);
                //给username，password赋值
                stmt.setString(1, userName);
                stmt.setString(2, password);
                stmt.setString(3, province);
                stmt.setString(4, city);
                stmt.setString(5, sex);
                stmt.setString(6, age);
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
     *  查看一个用户的信息
     * @param id 用户id
     * @return 该用户的信息
     */

   public UserVo selectOneUserInfo(int id){
       Connection conn = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
        UserVo user = null;
       //获取数据库连接对象
       conn = JdbcUtils.getConnection();
       String sql = "select * from user where id = "+id;
       try {
           //获取执行sql语句对象Statement
           stmt = conn.prepareStatement(sql);
           //执行查询语句，得到一个user的信息
           rs = stmt.executeQuery();
           while(rs.next()){
               //从数据库中获取每个用户的信息存在user中
               user = new UserVo();
               user.setUsername(rs.getString("username"));
               user.setPassword(rs.getString("password"));
               user.setProvince(rs.getString("province"));
               user.setCity(rs.getString("city"));
               user.setSex(rs.getString("sex"));
               user.setAge(rs.getInt("age"));
               user.setId(rs.getInt("id"));
               user.setHeadPicture(rs.getString("head_picture"));
               user.setMoney(rs.getDouble("money"));
               user.setIntegral(rs.getDouble("integral"));
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
           return user;
   }

    /**
     *  获取数据库内所有的用户资料
     * @return 所有用户的资料列表
     */
   public ArrayList<UserVo> getAllUsers(){
       Connection conn = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       //用户信息集合
       ArrayList<UserVo> list = new ArrayList<UserVo>();
       conn = JdbcUtils.getConnection();
       String sql = "select * from user";
       try {
           stmt = conn.prepareStatement(sql);
           rs = stmt.executeQuery();
           while(rs.next()){
               UserVo user =new UserVo();
               user.setId(rs.getInt("id"));
               user.setUsername(rs.getString("username"));
               user.setPassword(rs.getString("password"));
               user.setProvince(rs.getString("province"));
               user.setCity(rs.getString("city"));
               user.setSex(rs.getString("sex"));
               user.setAge(rs.getInt("age"));
               user.setHeadPicture(rs.getString("head_picture"));
               user.setMoney(rs.getDouble("money"));
               user.setIntegral(rs.getDouble("integral"));
               //加入集合
               list.add(user);
           }
           return list;
       } catch (SQLException e) {
           e.printStackTrace();
           return  null;
       }finally {
           //释放资源
           if(rs != null){
               try {
                   rs.close();
                   rs = null;
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
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

   }

    /**
     * 通过用户的id删除用户
      * @param id  用户id
     * @return 判断是否删除成功
     */
    public static boolean deleteById(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from user where id = '"+id+" '";
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
     *  更新用户信息 包含头像
     * @param userName 用户名
     * @param password 用户密码
     * @param province  用户省份
     * @param city  用户城市
     * @param sex 用户性别
     * @param age 用户年龄
     * @param userId 用户id
     * @param headPicture 用户城市
     * @return
     */
    public static boolean updateUsers(String userName,String password,String province,String city,String sex,int age,int userId,String headPicture){
        //将数据库中图片路径的\转换为/
        headPicture=headPicture.replace("\\","/");
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set username='"+userName+"',password='"+password+"',province='"+province+"',city='"+city+"',sex='"+sex+"',age='"+age+"',head_picture='"+headPicture+"'where id ='"+userId+"'";
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
     * 管理员修改用户信息
     * @param user 单个用户信息
     * @return 判断是否修改成功
     */
    public static boolean adminUpdateUsers(UserVo user){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set username='"+user.getUsername()+"',password='"+user.getPassword()+"',province='"+user.getProvince()+"',city='"+user.getCity()+"',sex='"+user.getSex()+"',age='"+user.getAge()+"',money ='"+user.getMoney()+"'where id ='"+user.getId()+"'";
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
     *  更新用户个人资料 不含头像
     * @param userName 用户名
     * @param password 用户密码
     * @param province 用户省份
     * @param city 用户城市
     * @param sex 用户性别
     * @param age 用户年龄
     * @param userId 用户id
     * @return 判断是否修改成功
     */
    public static boolean updateUsersExceptPicture(String userName,String password,String province,String city,String sex,int age,int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set username='"+userName+"',password='"+password+"',province='"+province+"',city='"+city+"',sex='"+sex+"',age='"+age+"'where id ='"+userId+"'";
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
     * 通过用户名获取用户id
     * @param userName 用户名
     * @return  返回用户id
     */
    public static int getUserIdByUserName(String userName){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from user where username = ?";
        try {
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给username 赋值
            stmt.setString(1, userName);
            //执行查询语句，得到user的id
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
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
        return id;
    }

    /**
     * 判断获取的用户id是否存在
     * @param id  获取的用户id
     * @return  若用户id存在，则返回用户id，否则返回0
     */
    public static int checkIfIdExist(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int userId = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from user";
        //获取执行sql语句对象Statement
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个member的信息
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库中获取会员id
                userId = rs.getInt("id");
                //判断会员id和用户id是否相等
                if (userId == id) {
                    System.out.println("该id存在");
                    return userId;
                }
            }
            System.out.println("该用户id不存在");
            userId = 0 ;
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
        return userId;
    }
}
