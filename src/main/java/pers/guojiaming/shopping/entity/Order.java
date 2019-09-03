package pers.guojiaming.shopping.entity;
/**
 * @Description: 订单的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class Order {
    /**
     * 买家id
     */
    private int buyerId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品出产地址
     */
    private String goodsCity;
    /**
     * 商品地址
     */
    private double goodsPrice;
    /**
     *  购买数量
     */
    private int buyNumber;
    /**
     * 订单编号
     */
    private String orderNumber;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCity() {
        return goodsCity;
    }

    public void setGoodsCity(String goodsCity) {
        this.goodsCity = goodsCity;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
