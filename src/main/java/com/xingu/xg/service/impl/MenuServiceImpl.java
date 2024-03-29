package com.xingu.xg.service.impl;

import com.xingu.xg.entity.Menu;
import com.xingu.xg.mapper.MenuMapper;
import com.xingu.xg.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiBaoJe
 * @since 2024-03-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 新增方法
     *
     * @param menu 实体
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(Menu menu) {
        //查询出当menu
        Menu currentMenu = null == menu.getCurrentId() ? new Menu() : baseMapper.selectById(menu.getCurrentId());
        Menu addMenu = new Menu();
        addMenu.setId(UUID.randomUUID().toString().replace("-", ""));
        addMenu.setName(menu.getName());
        if (null == menu.getInsertType()) {
            //默认添加顶级最后
            addMenu.setParentId("0");
            Menu lastSortMenu = baseMapper.getMaxSortMenuByParentId("0");
            addMenu.setSort(null == lastSortMenu || null == lastSortMenu.getSort() ? 1 : lastSortMenu.getSort() + 1);
        } else {
            switch (menu.getInsertType()) {
                case 1 -> {
                    //同级上
                    addMenu.setParentId(currentMenu.getParentId());
                    addMenu.setSort(1 == currentMenu.getSort() ? 1 : currentMenu.getSort());
                    //后移
                    baseMapper.retrusion(currentMenu.getParentId(), currentMenu.getSort(), true);
                }
                case 2 -> {
                    //同级下
                    addMenu.setParentId(currentMenu.getParentId());
                    addMenu.setSort(1 == currentMenu.getSort() ? 1 : currentMenu.getSort() + 1);
                    //后移
                    baseMapper.retrusion(currentMenu.getParentId(), currentMenu.getSort(), false);
                }
                case 3 -> {
                    //子集最后
                    addMenu.setParentId(currentMenu.getId());
                    Menu maxSortMenu = baseMapper.getMaxSortMenuByParentId(currentMenu.getId());
                    addMenu.setSort(null == maxSortMenu || null == maxSortMenu.getSort() ? 1 : maxSortMenu.getSort() + 1);
                }
                case 4 -> {
                    //添加父节点信息
                    addMenu.setName(menu.getName());
                    addMenu.setParentId(currentMenu.getParentId());
                    addMenu.setSort(currentMenu.getSort());
                    //更新被创建父节点的菜单
                    currentMenu.setParentId(addMenu.getId());
                    currentMenu.setSort(1);
                    baseMapper.updateById(currentMenu);
                }
                default -> {
                }
            }
        }
        return save(addMenu);
    }

    /**
     * 删除
     *
     * @param id 要删除的id
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(String id) {
        Menu menu = getById(id);
        //删除数据
        baseMapper.deleteById(id);
        //前移数据
        baseMapper.anteposition(menu.getParentId(), menu.getSort(), false);
        return true;
    }

    /**
     * 复制
     *
     * @param menu 相关信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean copy(Menu menu) {
        Menu maxSortMenu = baseMapper.getMaxSortMenuByParentId(null == menu.getToId()
                ? "0" : menu.getToId());
        //查询出需要copy的所有数据集
        List<Menu> list = baseMapper.recursionSelectById(menu.getCopyId());
        //如果是null 则跳过返回失败
        if (!CollectionUtils.isEmpty(list)) {
            //对结果进行新id设置
            list.forEach(item -> {
                item.setNewId(UUID.randomUUID().toString().replace("-", ""));
            });
            Map<String, Menu> menuMap = list.stream().collect(Collectors.toMap(Menu::getId, item -> item));
            list.forEach(item -> {
                Menu oldParent = menuMap.get(item.getParentId());
                //如果是复制的根节点将传递的要复制到的节点设置进去
                if (menu.getCopyId().equals(item.getId())) {
                    item.setParentId(menu.getToId());
                    item.setSort(null == maxSortMenu || null == maxSortMenu.getSort() ? 1 : maxSortMenu.getSort() + 1);
                } else {
                    item.setNewParentId(oldParent.getNewId());
                }
                item.setId(item.getNewId());
            });
            saveBatch(list);
            return true;
        }
        return false;
    }

    @Override
    public List<Menu> queryList() {
        List<Menu> list = lambdaQuery().list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //获取到顶级的所有数据
        List<Menu> returnList = list.stream().filter(item -> "0".equals(item.getParentId()))
                .sorted(Comparator.comparingInt(Menu::getSort)).toList();
        for (Menu menu : returnList) {
            recursionSetChildren(menu, list);
        }
        return list;
    }

    /**
     * 递归设置子集
     *
     * @param menu     父
     * @param menuList 总数据
     */
    private void recursionSetChildren(Menu menu, List<Menu> menuList) {
        List<Menu> childrenList = menuList.stream().filter(item -> menu.getId().equals(item.getParentId()))
                .sorted(Comparator.comparingInt(Menu::getSort)).toList();
        if (!CollectionUtils.isEmpty(childrenList)) {
            menu.setChildren(childrenList);
            for (Menu children : childrenList) {
                recursionSetChildren(children, menuList);
            }
        }
    }
}
