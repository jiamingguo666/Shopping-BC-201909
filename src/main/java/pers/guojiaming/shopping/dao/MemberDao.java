package pers.guojiaming.shopping.dao;

import pers.guojiaming.shopping.entity.Member;
import pers.guojiaming.shopping.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
/**
 * @Description: 执行mysql的会员表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/18 11:23
 */
public class MemberDao {
    /**
     * 检查判断用户id是否在会员表里
     * @param userId 用户id
     * @return  若该用户为会员，则该会员id大于0，否则会员id为0
     */
    public static int checkIfMember(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int memberId = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from member ";
        //获取执行sql语句对象Statement
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个member的信息
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库中获取会员id
                memberId = rs.getInt("member_id");
                //判断会员id和用户id是否相等
                if (memberId == userId) {
                    System.out.println("该用户为会员");
                    return memberId;
                }
            }
            System.out.println("该用户不是会员");
            memberId = 0 ;
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
        return memberId;
    }

    /**
     *  将注册会员界面信息插入数据库
     * @param userId 用户id
     * @param memberName 会员名
     * @param memberPhone 会员联系方式
     * @param memberPrice 会员费
     * @param startDate 会员注册时间
     * @param deadline 会员到期时间
     */
    public static void registeMember(int userId, String memberName, String memberPhone, int memberPrice, Timestamp startDate,Timestamp deadline) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "insert into member (member_id,member_name,member_phone,member_price,start_date,deadline) values (?,?,?,?,?,?)";
        //获取执行sql语句对象Statement
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, memberName);
            stmt.setString(3, memberPhone);
            stmt.setInt(4, memberPrice);
            stmt.setTimestamp(5,startDate);
            stmt.setTimestamp(6,deadline);
            int count = stmt.executeUpdate();
            if (count > 0) {
                System.out.println("数据添加成功");
            } else {
                System.out.println("数据添加失败");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

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
     * 判断会员费是否小于用户的money
     * @param userId  用户id
     * @param memberPrice 会员费
     * @return 若用户的G币小于会员费，则无法注册会员，否则可以注册
     */
    public static double moneyCompareMemberPrice(int userId,int memberPrice){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double money = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "select * from user where id = "+userId;
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()){
                money = rs.getDouble("money");
                money = money - memberPrice;
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
        return money;
    }

    /**
     * 更新用户剩余的G币
     * @param money G币
     * @param userId 用户id
     * @return 判断是否更新修改成功
     */
    public static boolean updateUserMoney(double money,int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update user set money='"+money+"'where id ='"+userId+"'";
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
     * 查看一个会员的信息
     * @param id 会员id
     * @return 该会员的信息
     */
    public Member selectOneMemberInfo(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Member member = null;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "select * from member where member_id = "+id;
        try {
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while(rs.next()){
                //从数据库中获取每个用户的信息存在user中
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setMemberName(rs.getString("member_name"));
                member.setMemberPhone(rs.getString("member_phone"));
                member.setMemberPrice(rs.getInt("member_price"));
                member.setStartDate(rs.getTimestamp("start_date"));
                member.setDeadline(rs.getTimestamp("deadline"));
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
        return member;
    }

    /**
     *  获取数据库内所有的会员用户资料
     * @return  所有会员用户的资料列表
     */

    public ArrayList<Member> getAllMembers(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //商品集合
        ArrayList<Member> list = new ArrayList<Member>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from member";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Member member =new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setMemberName(rs.getString("member_name"));
                member.setMemberPhone(rs.getString("member_phone"));
                member.setMemberPrice(rs.getInt("member_price"));
                member.setStartDate(rs.getTimestamp("start_date"));
                member.setDeadline(rs.getTimestamp("deadline"));
                //加入集合
                list.add(member);
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
     * 通过会员的id删除会员
     * @param memberId  会员id
     * @return 判断是否删除成功
     */
    public static boolean deleteByMemberId(int memberId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from member where member_id = '"+memberId+" '";
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
     *  修改用户信息
     * @param member  要修改的用户信息
     * @return 判断是否修改成功
     */
    public static boolean updateMember(Member member){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update member set start_date='"+member.getStartDate()+"',deadline='"+member.getDeadline()+"'where member_id ='"+member.getMemberId()+"'";
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