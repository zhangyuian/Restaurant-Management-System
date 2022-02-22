package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.DiningTableDAO;
import com.hspedu.mhl.domain.DiningTable;

import java.util.List;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description:
 */
public class DiningTableService {

    //定义一个DiningTableDAO对象
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的信息
    public List<DiningTable> getDiningTableStateList() {
        String sql = "select * from diningTable";
        return diningTableDAO.queryMulti(sql, DiningTable.class);
    }

    //根据id，查询对应DiningTable 对象
    //如果返回null，表示id编号对应的餐桌不存在
    public DiningTable getDiningTableById(Integer id) {
        return diningTableDAO.querySingle("select * from diningTable where id=?", DiningTable.class, id);
    }

    //如果餐桌可以预订，调用方法，对其状态进行更新（包括预订人的姓名和电话）
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state='已经预订', orderName=?, orderTel=? where id=?", orderName, orderTel, id);
        return update > 0;
    }

    //需要提供一个更新餐桌状态的方法
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state=? where id=?", state, id);
        return update > 0;
    }

    //提供方法，将指定的餐桌号设置为空闲状态
    public boolean updateDiningTableToFree(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state=? , orderName='', orderTel='' where id=?", state, id);
        return update > 0;
    }
}
