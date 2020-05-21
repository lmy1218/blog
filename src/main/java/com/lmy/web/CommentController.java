package com.lmy.web;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/23 21:29
 * @version V1.0
 */

import com.lmy.entity.Comment;
import com.lmy.entity.User;
import com.lmy.service.BlogService;
import com.lmy.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author lmy
 * @ClassName CommentController
 * @Description 评论功能控制器
 * @date 2020/3/23 21:29
 **/
@Slf4j
@Controller
public class CommentController {

    @Autowired
    private CommentService commentServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentServiceImpl.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogServiceImpl.getBlogById(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
//            comment.setNickname(user.getNickname());
        } else {
            comment.setAvatar(avatar);
        }
        commentServiceImpl.saveComment(comment);
        log.info("评论提交成功");
        return "redirect:/comments/" + comment.getBlog().getId();
    }


}
