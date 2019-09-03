package pers.guojiaming.shopping.entity;

import java.sql.Timestamp;
/**
 * @Description: 主订单的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class MainOrder {
    /**
     *  买家的id
     */
    private int buyerId;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 订单状态
     */
    private String orderState;
    /**
     * 订单金额
     */
    private double orderMoney;
    /**
     * 下单日期
     */
    private Timestamp orderDate;
    /**
     * 收货人姓名
     */
    private String buyerName;
    /**
     * 收货人联系方式
     */
    private String buyerPhone;
    /**
     * 收货人地址
     */
    private String buyerAddress;
    /**
     * 配送方式
     */
    private String buyerShipping;
    /**
     * 备注
     */
    private String buyerRemarks;

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }



    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerShipping() {
        return buyerShipping;
    }

    public void setBuyerShipping(String buyerShipping) {
        this.buyerShipping = buyerShipping;
    }

    public String getBuyerRemarks() {
        return buyerRemarks;
    }

    public void setBuyerRemarks(String buyerRemarks) {
        this.buyerRemarks = buyerRemarks;
    }
}
