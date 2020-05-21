package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/26 21:30
 * @version V1.0
 */

import com.lmy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lmy
 * @ClassName ArchiveShowController
 * @Description 归档控制器
 * @date 2020/3/26 21:30
 **/
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogServiceImpl.getArchiveBlog());
        model.addAttribute("blogCount", blogServiceImpl.countBlog());
        return "archives";
    }

}
