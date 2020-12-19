![](https://i.loli.net/2020/07/22/wHXjsvUFB9JtKIL.png)

# 前言

7月初，我们院里开展了为期五天的Java课程设计，要求在五天之内把分配的课题做出来。我们组分配到的是**图书管理系统**，时间已过去一个多月，现在就请小伙伴们随我来回顾一遍吧~

![](https://i.loli.net/2020/08/17/Qr7kiPBhpnl2Aaz.png)

# 部署说明

1、新建java项目，将图书管理系统项目目录的所有内容导入（复制）到新建的java项目目录中

2、将项目目录中的两个**jar**包导入，分别是**bautyeye_Inf.jar、mysql-connector-java-5.0.8-bin.jar**

3、在数据库中运行**sql.sql**文件

4、运行包名为**startup**中的**BootStrap.java**文件

# 需求分析

1. 登陆界面（包括：用户注册、管理员/读者登录）。
2. 图书/类别管理（包括：对图书或图书类别增删改查）。
3. 借阅管理（包括：图书借阅、归还）。

出于时间原因，我们只设计了三个大方面的需求，光有需求分析还不够，最好还能大概整理出系统模块结构图：

![](https://i.loli.net/2020/08/17/pOzo3JBUkTSsPDE.png)

有了结构图，我们的目标就更明确了，可以很好的为小组成员分工，谁哪一块有问题就找谁，不会扯皮。

# 详细设计

整理完需求和结构图，我们就可以以个人为单位，开始编码设计了。这次课设用的是标准入门技术**JavaSE+MySql**。

我统计了一下，大概有31个主类和1个sql文件。

## 数据库设计

在数据库中创建一个名为db_bms的数据库，共分为4个表，分别是：user、booktype、book、borrow。每张表的功能详情如下：
- user：存储管理员和读者的账号信息。
- booktype：存储书籍的类别信息。
- book：存储图书信息。
- borrow：存储读者借阅图书信息。

下图为**book**表结构：

![](https://i.loli.net/2020/08/17/Fwlh5gnUaX6T7JW.png)

## DAO层

平时大家都戏称它为“dao（刀）层”，dao(data access object)层主要做数据持久层的工作，负责与数据库通信的一些方法都封装在这里，在设计dao层的时候首先设计接口，在实现对应功能，使用时直接调用对应接口即可。属于一种比较底层，比较基础的操作，具体对于某个表的增删改查。

dao层分为两个模块：BookDAO（图书）、BookTypeDAO（图书类别）。

## Service层

service层是服务层，主要负责业务模块的逻辑设计，既然被称为服务层，肯定相比dao层是更加高层次的层结构，它主要把dao层的操作又进行了封装，将业务逻辑与dao层结合起来。

与dao层对应，service层也分为两个模块：BookService、BookTypeService。

## DAO和Service的关系

service层是建立在dao层之上的，建立了dao层才可以开始建立service层，service负责调用dao层接口，将dao层返回的数据继续返回给调用它的方法（通常是controller层）。

# 来吧，展示

![](https://i.loli.net/2020/08/17/lbk4UF8CxIz7whq.png)

![](https://i.loli.net/2020/08/17/Ro1xI7U5ENfqQ6c.png)

![](https://i.loli.net/2020/08/17/lcIANOkRf4qLQjs.png)

# 总结

有很多同学肯定都深受课设折磨，但紧张的课设也是我们快速成长的一个方式，学计算机本身就是多思考，多实践。我一直相信一句话：**没有记录就没有发生，没有整理就没有记忆**。将自己的思考用文字整理出来，其实也是对思维的一种刻意练习和对记忆的加深，每一次回顾都有不一样的感触。

---

关注即可提高学习效率！

![](https://i.loli.net/2020/07/23/ECtfFp5i8VMjmYD.png)
