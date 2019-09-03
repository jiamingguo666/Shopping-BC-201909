package pers.guojiaming.shopping.entity;
/**
 * @Description: 购物车的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class CarShopping {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 购买数量
     */
    private int buyNumber;
    /**
     * 商品价格
     */
    private double goodsPrice;
    /**
     * 商品出产地
     */
    private String goodsCity;
    /**
     *  商品id
     */
    private int goodsId;
    /**
     * 购买的商品id，和商品列表的商品id对应
     */
    private int buygoodsId;

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getBuygoodsId() {
        return buygoodsId;
    }

    public void setBuygoodsId(int buygoodsId) {
        this.buygoodsId = buygoodsId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }



    public String getGoodsCity() {
        return goodsCity;
    }

    public void setGoodsCity(String goodsCity) {
        this.goodsCity = goodsCity;
    }
}
