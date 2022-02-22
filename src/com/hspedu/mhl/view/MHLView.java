package com.hspedu.mhl.view;

import com.hspedu.mhl.domain.*;
import com.hspedu.mhl.service.*;
import com.hspedu.mhl.utils.Utility;

import java.util.List;

/**
 * @author: bytedance
 * @date: 2022/2/21
 * @description:
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    private String key = ""; //接受用户的选择
    //定义 EmployeeService 属性
    private EmployeeService employeeService = new EmployeeService();
    //定义 DiningTable 属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义 MenuService 属性
    private MenuService menuService = new MenuService();
    //定义 BillService 属性
    private BillService billService = new BillService();
    //定义 LoginService 属性
    private LoginService loginService = new LoginService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    //人事管理
    public void manageEmployee() {
        System.out.println("==============人事管理==============");
        System.out.println("\t\t 1 显示所有员工的资料");
        System.out.println("\t\t 2 添加员工");
        System.out.println("\t\t 3 删除员工");
        key = Utility.readString(1);
        switch (key) {
            case "1":
                List<Employee> allEmployee = employeeService.getAllEmployee();
                for (Employee employee : allEmployee) {
                    System.out.println(employee);
                }
                break;
            case "2":
                System.out.print("请输入员工ID：");
                String empId = Utility.readString(50);
                System.out.print("请输入员工姓名：");
                String name = Utility.readString(50);
                System.out.print("请输入员工职位：");
                String job = Utility.readString(50);
                System.out.print("请输入员工登录密码：");
                String pwd = Utility.readString(50);
                //1. 添加员工信息
                int i = employeeService.addEmployee(empId, name, job);
                if (i <= 0) {
                    System.out.println("==============人员信息添加失败==============");
                    return;
                }
                System.out.println("==============人员信息添加成功==============");
                //2. 添加员工登录信息
                int j = loginService.addLoginInfo(empId, pwd);
                if (j <= 0) {
                    System.out.println("==============人员登录信息添加失败==============");
                    return;
                }
                System.out.println("==============人员登录信息添加成功==============");
                System.out.println("==============添加成功==============");
                break;
            case "3":
                System.out.print("请输入需要删除员工的编号：");
                empId = Utility.readString(50);
                i = loginService.deleteLoginInfo(empId);
                if (i <= 0) {
                    System.out.println("==============人员登录信息删除失败==============");
                    return;
                }
                System.out.println("==============人员登录信息删除成功==============");
                j = employeeService.deleteEmployee(empId);
                if (j <= 0) {
                    System.out.println("==============人员信息删除失败==============");
                    return;
                }
                System.out.println("==============人员信息删除成功==============");
                System.out.println("==============息删除成功==============");
                break;
        }

    }

    //完成结账
    public void payBill() {
        System.out.println("==============结账服务==============");
        System.out.println("请选择要结账的餐桌编号(-1退出)：");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            return;
        }
        //验证餐桌是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if (diningTable == null) {
            System.out.println("==============结账的餐桌不存在==============");
            return;
        }
        //验证餐桌是否有需要结账的账单
        if (!billService.hasPayBillByDiningTableId(diningTableId)) {
            System.out.println("==============该餐位没有未结账的账单==============");
            return;
        }
        System.out.println("结账方式(现金/支付宝/微信)回车表示退出");
        String payMode = Utility.readString(20, "");
        if ("".equals(payMode)) {
            System.out.println("==============取消结账==============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("==============完成结账==============");
            }
        } else {
            System.out.println("==============结账失败==============");
        }
    }

    //显示账单
    public void listBill() {
//        List<Bill> bills = billService.list();
//        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
//        for (Bill bill : bills) {
//            System.out.println(bill);
//        }
//        System.out.println("==============显示完毕==============");

        List<MultiTableBean> multiTableBeans = billService.list2();
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t菜品名\t\t价格");
        for (MultiTableBean multiTableBean : multiTableBeans) {
            System.out.println(multiTableBean);
        }
        System.out.println("==============显示完毕==============");

    }


    //完成点餐
    public void orderMenu() {
        System.out.println("==============点餐服务==============");
        System.out.println("请输入点餐的桌号(-1退出):");
        int orderDiningTable = Utility.readInt();
        if (orderDiningTable == -1) {
            return;
        }
        System.out.println("请输入点餐的菜品编号(-1退出):");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            return;
        }
        System.out.println("请输入点餐的菜品数量(-1退出):");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            return;
        }

        //验证餐桌号是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTable);
        if (diningTableById == null) {
            System.out.println("==============餐桌号不存在==============");
            return;
        }

        //验证菜品是否存在
        Menu menuById = menuService.getMenuById(orderMenuId);
        if (menuById == null) {
            System.out.println("==============菜品号不存在==============");
            return;
        }

        //开始点餐
        boolean b = billService.orderMenu(orderMenuId, orderNums, orderDiningTable);
        if (b) {
            System.out.println("==============点餐成功==============");
        } else {
            System.out.println("==============点餐失败==============");
        }

    }


    //显示所有菜品
    public void listMenu() {
        List<Menu> list = menuService.list();
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    //完成订座
    public void orderDiningTable() {
        System.out.println("==============预订餐桌==============");
        System.out.println("请选择要预订的餐桌编号(-1退出)：");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("==============取消预订餐桌==============");
            return;
        }
        //该方法得到的是 Y 或者 N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {

            //根据 orderId 返回对应的 DiningTable 对象，如果为null， 说明该对象不存在
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null) {
                System.out.println("==============预订餐桌不存在==============");
                return;
            }
            //判断该餐桌的状态是否 "空"
            if (!("空".equals(diningTable.getState()))) {
                System.out.println("==============该餐桌已经预订或者就餐中==============");
                return;
            }
            //这时说明真的可以预订，更新餐桌的状态
            System.out.println("预订人的名字：");
            String orderName = Utility.readString(50);
            System.out.println("预订人的电话：");
            String orderTel = Utility.readString(50);
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("==============预订餐桌成功==============");
            } else {
                System.out.println("==============预订餐桌失败==============");
            }

        } else {
            System.out.println("==============取消预订餐桌==============");
        }
    }

    //显示所有餐桌状态
    public void ListDiningTable() {
        List<DiningTable> diningTableStateList = diningTableService.getDiningTableStateList();
        System.out.println("\n餐桌编号\t\t餐桌编号");
        for (DiningTable diningTable : diningTableStateList) {
            System.out.println(diningTable);
        }
    }

    //显示主菜单
    public void mainMenu() {
        while (loop) {
            System.out.println("===============满汉楼===============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("输入员工号：");
                    String id = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(50);
                    //到数据库去判断[一会写]
                    Login login = loginService.login(id, pwd);
                    if (login != null) {
                        System.out.println("===============登录成功===============\n");
                        //显示二级菜单
                        while (loop) {
                            System.out.println("===============满汉楼(二级菜单)===============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预订餐桌");
                            System.out.println("\t\t 3 显示所有菜单");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 7 人事管理");
                            System.out.println("\t\t 9 退出");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    ListDiningTable();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "7":
                                    manageEmployee();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你的输入有无，请重新输入：");
                            }
                        }
                    } else {
                        System.out.println("===============登录失败===============");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入.");
            }
        }
    }
}
