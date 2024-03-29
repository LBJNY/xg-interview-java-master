package com.xingu.xg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author luo
 * @date 2022/8/8
 * @description 启动类
 */

/**
 * 参数及用法
 * <p>
 * 复制 http://localhost:9400/api/menu/copy
 * {
 * "copyId":"复制的",
 * "toId":"粘贴到"
 * }
 * <p>
 * 查询 http://localhost:9400/api/menu/queryList
 * <p>
 * 新增  http://localhost:9400/api/menu/add
 * <p>
 *     顶级新增
 * {
 * "name":"新增名称"
 * }
 *     同级新增-上
 * {
 * "name":"新增名称",
 * "insertType":1 (增加类型实体中有描写),
 * "currentId":"当前需要向上新增的节点id"
 * }
 * 同级新增-下
 * {
 *     "name":"新增名称",
 *     "insertType":2,
 *     "currentId":"当前需要下新增子级的节点id"
 * }
 * 新增子级名
 * {
 * "name":"新增子级名称",
 * "insertType":3 (增加类型实体中有描写),
 * "currentId":"当前需要新增子级的节点id"
 * }
 * 新增父级
 * {
 * "name":"新增父级名称",
 * "insertType": 4  (增加类型实体中有描写),
 * "currentId":"当前需要新增父级的节点id"
 * }
 */
@SpringBootApplication//自动配置类
@MapperScan("com.xingu.xg.mapper")//扫描dao层
@ServletComponentScan
@EnableOpenApi //开启swagger3
public class XgJavaV2Application {

    public static void main(String[] args) {
        SpringApplication.run(XgJavaV2Application.class, args);
    }
}
