package pers.guojiaming.shopping.dao;

import pers.guojiaming.shopping.entity.CarShopping;
import pers.guojiaming.shopping.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 执行mysql的购物车表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class AddShoppingDao {
    /**
     * 将购物车信息加入到数据表carShopping
     * @param userId 用户id
     * @param goodsName 商品名
     * @param buygoodsNumber 购买数量
     * @param buyPrice   购买价格
     * @param goodsCity   商品出产地
     * @param buyGoodsId    所买商品的id
     */
    public static void addCarShopping(int userId,String goodsName,int buygoodsNumber,double buyPrice,String goodsCity,int buyGoodsId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int count;
        try {
            conn = JdbcUtils.getConnection();
            //向数据库插入信息
            String sql = " insert into carShopping(user_id,goods_name,buy_number,goods_price,goods_city,buygoods_id) values(?,?,?,?,?,?)";
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给user_id,goods_name 等赋值
            stmt.setInt(1, userId);

            stmt.setString(2, goodsName);
            stmt.setInt(3, buygoodsNumber);
            stmt.setDouble(4, buyPrice);
            stmt.setString(5, goodsCity);
            stmt.setInt(6,buyGoodsId);
            count = stmt.executeUpdate();
            if (count > 0) {
                System.out.println("数据添加成功");
            } else {
                System.out.println("数据添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
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
     * 若购物车已有所购商品，则更新购买数量和价格
     * @param carShopping 购物车类对象
     */
    public static void updateNumberAndPrice(CarShopping carShopping) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int updateCount = 0;
            try {
                conn = JdbcUtils.getConnection();
                //向数据库更新信息
                String sql = "update carshopping set buy_number='"+carShopping.getBuyNumber()+"'where buygoods_id = '"+carShopping.getBuygoodsId()+"' and user_id = '"+carShopping.getUserId()+"'";
                stmt = conn.prepareStatement(sql);
                updateCount = stmt.executeUpdate(sql);
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
            if (updateCount > 0) {
                System.out.println("更新成功！");
            }
    }

    /**
     * 根据用户编号获取其购买商品信息
     * @param userId  用户id
     * @return  用户购买商品的信息列表
     */
    public static ArrayList<CarShopping> getBuyGoodsByUserId(int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //用户所买商品集合
        ArrayList<CarShopping> list = new ArrayList<CarShopping>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from carShopping where user_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                CarShopping goods =new CarShopping();
                goods.setUserId(rs.getInt("user_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setBuyNumber(rs.getInt("buy_number"));
                goods.setGoodsPrice(rs.getDouble("goods_price"));
                goods.setGoodsCity(rs.getString("goods_city"));
                goods.setGoodsId(rs.getInt("goods_id"));
                goods.setBuygoodsId(rs.getInt("buygoods_id"));
                //加入集合
                list.add(goods);
            }
            return list;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
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
     * 根据用户的id获取其
     * @param userId  用户id
     * @return 购买商品的所有商品id列表
     */
    public static  ArrayList<Integer> getBuyGoodsIdByUserId(int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //用户所买商品集合
        ArrayList<Integer>goodsIdList = new ArrayList<Integer>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from carShopping where user_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){

                int buyGoodsId = rs.getInt("buygoods_id");
                System.out.println(buyGoodsId);
                //加入集合
                goodsIdList.add(buyGoodsId);

            }
            return goodsIdList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
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
     * 通过商品的id删除购物车的商品
     * @param goodsId  购物车的商品id
     * @return   判断是否删除成功
     */
    public static boolean deleteCarShooppingGoodsByGoodsId(int goodsId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from carshopping where goods_id = '"+goodsId+" '";
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
     * 通过用户的id删除其加入购物车的商品
     * @param userId  用户id
     */
    public static void deleteCarShooppingGoodsByUserId(int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from carshopping where user_id = '"+userId+" '";
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
           System.out.println("删除成功！");
        }

    }

    /**
     *  判断我的购物车页面的商品数量和carShopping是否一样
     * @param carShopping1 购物车类对象
     * @return 判断我的购物车页面的商品数量和carShopping是否一样标志
     */
    public static boolean goodsNumberAndBuyNumber(CarShopping carShopping1){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //判断数量是否相同
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //查找数据库信息
        String sql = "select * from carShopping where user_id ='"+carShopping1.getUserId()+"'and goods_id='"+carShopping1.getGoodsId()+"'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                int buyNumber = rs.getInt("buy_number");
                if(buyNumber == carShopping1.getBuyNumber()){
                    return f= true;
                }
            }
             f =false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return f;
    }

    /**
     *  根据用户id修改该用户加入购物车的商品数量
     * @param userId  用户id
     * @param saveGoodId  被修改商品的商品id
     * @param goodsNumber  修改后的商品数量
     */
    public static void updateCarShoppingNumber(int userId,int saveGoodId,int goodsNumber){
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update carShopping set buy_number='"+goodsNumber+"'where user_id ='"+userId+"'and buygoods_id = '"+saveGoodId+"'";
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
            System.out.println("修改成功");
        }
        else{
            System.out.println("修改失败");
        }
    }

    /**
     * 计算每个买家购买商品的总价
     * @param userId 用户id
     * @return 每个买家购买商品的总价
     */
    public static double sumPriceByUserId(int userId){
        double sumPrice = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        String sql = "select * from carShopping where user_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                int sumNumber = rs.getInt("buy_number");
                double nuitPrice = rs.getDouble("goods_price");
                sumPrice += sumNumber*nuitPrice;
            }
        } catch (SQLException e){
            e.printStackTrace();
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
        return sumPrice;
    }

    /**
     * 根据用户编号获取其购买商品信息
     * @param userId 用户id
     * @return 用户购买商品信息列表
     */

    public static List<CarShopping> getBuyGoodsInformationByUserId(int userId){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //用户所买商品集合
        List<CarShopping> list = new ArrayList<CarShopping>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from carShopping where user_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            while(rs.next()){
                CarShopping goods =new CarShopping();
                goods.setUserId(rs.getInt("user_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setBuyNumber(rs.getInt("buy_number"));
                goods.setGoodsPrice(rs.getDouble("goods_price"));
                goods.setGoodsCity(rs.getString("goods_city"));
                goods.setGoodsId(rs.getInt("goods_id"));
                //加入集合
                list.add(goods);
            }
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
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
     * 检查判断删除的货物id是否在购物车表里
     * @param deleteGoodsId 要删除商品的id
     * @return 若删除商品在购物车里，返回该商品的id，否则返回0
     */
    public static int checkIfDeleteGoodsExistCarShopping(int deleteGoodsId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int buyGoodsId = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        //定义sql,先将username设为？
        String sql = "select * from carshopping";
        //获取执行sql语句对象Statement
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个member的信息
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库中获取会员id
                buyGoodsId = rs.getInt("buygoods_id");
                //判断删除的货物id是否在购物车列表
                if (deleteGoodsId == buyGoodsId) {
                    return buyGoodsId;
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
        return buyGoodsId;
    }

    /**
     *  通过购买商品的buyGoodsId删除购物车的商品
     * @param buyGoodsId  购买商品的id
     * @return 判断是否删除成功
     */
    public static boolean deleteCarShooppingGoodsByBuyGoodsId(int buyGoodsId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from carshopping where buygoods_id = '"+buyGoodsId+" '";
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
}
