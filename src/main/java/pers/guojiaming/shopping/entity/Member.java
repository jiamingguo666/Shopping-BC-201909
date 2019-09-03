package pers.guojiaming.shopping.entity;

import java.sql.Timestamp;
/**
 * @Description: 会员的实体类
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
public class Member {
    /**
     * 会员id
     */
    private int memberId;
    /**
     * 会员费
     */
    private int memberPrice;
    /**
     * 会员名
     */
    private String memberName;
    /**
     *  会员联系方式
     */
    private String memberPhone;
    /**
     * 会员注册时间
     */
    private Timestamp startDate;
    /**
     * 会员到期时间
     */
    private Timestamp deadline;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
}
