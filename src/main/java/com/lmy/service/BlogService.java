package com.lmy.service;

import com.lmy.entity.Blog;
import com.lmy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.service
 * @date 2020/3/19 20:15
 */
public interface BlogService {
    // 根据ID查询
    Blog getBlogById(Long id);

    // 分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> getAllPageBlog(Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> getBlogByQuery(String query, Pageable pageable);

    Page<Blog> getAllBlogByTagId(Long tagId, Pageable pageable);

    Blog getAndConvert(Long id);

    // 归档年份
    Map<String, List<Blog>> getArchiveBlog();

    Long countBlog();

    // 新增
    Blog saveBlog(Blog blog);

    // 修改
    Blog updateBlog(Long id, Blog blog);

    // 删除
    void deleteBlogById(Long id);
}
