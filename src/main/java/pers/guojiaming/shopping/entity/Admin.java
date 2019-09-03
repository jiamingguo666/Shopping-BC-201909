package pers.guojiaming.shopping.entity;
/**
 * @Description: 管理员的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class Admin {
    /**
     * 管理员id
     */
    private int adminId;
    /**
     * 管理员名字
     */
    private String adminName;
    /**
     *  管理员密码
     */
    private int adminPassword;
    /**
     * 管理员省份
     */
    private String adminProvince;
    /**
     * 管理员城市
     */
    private String adminCity;
    /**
     * 管理员性别
     */
    private String adminSex;
    /**
     * 管理员年龄
     */
    private int adminAge;
    /**
     * 管理员头像
     */
    private String headPicture;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(int adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminProvince() {
        return adminProvince;
    }

    public void setAdminProvince(String adminProvince) {
        this.adminProvince = adminProvince;
    }

    public String getAdminCity() {
        return adminCity;
    }

    public void setAdminCity(String adminCity) {
        this.adminCity = adminCity;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    public int getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }
}
