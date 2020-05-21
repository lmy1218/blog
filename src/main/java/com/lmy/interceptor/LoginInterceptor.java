package com.lmy.interceptor;
/**
 * @Project blog
 * @Package com.lmy.interceptor
 * @author lmy
 * @date 2020/3/16 21:23
 * @version V1.0
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmy
 * @ClassName LoginInterceptor
 * @Description 登录拦截器
 * @date 2020/3/16 21:23
 **/

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
