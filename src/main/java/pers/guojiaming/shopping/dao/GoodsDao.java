package pers.guojiaming.shopping.dao;

import pers.guojiaming.shopping.entity.Goods;
import pers.guojiaming.shopping.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @Description: 执行mysql的商品表的增删改查
 * @Author: jiaming.guo
 * @Date: 2019/8/18 14:34
 */
public class GoodsDao {
    /**
     *  获取数据库内
     * @return  所有的普通商品资料列表
     */
    public ArrayList<Goods> getAllGoods(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //商品集合
        ArrayList<Goods> list = new ArrayList<Goods>();
        conn = JdbcUtils.getConnection();
        String sql = "select * from goods where category = 'ordinary_goods'";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                //从数据库获取商品的资料，存在goods
                Goods goods =new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setCity(rs.getString("city"));
                goods.setPrice(rs.getInt("price"));
                goods.setNumber(rs.getInt("number"));
                goods.setPicture(rs.getString("picture"));
                //将所有的商品资料加入集合list
                list.add(goods);
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
     * 根据商品编号获取商品资料
     * @param id  商品id
     * @return  单个商品的所有信息
     */

    public Goods getGoodsById(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        String sql = "select * from goods where id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()){
                Goods goods =new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setCity(rs.getString("city"));
                goods.setPrice(rs.getInt("price"));
                goods.setNumber(rs.getInt("number"));
                goods.setMemberPrice(rs.getDouble("member_price"));
                goods.setDiscountPrice(rs.getDouble("discount_price"));
                goods.setPicture(rs.getString("picture"));
                return goods;
            }else{
                return null;
            }
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
     * 管理员增加商品
     * @param goods 要增加商品的资料
     * @return 判断是否增加成功
     */
    public static boolean addGoods(Goods goods){
        //将数据库中图片路径的\转换为/
        String picture=goods.getPicture();
        picture=picture.replace("\\","/");
        goods.setPicture(picture);
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否添加成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库插入信息
          String sql = " insert into goods(name,city,price,number,category,member_price,discount_price,integral_price,picture) values('"+goods.getName()+"','"+goods.getCity()+"','"+goods.getPrice()+"','"+goods.getNumber()+"','"+goods.getCategory()+"','"+goods.getMemberPrice()+"','"+goods.getDiscountPrice()+"','"+goods.getIntegralPrice()+"','"+goods.getPicture()+"')";
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
        //若插入成功，返回true，否则返回false
        if (count > 0) {
            f = true;
        }
        return f;
    }

    /**
     * 通过商品的id删除商品
     * @param goodsId  商品id
     * @return  判断是否删除成功
     */
    public static boolean deleteById(int goodsId){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否删除成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库删除信息
        String sql = "delete from goods where id = '"+ goodsId +" '";
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
     * 管理员更新商品信息，含图片
     * @param goodsName  商品名
     * @param goodsCity 商品出产地
     * @param price 商品价格
     * @param memberPrice  会员价
     * @param discountPrice  优惠
     * @param number  库存
     * @param goodsId  商品id
     * @param picture  商品图
     * @return  判断是否修改成功
     */
    public static boolean updateGoods(String goodsName,String goodsCity,int price,double memberPrice,double discountPrice,int number,int goodsId,String picture){
        Connection conn = null;
        PreparedStatement stmt = null;
        //判断是否修改成功
        boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update goods set name='"+goodsName+"',city='"+goodsCity+"',price='"+price+"',member_price = '"+memberPrice+"',discount_price = '"+discountPrice+"',number='"+number+"',picture='"+picture+"'where id ='"+goodsId+"'";
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
     *  获取数据库内所有的会员商品资料
     * @return 所有会员商品资料列表
     */
        public ArrayList<Goods> getAllMemberGoods(){
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            //商品集合
            ArrayList<Goods> list = new ArrayList<Goods>();
            conn = JdbcUtils.getConnection();
            String sql = "select * from goods where category = 'member_goods'";
            try {
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    //从数据库获取商品的资料，存在goods
                    Goods goods =new Goods();
                    goods.setId(rs.getInt("id"));
                    goods.setName(rs.getString("name"));
                    goods.setCity(rs.getString("city"));
                    goods.setNumber(rs.getInt("number"));
                    goods.setPrice(rs.getInt("price"));
                    goods.setMemberPrice(rs.getDouble("member_price"));
                    goods.setCategory(rs.getString("category"));
                    goods.setDiscountPrice(rs.getDouble("discount_price"));
                    goods.setPicture(rs.getString("picture"));
                    //将所有的商品资料加入集合list
                    list.add(goods);
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
     *  判断购买数量是否在商品库存范围内
     * @param buyGoodsId  购买的商品的id
     * @param buyGoodsNumber 购买的商品数量
     * @return 库存量减去购买量，若值小于0.则在库存范围内，否则超过库存
     */
    public static  int buyNumberCompareGoodsNumber(int buyGoodsId,int buyGoodsNumber){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //剩余量
        int surplusNumber = 0;
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();
        String sql = "select * from goods where id = "+buyGoodsId;
        try {
            stmt = conn.prepareStatement(sql);
            //执行查询语句，得到一个user的信息
            rs = stmt.executeQuery();
            while (rs.next()){
                int number = rs.getInt("number");
                //库存量减去购买数量
                surplusNumber = number - buyGoodsNumber;
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
        return surplusNumber;
    }

    /**
     * 下单后，减少商品库存
     * @param buyNumber 下单的商品数量
     * @param buyGoodsId 下单的商品id
     */
    public static void updateGoodsNumber(int buyNumber,int buyGoodsId){
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
        String sql = "update goods set number = number-'"+buyNumber+"'where id ='"+buyGoodsId+"'";
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

    /**
     * 更新商品信息
     * @param goodsName 商品名
     * @param goodsCity 商品出产地
     * @param price  商品价格
     * @param memberPrice  会员价
     * @param discountPrice 优惠
     * @param number  库存量
     * @param goodsId  商品id
     * @return  判断是否更新成功
     */
      public static boolean updateGoodsExceptPicture(String goodsName,String goodsCity,int price,double memberPrice,double discountPrice,int number,int goodsId){
          Connection conn = null;
         PreparedStatement stmt = null;
         //判断是否修改成功
         boolean f = false;
        conn = JdbcUtils.getConnection();
        //向数据库修改信息
          String sql = "update goods set name='"+goodsName+"',city='"+goodsCity+"',price='"+price+"',member_price = '"+memberPrice+"',discount_price = '"+discountPrice+"',number='"+number+"'where id ='"+goodsId+"'";
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


