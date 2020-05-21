package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/25 20:55
 * @version V1.0
 */

import com.lmy.entity.Type;
import com.lmy.service.BlogService;
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

import java.util.List;

/**
 * @author lmy
 * @ClassName TypeController
 * @Description 前台分类展示
 * @date 2020/3/25 20:55
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeServiceImpl.getTypeTop(1000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        model.addAttribute("types", types);
        model.addAttribute("page", blogServiceImpl.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }




}
