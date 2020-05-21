package com.lmy.service;

import com.lmy.entity.Comment;

import java.util.List;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.service
 * @date 2020/3/23 21:34
 */
public interface CommentService {

    // 根据blogId 查询
    List<Comment> listCommentByBlogId(Long blogId);
    //保存
    Comment saveComment(Comment comment);

}
