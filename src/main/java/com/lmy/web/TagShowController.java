package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/25 20:55
 * @version V1.0
 */

import com.lmy.entity.Tag;
import com.lmy.service.BlogService;
import com.lmy.service.TagService;
import com.lmy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author lmy
 * @ClassName TagController
 * @Description 前台分类展示
 * @date 2020/3/25 20:55
 **/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Tag> tags = tagServiceImpl.getTagTop(1000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogServiceImpl.getAllBlogByTagId(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }




}
