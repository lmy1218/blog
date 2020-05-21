package com.lmy.advice;
/**
 * @Project blog
 * @Package com.lmy.web
 * @author lmy
 * @date 2020/3/14 20:40
 * @version V1.0
 */


import com.lmy.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lmy
 * @ClassName ControllerExceptionHandler
 * @Description 全局异常处理控制器
 * @date 2020/3/14 20:40
 **/
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {



    @ExceptionHandler(LyException.class)
    public ModelAndView exceptionHandler (HttpServletRequest request, Exception e) throws Exception {

        log.error("Requst URL : {}, Exception : {}", request.getRequestURL(), e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }

}
