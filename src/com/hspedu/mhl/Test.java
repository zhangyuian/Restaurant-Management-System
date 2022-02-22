package com.hspedu.mhl;

import java.util.UUID;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            String billID = UUID.randomUUID().toString();
            System.out.println(billID);
        }
    }
}
