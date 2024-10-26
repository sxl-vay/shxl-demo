# 后端demo整合工程
shxl-demo中定义了本模块所依赖的所有依赖的版本号
同时作为所有二级根模块的父模块（包含spring-boot-parent模块）
## 各个module介绍
### caffine-and-jetcache
本地缓存和redis缓存组成的二级缓存样例
### java-base
Java基础api样例包<br>
包含如下工程：
#### jdk-demo
java核心api
#### juc
Java并发工具集
### rabbitmq-test
java整合rabbitmq框架
### spring-boot-parent
spring-boot项目顶层模块
> 这里后期还是需要把shxl-demo的父依赖去除；或者自定义spring相关版本，否则很容易出现jar包冲突问题
### spring-bootstarter-test
自定义spring-starter工程