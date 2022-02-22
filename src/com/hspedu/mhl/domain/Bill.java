package com.hspedu.mhl.domain;

import java.util.Date;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description: 是一个javaBean 和 bill表对应
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;

    @Override
    public String toString() {
        return id +
                "\t\t" + menuId +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getBillId() {
        return billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public Double getMoney() {
        return money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public String getState() {
        return state;
    }

    public Bill(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Bill() {
    }
}
