package com.lmy.vo;
/**
 * @Project blog
 * @Package com.lmy.vo
 * @author lmy
 * @date 2020/3/19 21:19
 * @version V1.0
 */

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmy
 * @ClassName BlogQuery
 * @Description 博客查询条件
 * @date 2020/3/19 21:19
 **/
@Data
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;


}
