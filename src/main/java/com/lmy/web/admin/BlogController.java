package com.lmy.web.admin;
/**
 * @Project blog
 * @Package com.lmy.web.admin
 * @author lmy
 * @date 2020/3/16 21:15
 * @version V1.0
 */

import com.lmy.entity.Blog;
import com.lmy.entity.User;
import com.lmy.service.BlogService;
import com.lmy.service.TagService;
import com.lmy.service.TypeService;
import com.lmy.vo.BlogQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author lmy
 * @ClassName BlogController
 * @Description 后台管理页面控制器
 * @date 2020/3/16 21:15
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private TagService tagServiceImpl;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, blogQuery));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery, Model model) {
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, blogQuery));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAnfTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT ;
    }

    private void setTypeAnfTag(Model model) {
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("tags", tagServiceImpl.getTagAll());
    }


    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAnfTag(model);
        Blog blog = blogServiceImpl.getBlogById(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT ;
    }


    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeServiceImpl.getType(blog.getType().getId()));
        blog.setTags(tagServiceImpl.getTagsByIds(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b = blogServiceImpl.saveBlog(blog);
        }else {
            b = blogServiceImpl.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            log.info("【博客】新增失败");
            attributes.addFlashAttribute("message", blog.getId() ==  null ? "新增失败" : "更新失败");
        }else {
            log.info("【博客】 新增成功");
            attributes.addFlashAttribute("message", blog.getId() == null ? "新增成功" : "更新成功");
        }
        return REDIRECT_LIST;
    }


    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        blogServiceImpl.deleteBlogById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }


}
