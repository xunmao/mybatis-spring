# mybatis-spring

此项目用于学习和尝试 MyBatis-Spring ( https://mybatis.org/spring/zh/index.html ) 框架的各种功能

## 搭建环境

1. 在本地环境安装 MySQL 数据库。
2. 导入 Sakila 示例数据库。( https://dev.mysql.com/doc/sakila/en/ )

## 准备工作

### 创建 Maven 项目

使用以下命令创建 Maven 项目。参考：  
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

```sh
mvn archetype:generate -DgroupId=com.xunmao.demo -DartifactId=mybatis-spring -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

### 导入依赖

#### Connector/J

```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.29</version>
</dependency>
```

#### MyBatis

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.10</version>
</dependency>
```

#### MyBatis Spring

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>2.0.7</version>
</dependency>
```

#### Spring Core

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-core</artifactId>
  <version>5.3.22</version>
</dependency>
```

#### Spring Context

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.3.22</version>
</dependency>
```

#### Spring JDBC

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>5.3.22</version>
</dependency>
```

### 目录结构

此项目采用 MyBatis 官方推荐的目录结构。

```
/my_application
  /bin
  /devlib
  /lib                <-- MyBatis *.jar 文件在这里。
  /src
    /org/myapp/
      /action
      /data           <-- MyBatis 配置文件在这里，包括映射器类、XML 配置、XML 映射文件。
        /mybatis-config.xml
        /BlogMapper.java
        /BlogMapper.xml
      /model
      /service
      /view
    /properties       <-- 在 XML 配置中出现的属性值在这里。
  /test
    /org/myapp/
      /action
      /data
      /model
      /service
      /view
    /properties
  /web
    /WEB-INF
      /web.xml
```

### 配置 MyBatis

TODO

## 第一个映射器（Mapper）

### 创建 MyBatis 工具类

**此项目不再需要创建 MyBatis 工具类。**  
Mybatis-Spring 可以创建一个线程安全的映射器，这样就可以直接注入到其它的 bean 中了。  
再也不需要担心创建、打开、关闭 session，MyBatis-Spring 将打理好一切。  
https://mybatis.org/spring/zh/mappers.html

### 创建并测试 Actor 类（POJO）及其映射器

参考以下项目：  
https://github.com/xunmao/mybatis

1. 创建 Actor 类（POJO）
2. 创建 ActorMapper 类（映射器）
3. 创建测试类

## 实现租赁 DVD 服务

租赁 DVD 服务是 sakila 示例数据库中的一个业务，其业务逻辑如下：

1. 查询某张 DVD 的库存

```
mysql> SELECT inventory_in_stock(10);
+------------------------+
| inventory_in_stock(10) |
+------------------------+
|                      1 |
+------------------------+
1 row in set (0.01 sec)
```

2. 添加一条租赁记录（向 rental 表中插入记录）

```
mysql> INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id) 
           VALUES(NOW(), 10, 3, 1);
Query OK, 1 row affected (0.00 sec)
```

3. 获取租赁记录的主键（自增主键）以及顾客的费用

```
mysql> SET @rentID = LAST_INSERT_ID(),
                  @balance = get_customer_balance(3, NOW());
Query OK, 0 rows affected (0.14 sec)

mysql> SELECT @rentID, @balance;
+---------+----------+
| @rentID | @balance |
+---------+----------+
|   16050 |     4.99 |
+---------+----------+
1 row in set (0.00 sec)
```

4. 添加一条支付记录（向 payment 表中插入记录）

```
mysql> INSERT INTO payment (customer_id, staff_id, rental_id, amount,  payment_date)
           VALUES(3, 1, @rentID, @balance, NOW());
Query OK, 1 row affected (0.00 sec)
```

### 创建并测试 InventoryMapper 类（映射器）

查询某张 DVD 的库存时，可以通过示例数据库中的函数 inventory_in_stock()。  
调用此函数只需要一个参数，并且结果集只有一个数字，因此可以暂时省略 POJO 类。

1. 创建 InventoryMapper 类（映射器）
1. 创建测试类

### 创建并测试 Rental 类（POJO）及其映射器

1. 创建 Rental 类（POJO）
2. 创建 RentalMapper 类（映射器）
3. 创建测试类

需要注意自增主键的获取方式：

```xml
<!-- 
  https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#insert-update-和-delete
  rental 表使用了自增主键，参考官方文档，设置 useGeneratedKeys=”true”，
  然后把 keyProperty 设置为目标属性，之后，通过 rentalId 就可以取出主键的值。
  
  注意：虽然，取出的主键值可以放入 Map<String, Integer> 类型的 Map 中，
  但是，主键（rentalId）被取出后的默认类型为 BigInteger ，不能进行常规的类型转换：
  Integer rentalId = rentalMap.get("rentalId"); // 将抛出类型转换异常
 -->
<insert id="addRentalWithMap" useGeneratedKeys="true">
  INSERT INTO
      rental (rental_date, inventory_id, customer_id, staff_id)
  VALUES
      (
          NOW(), #{inventoryId}, #{customerId}, #{staffId}
      )
  <!-- 
    可以在这里添加 selectKey 元素，显式地控制 rentalId 的结果集类型。
   -->
  <selectKey keyProperty="rentalId" keyColumn="rental_id" resultType="int" order="AFTER">
    SELECT LAST_INSERT_ID()
  </selectKey>
</insert>
```

### 创建并测试 Payment 类（POJO）及其映射器

1. 创建 Payment 类（POJO）
2. 创建 PaymentMapper 类（映射器）
3. 创建测试类

在此映射器中，实现获取顾客的费用处理和添加支付记录处理。

```xml
<!-- 获取顾客的费用 -->
<select id="getCustomerBalance" resultType="decimal">
  SELECT get_customer_balance(#{customerId}, NOW())
</select>

<!-- 添加支付记录 -->
<insert id="addPaymentWithMap" useGeneratedKeys="true">
  INSERT INTO
      payment (customer_id, staff_id, rental_id, amount, payment_date)
  VALUES
      (
          #{customerId}, #{staffId}, #{rentalId}, #{amount}, NOW()
      )
  <selectKey keyProperty="paymentId" keyColumn="payment_id" resultType="int" order="AFTER">
    SELECT LAST_INSERT_ID()
  </selectKey>
</insert>
```

## 其他

### 使用 Log4j 2 日志工厂

#### 导入依赖

https://logging.apache.org/log4j/2.x/maven-artifacts.html

```xml
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-api</artifactId>
  <version>2.18.0</version>
</dependency>
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.18.0</version>
</dependency>
```

#### 配置 Log4j 2

根据 MyBatis 官方文档中的相关介绍配置 Log4j 2。  
https://mybatis.org/mybatis-3/zh/logging.html#

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns="http://logging.apache.org/log4j/2.0/config">
  <!-- 
    暂时需要将该文件放置在 resources 文件夹（类路径）下，否则配置不生效
    学习Maven的配置后，将该文件放在 resources 文件夹的子文件夹中
   -->

  <Appenders>
    <Console name="stdout" target="SYSTEM_OUT">
      <!-- ANSI Styled -->
      <PatternLayout pattern="%d %highlight{%p} %style{%C{1.} [%t] %m}{bold,green}%n" />
    </Console>
  </Appenders>

  <Loggers>
    <Root level="debug">
      <AppenderRef ref="stdout" />
    </Root>
  </Loggers>
</Configuration>
```
