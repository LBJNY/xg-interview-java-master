package com.xingu.xg.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author LiBaoJe
 * @since 2024-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 默认0，顶级节点
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;


    /**
     * 新增类型 1：上 2：下 3：子集(默认添加到子集最后) 4:增加父级(原来位置上)
     */
    @TableField(exist = false)
    private Integer insertType;

    /**
     * 当前数据id
     */
    @TableField(exist = false)
    private String currentId;

    /**
     * 复制的元数据id
     */
    @TableField(exist = false)
    private String copyId;

    /**
     * 子数据集
     */
    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 新id
     */
    @TableField(exist = false)
    private String newId;

    /**
     * 新parentId
     */
    @TableField(exist = false)
    private String newParentId;

    /**
     * 粘贴到节点下
     */
    @TableField(exist = false)
    private String toId;
}
