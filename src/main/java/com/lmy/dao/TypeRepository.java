package com.lmy.dao;
/**
 * @Project blog
 * @Package com.lmy.dao
 * @author lmy
 * @date 2020/3/16 21:55
 * @version V1.0
 */

import com.lmy.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lmy
 * @ClassName TypeRepository
 * @Description TODO
 * @date 2020/3/16 21:55
 **/

public interface TypeRepository extends JpaRepository<Type, Long> {
    // 根据名称查询
    Type findByName(String name);

//    // 查询指定个数的分类
//    @Query("select t.* from t_blog b, t_type t WHERE b.type_id = t.id group by b.type_id order by count(b.type_id) desc limit 0,?")
//    List<Type> findTop(Integer size);

    // 查询指定个数的分类
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
