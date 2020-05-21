package com.lmy.web.admin;
/**
 * @Project blog
 * @Package com.lmy.web.admin
 * @author lmy
 * @date 2020/3/16 22:05
 * @version V1.0
 */

import com.lmy.entity.Type;
import com.lmy.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author lmy
 * @ClassName TypeController
 * @Description 分类管理控制器
 * @date 2020/3/16 22:05
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeServiceImpl;

    // 分类展示
    @GetMapping("/types")
    public String types(@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        // 分页查询
        model.addAttribute("page", typeServiceImpl.listType(pageable));
        return "admin/types";
    }

    // 显示新增页
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    // 新增分类
    @PostMapping("/types")
    public String save(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type reType = typeServiceImpl.saveType(type);
        if (reType == null) {
            log.info("【分类】新增失败");
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            log.info("【分类】 新增成功");
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    // 显示修改页
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeServiceImpl.getType(id));
        return "admin/types-input";
    }



    // 修改分类
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type reType = typeServiceImpl.updateType(id, type);
        if (reType == null) {
            log.info("【分类】更新失败");
            attributes.addFlashAttribute("message", "更新失败");
        }else {
            log.info("【分类】 更新成功");
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
        typeServiceImpl.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
