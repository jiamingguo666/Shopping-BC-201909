package pers.guojiaming.shopping.dao;

import pers.guojiaming.shopping.entity.MainOrder;
import pers.guojiaming.shopping.entity.Order;
import pers.guojiaming.shopping.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
/**
 * @Description: 执行mysql的订单表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:23
 */
public class OrderDao {
    /**
     * 将提交订单页面的数据上传到数据表mainorder
     * @param buyerId 购买用户的id
     * @param orderNumber 订单编号
     * @param orderState 订单状态
     * @param orderMoney 订单金额
     * @param orderDate 下单时间
     * @param buyerName 收货人姓名
     * @param buyerPhone 收货人联系方式
     * @param buyerAddress 收获地址
     * @param buyerShipping 配送方式
     * @param buyerRemarks 买家备注
     */
    public static void addMainOrder(int buyerId, String orderNumber, String orderState, double orderMoney,
                                    Timestamp orderDate, String buyerName, String buyerPhone, String buyerAddress,
                                    String buyerShipping, String buyerRemarks) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取数据库连接对象
            conn = JdbcUtils.getConnection();
            String sql = "insert into mainorder(buyer_id,order_number,order_state,order_money,order_date,buyer_name," +
                    "buyer_phone,buyer_address,buyer_shipping,buyer_remarks) values (?,?,?,?,?,?,?,?,?,?)";
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给字段赋值
            stmt.setInt(1, buyerId);
            stmt.setString(2, orderNumber);
            stmt.setString(3, orderState);
            stmt.setDouble(4, orderMoney);
            stmt.setTimestamp(5, orderDate);
            stmt.setString(6, buyerName);
            stmt.setString(7, buyerPhone);
            stmt.setString(8, buyerAddress);
            stmt.setString(9, buyerShipping);
            stmt.setString(10, buyerRemarks);
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
     *  将提交订单页面的数据上传到数据submitorder
     * @param buyerId 购买用户的id
     * @param goodsName 所购商品的名称
     * @param goodsCity  所购商品的产地
     * @param goodsPrice 所购商品的价格
     * @param buyNumber  购买的数量
     * @param orderNumber 订单号
     * @param buyGoodsId 所购商品的id
     */
    public static void addSubmitorder(int buyerId, String goodsName, String goodsCity, double goodsPrice,
                                      int buyNumber, String orderNumber, int buyGoodsId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取数据库连接对象
            conn = JdbcUtils.getConnection();
            String sql = "insert into submitorder(buyer_id,goods_name,goods_city,goods_price,buy_number,order_number,goods_id) values (?,?,?,?,?,?,?)";
            //获取执行sql语句对象Statement
            stmt = conn.prepareStatement(sql);
            //给字段赋值
            stmt.setInt(1, buyerId);
            stmt.setString(2, goodsName);
            stmt.setString(3, goodsCity);
            stmt.setDouble(4, goodsPrice);
            stmt.setInt(5, buyNumber);
            stmt.setString(6, orderNumber);
            stmt.setInt(7, buyGoodsId);
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
     * 计算每个买家订单页面的总价
     * @param buyerId 买家用户的id
     * @return 每个买家每个订单的金额
     */
    public static double everyOrderPriceByUserId(int buyerId) {
        double everOrderPrice = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        String sql = "select * from carshopping where user_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, buyerId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int buyNumber = rs.getInt("buy_number");
                double nuitPrice = rs.getDouble("goods_price");
                everOrderPrice += buyNumber * nuitPrice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
        return everOrderPrice;
    }

    /**
     * 根据用户的id和订单状态获取该用户所有的订单编号
     * @param userId 用户id
     * @param orderState 订单状态
     * @return  每个用户每个订单状态下的所有订单编号
     */
    public static ArrayList<String> getAllOrderNumbersByUserIdAndState(int userId, String orderState) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //用户所有订单编号的集合
        ArrayList<String> orderNumberList = new ArrayList<String>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from mainorder where buyer_id  = '" + userId + "' and order_state = '" + orderState + "'";
        try {
            String orderNumber = "";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //获取订单编号
                String orderNumber1 = rs.getString("order_number");
                System.out.println(orderNumber1);
                //判读该用户的订单编号是否重复，不重复则将订单编号存在orderNumberList
                if (orderNumber1 != null && !orderNumber1.equals(orderNumber)) {
                    //加入集合
                    orderNumberList.add(orderNumber1);
                    orderNumber = orderNumber1;
                }
            }
            return orderNumberList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
     * 根据订单编号获取该订单下的收货人信息
     * @param orderNumber 订单编号
     * @return 每个订单编号下的收货人的所有信息
     */
    public MainOrder getReceiverByOrderNumber(String orderNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        String sql = "select * from mainorder where order_number  = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderNumber);
            rs = stmt.executeQuery();
            if (rs.next()) {
                MainOrder mainOrder = new MainOrder();
                mainOrder.setOrderNumber(rs.getString("order_number"));
                mainOrder.setOrderState(rs.getString("order_state"));
                mainOrder.setOrderMoney(rs.getDouble("order_money"));
                mainOrder.setOrderDate(rs.getTimestamp("order_date"));
                mainOrder.setBuyerId(rs.getInt("buyer_id"));
                mainOrder.setBuyerName(rs.getString("buyer_name"));
                mainOrder.setBuyerAddress(rs.getString("buyer_address"));
                mainOrder.setBuyerPhone(rs.getString("buyer_phone"));
                mainOrder.setBuyerShipping(rs.getString("buyer_shipping"));
                mainOrder.setBuyerRemarks(rs.getString("buyer_remarks"));
                return mainOrder;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
     * 根据订单编号获取该该编号下的所有商品信息，并存在goodList表里
     * @param orderNumber  订单编号
     * @return 单个订单编号的所有商品信息列表
     */
    public ArrayList<Order> getAllGoodsByOrderNumber(String orderNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //商品集合
        ArrayList<Order> goodsList = new ArrayList<Order>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from submitorder where order_number =?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //从数据库获取商品的资料，存在order
                Order order = new Order();
                order.setGoodsName(rs.getString("goods_name"));
                order.setGoodsCity(rs.getString("goods_city"));
                order.setGoodsPrice(rs.getDouble("goods_price"));
                order.setBuyNumber(rs.getInt("buy_number"));
                //将所有的商品资料加入集合list
                goodsList.add(order);
            }
            return goodsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
     * 根据订单编号修改订单状态
     * @param orderNumber 订单编号
     * @param orderState 订单状态
     * @return 判断是否修改成功
     */
    public static boolean updateOrderStateByOrderNumber(String orderNumber, String orderState) {
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update mainorder set order_state ='" + orderState + "'where order_number ='" + orderNumber + "'";
        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
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
     * 管理员根据订单状态获取所有用户所有的订单编号
     * @param orderState 订单状态
     * @return 每个订单状态获取的所有用户所有的订单编号列表
     */
    public static ArrayList<String> getAllOrderNumbersByOrderState(String orderState) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //用户所有订单编号的集合
        ArrayList<String> orderNumberList = new ArrayList<String>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from mainorder where order_state = '" + orderState + "'";
        try {
            String orderNumber = "";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String orderNumber1 = rs.getString("order_number");
                System.out.println(orderNumber1);
                if (orderNumber1 != null && !orderNumber1.equals(orderNumber)) {
                    //加入集合
                    orderNumberList.add(orderNumber1);
                    orderNumber = orderNumber1;
                }
            }
            return orderNumberList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
     * 取消订单后根据订单编号将该该编号下的所有商品的数量加回库存
     * @param orderNumber 订单编号
     */
    public static void addGoodsNumberByOrderNumber(String orderNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //商品集合
        ArrayList<Order> goodsList = new ArrayList<Order>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from submitorder where order_number =?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //获取该订单编号的商品id和购买数量
                int goodsId = rs.getInt("goods_id");
                int buyNumber = rs.getInt("buy_number");
                int buyGoodsNumber = 0 - buyNumber;
                //调用方法将数量加回库存
                GoodsDao.updateGoodsNumber(buyGoodsNumber, goodsId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
     * 计算每个用户每个订单状态下的商品总价
     * @param userId  用户id
     * @param orderState 订单状态
     * @return 每个用户每个订单状态下的商品总价
     */
    public static double sumPriceByUserIdAndOrderState(int userId, String orderState) {
        double sumPrice = 0;
        OrderDao orderDao = new OrderDao();
        //根据用户id和订单状态获取订单的编号
        ArrayList<String> orderNumberList = OrderDao.getAllOrderNumbersByUserIdAndState(userId, orderState);
        if (orderNumberList != null && orderNumberList.size() > 0) {
            for (int i = 0; i < orderNumberList.size(); i++) {
                //依次获取订单编号
                ArrayList<Order> goodsList = orderDao.getAllGoodsByOrderNumber(orderNumberList.get(i));
                //获取该订单编号下的商品价格和购买数量
                if (goodsList != null && goodsList.size() > 0) {
                    for (int j = 0; j < goodsList.size(); j++) {
                        Order order = goodsList.get(j);
                        int buyNumber = order.getBuyNumber();
                        double goodsPrice = order.getGoodsPrice();
                        sumPrice += buyNumber * goodsPrice;
                    }
                }
            }
        }
        return sumPrice;
    }

    /**
     * 计算所有用户每个订单状态下的总价
     * @param orderState   订单状态
     * @return
     */
    public static double sumPriceByOrderState(String orderState) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double sumPrice = 0;
        conn = JdbcUtils.getConnection();
        String sql = "select * from mainorder where order_state   = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderState);
            rs = stmt.executeQuery();
            while (rs.next()) {
               double orderMoney =  rs.getDouble("order_money");
               sumPrice += orderMoney;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
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
}