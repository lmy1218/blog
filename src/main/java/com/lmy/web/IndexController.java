package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/14 20:29
 * @version V1.0
 */

import com.lmy.entity.Blog;
import com.lmy.service.BlogService;
import com.lmy.service.TagService;
import com.lmy.service.TypeService;
import com.lmy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lmy
 * @ClassName IndexController
 * @Description 首页显示
 * @date 2020/3/14 20:29
 **/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private TagService tagServiceImpl;


    // 资源初始化
    @GetMapping("/")
    public String index (@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        model.addAttribute("types", typeServiceImpl.getTypeTop(6));
        model.addAttribute("page", blogServiceImpl.getAllPageBlog(pageable));
        model.addAttribute("tags", tagServiceImpl.getTagTop(6));
        model.addAttribute("recommendBlogs", blogServiceImpl.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogServiceImpl.getBlogByQuery(query, pageable));

        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogServiceImpl.getAndConvert(id));
        return "blog";
    }


    @GetMapping("/footer/newblog")
    public String newBlogs(Model model) {

        model.addAttribute("newblogs", blogServiceImpl.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }


}
