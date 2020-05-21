package com.lmy.service.impl;
/**
 * @Project blog
 * @Package com.lmy.service.impl
 * @author lmy
 * @date 2020/3/16 14:42
 * @version V1.0
 */

import com.lmy.dao.UserRepository;
import com.lmy.entity.User;
import com.lmy.service.UserService;
import com.lmy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmy
 * @ClassName UserServiceImpl
 * @Description 用户登录处理
 * @date 2020/3/16 14:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // 用户校验
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
