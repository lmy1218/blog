package com.lmy.service;

import com.lmy.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.service
 * @date 2020/3/16 21:51
 */
public interface TypeService {
    // 新增分类
    Type saveType(Type type);

    // 根据id查询
    Type getType(Long id);

    // 根据名称查询
    Type getTypeByName(String name);

    // 分页查询
    Page<Type> listType(Pageable pageable);

    // 查询所有分类
    List<Type> getAllType();

    List<Type> getTypeTop(Integer size);

    // 修改
    Type updateType(Long id, Type type);

    // 删除
    void deleteType(Long id);
}
