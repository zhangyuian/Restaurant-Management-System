package com.hspedu.mhl.domain;

import java.util.Date;

/**
 * @author: bytedance
 * @date: 2022/2/22
 * @description: 这是一个javaBean 可以和多张表进行对应
 */
public class MultiTableBean {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;
    //增加一个来自 menu 表的列 name
    private String name;
    private Double price;

    @Override
    public String toString() {
        return id +
                "\t\t" + menuId +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + state +
                "\t\t" + name +
                "\t\t" + price;

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

//    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state, String name, Double price) {
//        this.id = id;
//        this.billId = billId;
//        this.menuId = menuId;
//        this.nums = nums;
//        this.money = money;
//        this.diningTableId = diningTableId;
//        this.billDate = billDate;
//        this.state = state;
//        this.name = name;
//        this.price = price;
//    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MultiTableBean() {
        System.out.println("反射调用");
    }
}
