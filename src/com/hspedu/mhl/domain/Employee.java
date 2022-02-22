package com.hspedu.mhl.domain;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description: 这是一个javabean 与employee 对应
 */
public class Employee {
    private Integer id;
    private String empId;
    private String name;
    private String job;

    public Employee() { // 无参构造器，底层apache-dbutils反射需要
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.job = job;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
