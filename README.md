# README

本项目是为了满足个人博客版本 v3.0 而开发的博客系统，以 wordpress 为蓝本，采用 Java 语言进行编写的。

本项目也是业余练手之作。

----------

# 项目架构

## 采用技术

Vertx: 基于JVM、轻量级、高性能的

Mysql: 关系型数据库管理系统

Freemarker：模板引擎

Bootstrap: 样式框架

JQuery: javascript 框架

html5 & CSS

## 数据库设计

参考 wordpress 数据库

| Tables        | Description           | 
| ------------- |:-------------:|
| wp_commentmeta	| 文章评论元信息|
| wp_comments	| 文章评论信息|
| wp_links	| 文章链接|
| wp_options	| 配置|
| wp_postmeta	| 文章元信息|
| wp_posts	| 文章信息|
| wp_terms	| 文章分类、链接分类、标签|
| wp_term_relationships	| 分类与文章信息、链接的关联|
| wp_term_taxonomy	| 分类信息|
| wp_usermeta	| 用户元信息|
| wp_users	| 用户列表|

### Table: wp_commentmeta

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|meta_id	|bigint(20)	|	|每个元信息的唯一ID号|
|comment_id	|bigint(20)	|	|每个元信息对应的评论的ID号|
|meta_key	|varchar(255)	|	|每个元信息的关键字|
|meta_value	|longtext	|	|每个元信息的详细值|



### Table: wp_comments

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|comment_ID	|bigint(20)		||每个评论的唯一ID号|
|comment_post_ID	|bigint(20)	||	每个评论对应的文章的ID号|
|comment_author	|tinytext	||	每个评论的评论者名称|
|comment_author_email	|varchar(200)	||	每个评论的评论者电邮地址|
|comment_author_url	|varchar(200)	||	每个评论的评论者网址|
|comment_author_IP	|varchar(100)	||	每个评论的评论者的IP地址|
|comment_date	|datetime	||	每个评论发表的时间|
|comment_date_gmt|	datetime	||	每个评论发表的时间|
|comment_content	|text	||	每个评论的具体内容|
|comment_approved	|varchar(20)| '0','1','spam'|		每个评论的当前状态|
|comment_agent	|varchar(255)	||	每个评论的评论者的客户端信息|
|comment_type	|varchar(20)	||	每个评论的类型|
|comment_parent	|bigint(20)	||	某一评论的上级评论|
|user_id	|bigint(20)	||	某一评论对应的用户ID|

### Table: wp_links

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|link_id|	bigint(20)	|	|每个链接的唯一ID号|
|link_url	|varchar(255)|		|每个链接的URL地址|
|link_name	|varchar(255)|		|每个链接的名字|
|link_image	|varchar(255)|		|每个链接的图片链接|
|link_target	|varchar(25)	|'_blank'，'_top','none'|	每个链接的打开方式|
|link_category	|varchar(255)|		|每个链接对应的链接分类|
|link_description	|varchar(255)|		|每个链接的说明文字|
|link_visible	|varchar(20)|	'Y','N'	|每个链接是否可用|
|link_owner|	bigint(20)|	1	|每个链接的创建人|
|link_rating	|int(11)	|0	|每个链接的等级|
|link_updated	|datetime|		|每个链接被修改的时间|
|link_notes	|mediumtext|		|每个链接的详细说明|
|link_rss	|varchar(255)	|	|每个链接的RSS地址|
	 	 	 	 

### Table: wp_options

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|option_id|	bigint(20)|	|	每个配置的唯一ID号|
|option_name|	varchar(64)	|	|每个配置的名称|
|option_value|	longtext|		|每个配置的值|
|autoload	|varchar(20)	|	|某个配置是否自动加载|



### Table: wp_postmeta

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|meta_id|	bigint(20)|		|每个元信息的唯一ID号|
|post_id|	bigint(20)|		|每个元信息对应文章的ID号|
|meta_key|	varchar(255)|		|某个元信息关键字|
|meta_value|	longtext|		|某个元信息详细值|


### Table: wp_posts
	 
|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
ID	|bigint(20)|	|	每个文章的唯一ID号|
|post_author	|bigint(20)	|	|每个文章对应用户的ID号|
|post_date|	datetime|	|	每个文章的创建时间|
|post_date_gmt	|datetime	|	|每个文章的创建时间GMT|
|post_content|	longtext	|	|每个文章的内容|
|post_title|	text|		|每个文章的标题|
|post_excerpt	|text	|	|每个文章的摘要|
|post_status	|varchar(20)|	|	每个文章的状态|
|comment_status	|varchar(20)	|	|每个文章的评论状态|
|post_modified	|datetime	|	|每个文章的修改时间|
|post_modified_gmt|	datetime|		|每个文章的修改时间GMT|
|post_parent|	bigint(20)|	0	|每个文章的上级文章ID号|
|guid	|varchar(255)|	|	每个文章的地址|
|post_type|	varchar(20)	|	|每个文章的类型|
|comment_count	|bigint(20)|	0	|每个文章的评论计数|

### Table: wp_terms

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|term_id	|bigint(20)	|	|每个分类的唯一ID号|
|name	|varchar(200)	|	|每个分类的名称|
|slug	|varchar(200)	|	|每个分类的缩略名|
|term_group	|bigint(10)|	0|	每个分离的组别|

### Table: wp_term_taxonomy

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|term_taxonomy_id	|bigint(20)	|	|每个分类方法的唯一ID号|
|term_id	|bigint(20)||每个分类方法对应的分类ID号|
|taxonomy*|	varchar(32)	|	|每个分类方法的类型|
|description	|longtext	|	|每个分类方法的描述|
|parent|	bigint(20)|	0	|每个分类方法的上级分类方法ID号|
|count	|bigint(20)|	0|	每个分类方法的文章数统计|
taxonomy：category 文章类别，nav_menu 导航栏，link_category，post_format，post_tag


### Table: wp_term_relationships

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|object_id	|bigint(20)|		|每个文章的唯一ID号|
|term_taxonomy_id	|bigint(20)|		|每个文章对应的分类方法ID号|
|term_order	|int(11)	|0	|每个方法对应的分类方法排序|


### Table: wp_usermeta

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|umeta_id|	bigint(20)|	|	每个元数据的唯一ID号|
|user_id|	bigint(20)|	0	|每个元数据对应的用户ID号|
|meta_key|	varchar(255)|		|每个元信息的关键字|
|meta_value|	longtext|		|每个元信息的详细值|

### Table: wp_users

|Field|	Type|	Default|	Description|
|-------------|	:-------------:|	:-------------:|	:-------------:|
|ID	|bigint(20)||每个用户的唯一ID号|
|user_login	|varchar(60)	|	|每个用户的注册名称|
|user_pass|	varchar(64)	|	|每个用户的密码|
|user_nicename	|varchar(50)|		|每个用户的昵称|
|user_email|	varchar(100)	|	|每个用户的电邮地址|
|user_url	|varchar(100)|		|每个用户的网址|
|user_registered	|datetime	|	|每个用户的注册时间|
|user_activation_key	|varchar(60)	|	|每个用户的激活码|
|user_status	|int(11)|	0|	每个用户的状态|
