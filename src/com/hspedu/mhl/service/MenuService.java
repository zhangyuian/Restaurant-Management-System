package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.Menu;

import java.util.List;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description: 完成对 Menu 表的各种操作
 */
public class MenuService {

    //定义 MenuDAO 属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有的菜品，返回给界面使用
    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    //需要一个方法，根据 id，返回 Menu 对象
    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id=?", Menu.class, id);
    }
}
