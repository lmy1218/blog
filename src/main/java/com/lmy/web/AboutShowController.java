package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/27 16:55
 * @version V1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lmy
 * @ClassName AboutShowController
 * @Description 关于我控制器
 * @date 2020/3/27 16:55
 **/
@Controller
public class AboutShowController {



    @GetMapping("/about")
    public String about() {
        return "about";
    }





}
