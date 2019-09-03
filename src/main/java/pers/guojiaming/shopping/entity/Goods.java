package pers.guojiaming.shopping.entity;
/**
 * @Description: 商品的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class Goods {
    /**
     * 商品id
     */
    private int id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品出产地
     */
    private String city;
    /**
     * 商品价格
     */
    private int price;
    /**
     * 商品库存
     */
    private int number;
    /**
     *  商品图
     */
    private String picture;
    /**
     * 商品种类
     */
    private String category;
    /**
     * 商品的会员价格
     */
    private double memberPrice;
    /**
     * 商品的积分
     */
    private double integralPrice;
    /**
     *  商品会员价和原价的差值
     */
    private double discountPrice;

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(double integralPrice) {
        this.integralPrice = integralPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Goods(){};

    public Goods(int id,String name,String city,int price,int number,String picture){
        this.id = id;
        this.name = name;
        this.city = city;
        this.price = price;
        this.number = number;
        this.picture = picture;
    }
}
