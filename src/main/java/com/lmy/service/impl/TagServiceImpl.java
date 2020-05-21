package com.lmy.service.impl;
/**
 * @Project blog
 * @Package com.lmy.service.impl
 * @author lmy
 * @date 2020/3/18 20:57
 * @version V1.0
 */

import com.lmy.dao.TagRepository;
import com.lmy.entity.Tag;
import com.lmy.exception.LyException;
import com.lmy.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmy
 * @ClassName TagServiceImpl
 * @Description 标签业务层
 * @date 2020/3/18 20:57
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    // 新增标签
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // 根据ID查询
    @Override
    public Tag getTagById(Long id) {
        return tagRepository.getOne(id);
    }

    // 根据名称查询
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }


    // 分页查询
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> getTagAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getTagsByIds(String ids) {
        return tagRepository.findAllById(converToList(ids));
    }

    @Override
    public List<Tag> getTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    // 将数字字符串转化为List集合
    private List<Long> converToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                list.add(new Long(idArray[i]));
            }
        }
        return list;
    }

    // 修改
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag oldTag = tagRepository.getOne(id);
        if (oldTag == null) {
            throw new LyException("该标签不存在");
        }
        BeanUtils.copyProperties(tag, oldTag);
        return tagRepository.save(oldTag);
    }

    // 删除
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
