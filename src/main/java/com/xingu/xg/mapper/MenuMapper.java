package com.xingu.xg.mapper;

import com.xingu.xg.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LiBaoJe
 * @since 2024-03-28
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 后移
     *
     * @param parentId           父ID
     * @param sort               序号
     * @param containCurrentSort 是否包含现在的序号
     * @return 结果
     */
    boolean retrusion(@Param("parentId") String parentId, @Param("sort") Integer sort,
                      @Param("containCurrentSort") boolean containCurrentSort);

    /**
     * 前移
     *
     * @param parentId           父ID
     * @param sort               序号
     * @param containCurrentSort 是否包含现在的序号
     * @return 结果
     */
    boolean anteposition(@Param("parentId") String parentId, @Param("sort") Integer sort,
                         @Param("containCurrentSort") boolean containCurrentSort);


    /**
     * 递归查询
     *
     * @param copyId 复制的id
     * @return 子集
     */
    List<Menu> recursionSelectById(@Param("copyId") String copyId);

    /**
     * 根据父id查询排序最大的一条数据
     *
     * @param parentId 父id
     * @return 结果
     */
    Menu getMaxSortMenuByParentId(@Param("parentId") String parentId);
}
