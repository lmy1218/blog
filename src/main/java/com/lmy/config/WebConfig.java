package com.lmy.config;
/**
 * @Project blog
 * @Package com.lmy.config
 * @author lmy
 * @date 2020/3/16 21:28
 * @version V1.0
 */

import com.lmy.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmy
 * @ClassName WebConfig
 * @Description Url配置类
 * @date 2020/3/16 21:28
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
