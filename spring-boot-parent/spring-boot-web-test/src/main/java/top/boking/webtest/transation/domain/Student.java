package top.boking.webtest.transation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 学生信息实体类
 * @version 1.0
 * @auther shxl
 * @date 2025/8/27 09:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
