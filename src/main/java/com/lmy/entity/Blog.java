package com.lmy.entity;
/**
 * @Project blog
 * @Package com.lmy.entity
 * @author lmy
 * @date 2020/3/15 21:55
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
 * @ClassName Blog
 * @Description 博客类
 * @date 2020/3/15 21:55
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_blog")
public class Blog {
    // id
    @Id
    @GeneratedValue
    private Long id;
    // 标题
    private String title;
    // 内容
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    // 首图
    private String firstPicture;
    // 标记
    private String flag;
    // 浏览次数
    private Integer views;
    // 赞赏开启
    private boolean appreciation;
    // 版权开启
    private boolean shareStatement;
    // 评论开启
    private boolean commentabled;
    // 是否发布
    private boolean published;
    // 是否推荐
    private boolean recommend;
    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    // 更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    private String tagIds;

    private String description;

    // 类型
    @ManyToOne
    private Type type;
    // 标签
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    // 用户
    @ManyToOne
    private User user;
    // 评论
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public void init() {
        this.tagIds = tagsToIds(this.tags);
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }

}
