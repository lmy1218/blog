package com.lmy.service;

import com.lmy.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.service
 * @date 2020/3/18 20:52
 */
public interface TagService {
    // 新增
    Tag saveTag(Tag tag);

    // 根据id查询
    Tag getTagById(Long id);

    // 根据名称查询
    Tag getTagByName(String name);

    // 分页查询所有标签
    Page<Tag> listTag(Pageable pageable);

    // 查询所有
    List<Tag> getTagAll();

    // 根据ids查询
    List<Tag> getTagsByIds(String ids);

    List<Tag> getTagTop(Integer size);

    // 修改
    Tag updateTag(Long id, Tag tag);

    // 删除
    void deleteTag(Long id);

}
