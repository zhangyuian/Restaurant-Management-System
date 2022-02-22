package com.hspedu.mhl.domain;

/**
 * @author: bytedance
 * @date: 2022/2/22
 * @description:
 */
public class Login {
    private Integer id;
    private String empId;
    private String pwd;

    public Login() {
    }

    public Login(Integer id, String empId, String pwd) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public String getEmpId() {
        return empId;
    }

    public String getPwd() {
        return pwd;
    }
}
