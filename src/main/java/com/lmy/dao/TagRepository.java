package com.lmy.dao;
/**
 * @Project blog
 * @Package com.lmy.dao
 * @author lmy
 * @date 2020/3/18 20:50
 * @version V1.0
 */

import com.lmy.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lmy
 * @ClassName TagRepository
 * @Description 便签数据层
 * @date 2020/3/18 20:50
 **/

public interface TagRepository extends JpaRepository<Tag, Long> {
    // 根据名称查询标签
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
