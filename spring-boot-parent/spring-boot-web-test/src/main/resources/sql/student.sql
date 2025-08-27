-- 学生信息表
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '学生姓名',
  `class_id` bigint(20) NOT NULL COMMENT '班级ID',
  `student_no` varchar(50) NOT NULL COMMENT '学号',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_no` (`student_no`),
  KEY `idx_class_id` (`class_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

-- 插入测试数据
INSERT INTO `student` (`name`, `class_id`, `student_no`, `create_time`, `update_time`, `is_deleted`) VALUES
('张三', 1, '2024001', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
('李四', 1, '2024002', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
('王五', 2, '2024003', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
('赵六', 2, '2024004', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0),
('孙七', 3, '2024005', '2024-01-01 10:00:00', '2024-01-01 10:00:00', 0); 