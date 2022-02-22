package com.hspedu.mhl.domain;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description: 这是一个javaBean 和 DiningTable对应
 */
public class DiningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderTel='" + orderTel + '\'' +
                '}';
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public DiningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public DiningTable() {
    }
}
