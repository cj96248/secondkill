# Seconds kill System

Secondskill 是基于 SpringBoot 构建的秒杀系统，基本实现了从用户注册、登录，到商品展示，到订单管理，到商品秒杀的整个流程。


## 项目起源

来源于慕课网实战课程 [SpringBoot构建电商基础秒杀项目](http://www.imooc.com/learn/1079)，我尽可能保证与授课代码一致，在个别地方稍作修改。

> 如果刚开始看视屏就参考代码，会有很多地方不一致，是因为授课老师会在后期对代码不断优化。


## 技术选型

### 工具

- idea 2017

### 软件支持

- Java 8 （代码中使用到了lambda表达式）
- Mysql 5.7 （代码使用到了事务，需要存储引擎为InnoDB）

### 后端技术

- Spring-boot 1.5.2.RELEASE
- mysql-connector 5.1.22
- druid 1.1.3
- commons-lang3
- hibernate-validator

### 前端技术

- Metronic (使用到了该框架，不过主要用到css)
- jQuery
- HTML

## 项目运行

1. 确保已经启动了Mysql
2. 导入`seckill.sql`文件:`source seckill.sql`
3. 在idea中运行`App.class`

访问 http://localhost:9090/login.html 查看是否正常。

## 包结构

这个项目最令人激动就是这个包结构了，活脱脱一个**大型项目**的感觉。

```
secondkill
|- java
| App------------------------- 启动类
|   |- controller------------- 控制层，restful接口
|   |   |- viewobject--------- 控制层模型，用户前端显示
|   |- dao-------------------- 数据访问层，只有接口定义，代码由插件生成
|   |- dataobject------------- 数据层模型，代码由插件生成
|   |- error------------------ 统一错误处理
|   |- response--------------- 归一化结果集
|   |- service---------------- 服务层，只有接口定义
|   |   |- impl--------------- 服务层实现
|   |   |- model-------------- 服务层模型
|   |- util------------------- 工具类
|   |- validator-------------- 统一校验器
|- resources
|   |- mapping---------------- mybatis映射文件
|   |- static----------------- 前端代码
|   | application.yml--------- 配置文件
|   | mybatis-generator.xml--- mybatis生成器配置文件
|   | seckill.sql------------- 数据库文件
```

## 四大模块

### 用户模块

1. 用户登录
2. 用户注册逻辑实现
3. 用户详细信息填写
4. 用户密码分表、加密存储

### 商品模块

1. 创建一个商品
2. 商品列表信息浏览
3. 商品详细信息浏览

### 交易模块

1. 商品订单 ID 生成策略
2. 商品订单生成

### 秒杀模块

1. 秒杀活动未开始
2. 秒杀活动进行中
3. 秒杀活动已结束

## 成果展示

### 用户获取验证码

`http://localhost:9090/getotp.html`

![获取验证码](./src/main/resources/static/images/info/pic1226220152.png)

### 用户注册

`http://localhost:9090/register.html`


![用户注册](./src/main/resources/static/images/info/pic1226220225.png)

### 用户登录

`http://localhost:9090/login.html`

![用户注册](./src/main/resources/static/images/info/pic1226220212.png)

### 商品列表展示

`http://localhost:9090/listitem.html`

![商品列表展示](./src/main/resources/static/images/info/pic1226220647.png)

### 商品详情

`http://localhost:9090/detail.html?id=3`

![商品详情](./src/main/resources/static/images/info/pic1226220720.png)

### 秒杀时详情页

`http://localhost:9090/detail.html?id=3`

![商品详情](./src/main/resources/static/images/info/pic0109223249.png)

`http://localhost:9099/promo.html?id=3`

### 秒杀未开始
![商品详情](./src/main/resources/static/images/info/pic0109223513.png)

`http://localhost:9099/promo.html?id=3`

### 秒杀进行中
![商品详情](./src/main/resources/static/images/info/pic0109223321.png)

`http://localhost:9099/promo.html?id=2`

### 秒杀已结束
![商品详情](./src/main/resources/static/images/info/pic0109223303.png)

## 项目思考

1. 使用SpringBoot + MyBatis 开发Java web项目
2. 电商秒杀系统的基本实现流程
3. 该项目的分层思想可以直接运用到实际生产中
4. 用户模块花了很多心思在分表的处理，安全性考虑周密
5. 商品模块热点数据的处理思路
6. 订单模块处理流程，事务的处理
7. 秒杀模块需要对前期准备做大幅度修改，侵入性过高

## 遗留问题

1. 容量问题
2. 水平扩展
3. 查询效率低下
4. 库存行锁
5. 下单需要操作太多





