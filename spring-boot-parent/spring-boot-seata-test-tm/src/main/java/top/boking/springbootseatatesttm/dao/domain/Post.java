package top.boking.springbootseatatesttm.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 * @TableName post
 */
@Data
@TableName("post")
public class Post implements Serializable {
    /**
     * id
     */
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 点赞数
     */
    @TableField("thumbNum")
    private Integer thumbNum;

    /**
     * 收藏数
     */
    @TableField("favourNum")
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField("isDelete")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}