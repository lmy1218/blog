package com.lmy.dao;

import com.lmy.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.dao
 * @date 2020/3/23 21:37
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
