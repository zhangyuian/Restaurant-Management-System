package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.LoginDAO;
import com.hspedu.mhl.domain.Login;

/**
 * @author: bytedance
 * @date: 2022/2/22
 * @description:
 */
public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public Login login(String empId, String pwd) {
        return loginDAO.querySingle("select * from login where empId=? and pwd=MD5(?)", Login.class, empId, pwd);
    }

    public int addLoginInfo(String empId, String pwd) {
        return loginDAO.update("insert into login values(null, ?, MD5(?))", empId, pwd);
    }

    public int deleteLoginInfo(String empId) {
        return loginDAO.update("delete from login where empId=?", empId);
    }
}
