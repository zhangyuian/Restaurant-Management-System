# Restaurant-Management-System
餐厅管理-JDBC、MySQL数据库、德鲁伊连接池实战；用户登录、订座、点餐、结账、人事管理
# 满汉楼
## 写项目之前，需要写需求白皮书
1. 需要实现的功能
2. 框架搭建

![image.png](https://note.youdao.com/yws/res/1/WEBRESOURCE1dbc0365b200fc555baea9360b8490f1)

软件分层的好处：
1.  可以用不同的包来存放对应的类
2.  体现了一个调用的关系
3.  各层可以各司其职

## 功能1：满汉楼界面
1. 先写框架，==把框架先搭建起来，先别考虑细节==！
2. 对应到界面就是先把界面建起来，功能先不写！


## 功能2：用户登录
写代码的顺序：==从底层开始往上层分析，从下往上写代码==
1. 先写数据库：创建 mhl 数据库，建立 employee 表
2. 写 Domian 层，建立 employee 类与数据库的表映射起来
3. 写 DAO 层，写对表 employee 增删改查的操作
4. 写 Service 层，，组织 SQL 语句，调用 DAO 层对表 employee 进行 CRUD 操作

## 功能3: 显示所有餐桌状态
和功能2差不多，通过 select 语句查询餐桌状态
1. 先写数据库：建立 diningTable 表
2. 写 Domian 层，建立 diningTable 的 javaBean
3. 写 DAO 层，写对表 diningTable 增删改查的操作
4. 写 Service 层，组织 SQL 语句

## 功能4：订座
![image.png](https://note.youdao.com/yws/res/d/WEBRESOURCEf7e3b3342d8da452c68e7cac5f83802d)
- 功能说明：
如果该餐桌处于==已经预订==或者==就餐状态==，给出提示

- 思路分析：
1. 检测餐桌是否存在
2. 检测餐桌的状态

## 功能5：显示所有菜品
![image.png](https://note.youdao.com/yws/res/8/WEBRESOURCE50992dd7f16cf169d509fb0764efcbf8)

思路和前面的基本一致
1. 数据库中创建 Menu 表
2. 在 Domain 层创建 javaBean Menu类
3. 在 DAO 层创建 MenuDAO 用于对表 Menu 进行增删改查操作
4. 在 Service 层创建 MenuService 用于组织具体的 SQL 语句


## 功能6：点餐
![image.png](https://note.youdao.com/yws/res/e/WEBRESOURCE3e7712aef321d54236e088ade6936a9e)

> 自己分析：
> 1. 点餐的桌号：DiningTable表
> 2. 菜品编号：Menu表
> 3. 菜品数量：
> 4. 是否点这个菜：
> 这里需要建立一个账单 bill 表


功能说明：要求队餐桌号，菜品编号，做合理性校验，如果不合理，给出提示信息。

思路分析：
1. 餐桌号，菜品编号 校验
2. 点餐成功，需要修改餐桌状态
3. 生成账单

> javaBean 中 Date 类是 java.util 中的 Date，不是 java.sql 的 Date


## 功能7：查看账单

返回账单数据，显示在界面上

## 功能8：结账

![image.png](https://note.youdao.com/yws/res/9/WEBRESOURCEcbdf6a60155fe4bc34871792371a08a9)

> 自己分析：
> 1. 选择要结账的餐桌编号：将餐桌状态改成-空
> 2. 结账的方式：将账单状态改成xxx
> 3. 确定是否结账

思路分析：
1. 对餐桌号进行校验
2. 修改 bill 表的 state
3. 修改 DiningTable 信息
4. 不需要增加新表，不需要增加新的类，需要增加方法

==bug==：用数据库查询单条数据为null，实际在数据库后台能够查询到该数据。
bug 原因：编码问题，查询语句中含有中文，而在 Druid 数据库连接配置中没有指定编码为 UTF-8，因此中文的查询返回会变成null。
解决方法：在 druid 数据库配置文件中的url最后添加 &characterEncoding=UTF-8

## 功能9-拓展思考：多表查询
### 如果多表查询怎么处理？
比如：查看账单时，还想显示菜品名
1. 新增一个 javaBean 类 MultiTableBean 用来映射多表查询的结果
2. 相应的，还需要新增 DAO 类来处理多表查询
3. 在 Service 层新建一个处理的类或者写在 BillService 类里面

如果还要再新增price字段，显示菜品的价格
1. 依然在原来的 javaBean 类 MultiTableBean 新增 price 即可

### 细节1：
后面就算 sql 语句中没有查询 price 这一列，price显示就只是为空而已

### 细节2：
javaBean 中的有参构造器其实没有必要存在。因为在底层中是通过反射调用无参构造器来生成 javaBean 对象，并通过 setter 方法赋值的。

### 细节3
思考：如果 MultiTableBean 中的属性和表中的列名不一致会怎么样？如将 name 改成 name2
答：会出问题，因为底层是通过调用 setName() 来给 MultiTableBean 对象赋值的，给 name2 赋值则是需要通过调用 setName2() ，因此赋值失败。
> 可以通过 sql 语句将列改成 AS name2 解决，但是最好保持一致


### 如果后面有越来越多的多表查询怎么办？
MutiTableBean 会越来越大，这时候可以根据业务来拆分大的 MutiTableBean

![image.png](https://note.youdao.com/yws/res/a/WEBRESOURCE2091a6c19e4469a560e38ab1d987ed5a)

## 功能10-拓展-登录管理&人事管理
拓展1：登录管理：员工信息字段可能有很多，而且员工数也会很多，为提高效率，可以采用==分表设计== employee 和 login
思路：
1. 数据库中将员工表一分为二，分为登录表和信息表
2. domain 层新建两个javaBean：Login 类和 Employee 类
3. DAO 层中新建 LoginDAO 和 EmployeeDAO 用于管理登录

![image.png](https://note.youdao.com/yws/res/3/WEBRESOURCEd38ec80cea7922effd5b8276f914eaf3)

![image.png](https://note.youdao.com/yws/res/6/WEBRESOURCE20a896088f0f780d5dfd1458a40623c6)

拓展2：人事管理
添加、删除员工信息
思路：
1. 添加员工信息和删除员工信息：分别在employeeService 里面添加两个方法进行添加和删除
2. 添加员工登录信息和删除员工登录信息：分别在login里面添加两个方法进行添加和删除

## 总结：项目的难点，架构设计
项目的层次分为 Domain层、DAO层、Service层，View 层以及 Utils 工具层

1. Domian 层与数据库中的表一一映射，将数据库中查询得到的表对应为 java 中的对象，也就是 javaBean 

2. DAO 层负责对数据库进行操作，其有一个 BasicDAO 类用于提供基础的操作，用到Druid连接池与数据库进行连接，其他的 DAO 层在继承了该类的获取基础操作功能之外还可以有其他的拓展功能

3. Service 层用于组织具体的 sql 语句，提供特定的服务给 View 层

4. View 层为界面层用于接受用户的输入，显示输出。
