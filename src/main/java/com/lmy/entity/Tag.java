package com.lmy.entity;
/**
 * @Project blog
 * @Package com.lmy.entity
 * @author lmy
 * @date 2020/3/15 22:13
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
 * @ClassName Tag
 * @Description 标签类
 * @date 2020/3/15 22:13
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_tag")
public class Tag {
    // id
    @Id
    @GeneratedValue
    private Long id;
    // 标签名
    @NotBlank(message = "标签名称不能为空")
    private String name;

    // 博客
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

}
