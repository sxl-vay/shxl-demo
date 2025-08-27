# Spring Boot Web Test 项目

## 项目简介
这是一个基于Spring Boot + MyBatis-Plus的Web测试项目，实现了班级信息管理和学生信息管理的完整CRUD功能。

## 技术栈
- Spring Boot
- MyBatis-Plus
- MySQL
- Lombok
- Spring Web

## 项目结构
```
src/main/java/top/boking/webtest/transation/
├── controller/          # 控制器层
│   ├── ShxlClassController.java
│   └── StudentController.java
├── service/            # 服务层
│   ├── ShxlClassService.java
│   ├── StudentService.java
│   └── impl/
│       ├── ShxlClassServiceImpl.java
│       └── StudentServiceImpl.java
├── mapper/             # 数据访问层
│   ├── ShxlClassMapper.java
│   └── StudentMapper.java
├── domain/             # 实体类
│   ├── ShxlClass.java
│   └── Student.java
└── common/             # 公共类
    └── Result.java

src/main/resources/
├── mapper/             # MyBatis XML配置
│   ├── ShxlClassMapper.xml
│   └── StudentMapper.xml
└── sql/                # SQL脚本
    ├── shxl_class.sql
    └── student.sql
```

## 数据库设计

### 班级信息表 (shxl_class)
| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | bigint | 20 | NO | AUTO_INCREMENT | 主键ID |
| name | varchar | 100 | NO | - | 班级名称 |
| class_no | varchar | 50 | NO | - | 班级编号 |
| create_time | varchar | 20 | YES | NULL | 创建时间 |
| update_time | varchar | 20 | YES | NULL | 更新时间 |
| is_deleted | tinyint | 1 | YES | 0 | 是否删除 |

### 学生信息表 (student)
| 字段名 | 类型 | 长度 | 是否为空 | 默认值 | 说明 |
|--------|------|------|----------|--------|------|
| id | bigint | 20 | NO | AUTO_INCREMENT | 主键ID |
| name | varchar | 100 | NO | - | 学生姓名 |
| class_id | bigint | 20 | NO | - | 班级ID |
| student_no | varchar | 50 | NO | - | 学号 |
| create_time | varchar | 20 | YES | NULL | 创建时间 |
| update_time | varchar | 20 | YES | NULL | 更新时间 |
| is_deleted | tinyint | 1 | YES | 0 | 是否删除 |

## API接口

### 班级管理接口

#### 1. 分页查询班级列表
- **GET** `/api/class/list`
- **参数**: 
  - `current`: 当前页码 (默认: 1)
  - `size`: 每页大小 (默认: 10)
  - `name`: 班级名称 (可选，模糊查询)

#### 2. 根据ID查询班级
- **GET** `/api/class/{id}`

#### 3. 新增班级
- **POST** `/api/class`
- **请求体**: ShxlClass对象

#### 4. 更新班级信息
- **PUT** `/api/class/{id}`
- **请求体**: ShxlClass对象

#### 5. 删除班级
- **DELETE** `/api/class/{id}`

#### 6. 查询所有班级
- **GET** `/api/class/all`

### 学生管理接口

#### 1. 分页查询学生列表
- **GET** `/api/student/list`
- **参数**: 
  - `current`: 当前页码 (默认: 1)
  - `size`: 每页大小 (默认: 10)
  - `name`: 学生姓名 (可选，模糊查询)
  - `classId`: 班级ID (可选)

#### 2. 根据ID查询学生
- **GET** `/api/student/{id}`

#### 3. 新增学生
- **POST** `/api/student`
- **请求体**: Student对象

#### 4. 更新学生信息
- **PUT** `/api/student/{id}`
- **请求体**: Student对象

#### 5. 删除学生
- **DELETE** `/api/student/{id}`

#### 6. 查询所有学生
- **GET** `/api/student/all`

#### 7. 根据班级ID查询学生
- **GET** `/api/student/class/{classId}`

#### 8. 根据学号查询学生
- **GET** `/api/student/no/{studentNo}`

## 使用说明

### 1. 数据库配置
在 `application.yml` 中配置数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=utf8&useSSL=false
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 2. 创建数据库表
执行以下SQL文件中的建表语句：
- `src/main/resources/sql/shxl_class.sql` - 班级表
- `src/main/resources/sql/student.sql` - 学生表

### 3. 启动应用
运行主类启动Spring Boot应用。

## 特性
- 基于MyBatis-Plus的CRUD操作
- 统一响应结果封装
- 逻辑删除支持
- 分页查询支持
- RESTful API设计
- 完整的异常处理
- 支持按班级查询学生
- 支持按学号查询学生

## 注意事项
- 确保MySQL服务已启动
- 检查数据库连接配置
- 确保Lombok依赖已添加
- 建议使用Postman或类似工具测试API接口
- 学生表与班级表通过class_id字段关联 