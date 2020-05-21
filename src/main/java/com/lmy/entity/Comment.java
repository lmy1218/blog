package com.lmy.entity;
/**
 * @Project blog
 * @Package com.lmy.entity
 * @author lmy
 * @date 2020/3/15 22:16
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
 * @ClassName Comment
 * @Description  评论类
 * @date 2020/3/15 22:16
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_comment")
public class Comment {
    // id
    @Id
    @GeneratedValue
    private Long id;
    // 昵称
    private String nickname;
    // 邮箱
    private String email;
    // 评论内容
    private String content;
    // 头像
    private String avatar;
    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // 博客
    @ManyToOne
    private Blog blog;

    // 子评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    // 父评论
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;




}
