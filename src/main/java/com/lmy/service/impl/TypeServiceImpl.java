package com.lmy.service.impl;
/**
 * @Project blog
 * @Package com.lmy.service.impl
 * @author lmy
 * @date 2020/3/16 21:54
 * @version V1.0
 */

import com.lmy.dao.TypeRepository;
import com.lmy.entity.Type;
import com.lmy.exception.LyException;
import com.lmy.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lmy
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @date 2020/3/16 21:54
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

//    @Override
//    public List<Type> getTypeTop(Integer size) {
//        return typeRepository.findTop(size);
//    }
//
    @Override
    public List<Type> getTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type oldType = typeRepository.getOne(id);
        if (oldType == null) {
            throw new LyException("该类型不存在");
        }
        BeanUtils.copyProperties(type, oldType);
        return typeRepository.save(oldType);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
