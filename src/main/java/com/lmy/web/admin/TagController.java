package com.lmy.web.admin;
/**
 * @Project blog
 * @Package com.lmy.web.admin
 * @author lmy
 * @date 2020/3/18 21:08
 * @version V1.0
 */

import com.lmy.entity.Tag;
import com.lmy.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author lmy
 * @ClassName TagController
 * @Description 标签管理控制器
 * @date 2020/3/18 21:08
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagServiceImpl;

    // 分页查询标签
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 6, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", tagServiceImpl.listTag(pageable));
        log.info("【标签】分页查询成功");
        return "admin/tags";
    }


    // 显示新增页
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());;
        return "admin/tags-input";
    }


    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagServiceImpl.getTagById(id));
        return "admin/tags-input";
    }


    // 新增
    @PostMapping("/tags")
    public String insertTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if (tagByName != null) {
            result.rejectValue("name", "nameError", "该标签已存在");
            log.info("【标签】标签新增失败, 该标签已存在");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag reTag = tagServiceImpl.saveTag(tag);
        if (reTag == null) {
            attributes.addFlashAttribute("message", "新增失败");
            log.info("【标签】 新增失败，服务器忙");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
            log.info("【标签】 新增成功");
        }
        return "redirect:/admin/tags";

    }



    // 修改
    @PostMapping("/tags/{id}")
    public String updateTag(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if (tagByName != null) {
            result.rejectValue("name", "nameError", "该标签已存在");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag reTag = tagServiceImpl.updateTag(id, tag);
        if (reTag == null) {
            log.info("【标签】 修改失败，服务器忙");
            attributes.addFlashAttribute("message", "更新失败");
        }else {
            log.info("【标签】 修改失败，服务器忙");
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }


    // 删除
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attributes) {
        tagServiceImpl.deleteTag(id);
        log.info("【标签】 删除成功");
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
