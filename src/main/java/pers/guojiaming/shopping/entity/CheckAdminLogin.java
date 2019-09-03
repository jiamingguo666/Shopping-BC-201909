package pers.guojiaming.shopping.entity;
/**
 * @Description: 管理员登录判断用户名是否存在
 * @Author: jiaming.guo
 * @Date: 2019/8/22 10:34
 */
public class CheckAdminLogin {
    /**
     * 管理员登录标志
     */
    private int adminFlag;
    /**
     * 管理员id
     */
    private int adminId;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(int adminFlag) {
        this.adminFlag = adminFlag;
    }
}
