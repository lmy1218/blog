package com.lmy.entity;
/**
 * @Project blog
 * @Package com.lmy.entity
 * @author lmy
 * @date 2020/3/15 22:11
 * @version V1.0
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmy
 * @ClassName Type
 * @Description 博客分类
 * @date 2020/3/15 22:11
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {
    // id
    @Id
    @GeneratedValue
    private Long id;
    // 分类名
    @NotBlank(message = "分类名称不能为空")
    private String name;

    // 博客
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}
