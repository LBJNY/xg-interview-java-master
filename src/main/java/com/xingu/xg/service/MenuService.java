package com.xingu.xg.service;

import com.xingu.xg.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiBaoJe
 * @since 2024-03-28
 */
public interface MenuService extends IService<Menu> {

    /**
     * 新增菜单
     *
     * @param menu 实体
     * @return 结果
     */
    boolean add(Menu menu);

    /**
     * 删除菜单
     *
     * @param id 要删除的id
     * @return 结果
     */
    boolean deleteById(String id);

    /**
     * 复制
     *
     * @param menu 相关信息
     * @return 结果
     */
    boolean copy(Menu menu);

    /**
     * 查询列表
     *
     * @return 结果
     */
    List<Menu> queryList();
}
