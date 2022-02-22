package com.hspedu.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: bytedance
 * @date: 2022/2/20
 * @description:
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        //测试Utility 工具类
        System.out.println("请输入一个整数");
        int i = Utility.readInt();
        System.out.println("i=" + i);

        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
