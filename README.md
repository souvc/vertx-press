# vertx-press
本项目是为了满足个人博客版本 v3.0 而开发的博客系统，以 wordpress 为蓝本，采用 Java 语言进行编写的。

亦是业余练手之作。

## vertx-manage

管理模块

### 如何运行

	1. cd ../vertx-press
	2. mvn clean install
	3. vertx run io.vertPress.manage.ManageServer -cp vertx-manage/target/vertx-manage-0.0.1.jar

### 初始化模块

1. 初始化数据库表

	管理员账号：admin
	
	管理员密码：cxj123