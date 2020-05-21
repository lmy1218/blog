package com.lmy.dao;

import com.lmy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lmy
 * @version V1.0
 * @Project blog
 * @Package com.lmy.dao
 * @date 2020/3/16 14:45
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据用户名和密码查询用户
    User findByUsernameAndPassword(String username, String password);
}
