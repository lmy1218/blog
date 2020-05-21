package com.lmy.service;

import com.lmy.entity.User;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.service
 * @date 2020/3/16 14:41
 */
public interface UserService {
    // 用户校验
    public User checkUser(String username, String password);

}
