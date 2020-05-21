package com.lmy.entity;
/**
 * @Project blog
 * @Package com.lmy.entity
 * @author lmy
 * @date 2020/3/15 22:22
 * @version V1.0
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lmy
 * @ClassName User
 * @Description 用户类
 * @date 2020/3/15 22:22
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    // id
    @Id
    @GeneratedValue
    private Long id;
    // 昵称
    private String nickname;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 头像
    private String avatar;
    // 类型
    private Integer type;
    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    // 更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // 博客
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();


}
