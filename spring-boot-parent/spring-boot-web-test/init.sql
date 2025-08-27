-- 创建班级信息表
CREATE TABLE `shxl_class`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(100) NOT NULL COMMENT '班级名称',
    `class_no`    varchar(50)  NOT NULL COMMENT '班级编号',
    `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
    `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1)  DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_class_no` (`class_no`),
    KEY `idx_name` (`name`),
    KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='班级信息表';

-- 插入测试数据
INSERT INTO `shxl_class` (`name`, `class_no`, `create_time`, `update_time`, `is_deleted`)
VALUES ('计算机科学与技术1班', 'CS001', '2025-01-27 10:00:00', '2025-01-27 10:00:00', 0),
       ('软件工程1班', 'SE001', '2025-01-27 10:00:00', '2025-01-27 10:00:00', 0),
       ('数据科学与大数据技术1班', 'DS001', '2025-01-27 10:00:00', '2025-01-27 10:00:00', 0),
       ('人工智能1班', 'AI001', '2025-01-27 10:00:00', '2025-01-27 10:00:00', 0);


-- 学生信息表
CREATE TABLE `student`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(100) NOT NULL COMMENT '学生姓名',
    `class_id`    bigint(20)   NOT NULL COMMENT '班级ID',
    `student_no`  varchar(50)  NOT NULL COMMENT '学号',
    `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
    `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  tinyint(1)  DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_no` (`student_no`),
    KEY `idx_class_id` (`class_id`),
    KEY `idx_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='学生信息表';

-- 插入测试数据
INSERT INTO `student` (`name`, `class_id`, `student_no`, `create_time`, `update_time`, `is_deleted`)
VALUES ('张三', 1, '2024001', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
       ('李四', 1, '2024002', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
       ('王五', 2, '2024003', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
       ('赵六', 2, '2024004', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
       ('孙七', 3, '2024005', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0);