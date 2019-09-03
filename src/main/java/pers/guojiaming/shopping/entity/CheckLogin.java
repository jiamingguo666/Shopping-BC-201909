package pers.guojiaming.shopping.entity;

/**
 * @Description: 用户登录判断用户名是否存在
 * @Author: jiaming.guo
 * @Date: 2019/8/22 10:34
 */
public class CheckLogin {
    /**
     * 用户登录是否成功标志
     */
    private boolean result;
    /**
     * 用户登录标志
     */
    private int flag;

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
