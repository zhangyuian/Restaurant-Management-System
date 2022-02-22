package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.EmployeeDAO;
import com.hspedu.mhl.domain.Employee;

import java.util.List;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description: 该类完成对 employee 表的各种操作（通过调用EmployeeDAO对象完成）
 */
public class EmployeeService {

    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //根据 empId 和 pwd 来查询
    //如果查询不到，就返回null
//    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
//        String sql = "select * from employee where empId=? and pwd=MD5(?)";
//        return employeeDAO.querySingle(sql, Employee.class, empId, pwd);
//    }

    public List<Employee> getAllEmployee() {
        return employeeDAO.queryMulti("select * from employee", Employee.class);
    }

    //添加员工
    public int addEmployee(String empId, String name, String job) {
        return employeeDAO.update("insert into employee values(null,?,?,?)", empId, name, job);
    }

    //删除员工
    public int deleteEmployee(String empId) {
        return employeeDAO.update("delete from employee where empId=?", empId);
    }

}
