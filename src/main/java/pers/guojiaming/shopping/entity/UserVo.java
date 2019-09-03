package pers.guojiaming.shopping.entity;
/**
 * @Description: 用户的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class UserVo {
    /**
     * 用户id
     */
    private int id;
    /**
     *  用户名
     */
    private String username;
    /**
     *  用户密码
     */
    private String password;
    /**
     * 用户省份
     */
    private String province;
    /**
     *  用户城市
     */
    private String city;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户年龄
     */
    private int age;
    /**
     *  用户头像
     */
    private String headPicture;
    /**
     *  用户G币
     */
    private double money;
    /**
     * 用户积分
     */
    private double integral;

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
