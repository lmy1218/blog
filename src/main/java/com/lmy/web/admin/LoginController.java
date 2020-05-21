package com.lmy.web.admin;
/**
 * @Project blog
 * @Package com.lmy.web.admin
 * @author lmy
 * @date 2020/3/16 14:51
 * @version V1.0
 */

import com.lmy.entity.User;
import com.lmy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author lmy
 * @ClassName LoginController
 * @Description 登录控制器
 * @date 2020/3/16 14:51
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userServiceImpl;

    // 登录页面
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "admin/index";
    }

    // 登录校验
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        log.info("用户校验开始");
        User user = userServiceImpl.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            log.info("用户校验成功");
            return "admin/index";
        }else {
            log.error("用户校验失败， 用户不存在, 用户名为: {}", username);
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    // 注销
    @GetMapping("/logout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
