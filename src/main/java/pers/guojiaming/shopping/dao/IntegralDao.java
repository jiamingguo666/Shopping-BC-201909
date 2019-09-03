package pers.guojiaming.shopping.dao;

import pers.guojiaming.shopping.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @Description: 执行mysql的积分表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/21 11:23
 */
public class IntegralDao {
    /**
     * 更新user表用户的积分值
     * @param userIntegral 用户的积分
     * @param userId 用户的id
     * @return 判断是否修改成功
     */
    public static double updateUserIntegral(double userIntegral,int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set integral= '"+userIntegral+"'where id ='"+userId+"'";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
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
        return userIntegral;
    }

    /**
     * 判断积分值是否足够兑换
     * @param userId  用户id
     * @param integralChangeMoney   积分兑换G币
     * @return 积分值减去要兑换成G币的积分，若大于0，则能兑换，否则积分不足，兑换不了
     */
    public static double integralCompareIntegralChangeMoney(int userId,double integralChangeMoney){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double integral = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "select * from user where id = "+userId;
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()){
                integral = rs.getDouble("integral");
                integral = integral - integralChangeMoney;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
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

        return integral;
    }

    /**
     * 更新用户剩余的积分
     * @param surplusIntegral 兑换G币后剩余的积分
     * @param userId 用户id
     * @return 判断是否=更新数据表成功
     */
    public static boolean updateUserSurplusIntegral(double surplusIntegral,int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set integral='"+surplusIntegral+"'where id ='"+userId+"'";
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
     * 更新user表用户的money
     * @param integralChangeMoney 积分兑换G币后，G币要增加的值
     * @param userId 用户id
     */
    public static void updateUserMoney(double integralChangeMoney,int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set money = money+'"+integralChangeMoney+"'where id ='"+userId+"'";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
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
    }
}
