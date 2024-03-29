package com.xingu.xg.controller;


import com.xingu.xg.common.consts.ComRT;
import com.xingu.xg.common.consts.ComResult;
import com.xingu.xg.entity.Menu;
import com.xingu.xg.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiBaoJe
 * @since 2024-03-28
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务
     */
    @Autowired
    private MenuService menuService;

    /**
     * 新增
     */
    @PostMapping("add")
    public ComResult<String> add(@RequestBody Menu menu) {
        boolean res = menuService.add(menu);
        return res ? ComRT.success("新增成功") : ComRT.fail(500, "新增失败");
    }

    /**
     * 删除
     */
    @PostMapping("delete")
    public ComResult<String> delete(String id) {
        boolean res = menuService.deleteById(id);
        return res ? ComRT.success("删除成功") : ComRT.fail(500, "删除失败");
    }

    /**
     * 复制
     */
    @PostMapping("copy")
    public ComResult<String> copy(@RequestBody Menu menu) {
        boolean res = menuService.copy(menu);
        return res ? ComRT.success("粘贴成功") : ComRT.fail(500, "粘贴失败");
    }

    /**
     * 查询节点
     */
    @GetMapping("queryList")
    public ComResult<List<Menu>> queryList() {
        return ComRT.success(menuService.queryList());
    }
}

