package com.lmy.exception;
/**
 * @Project blog
 * @Package com.lmy.exception
 * @author lmy
 * @date 2020/3/14 21:04
 * @version V1.0
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lmy
 * @ClassName LyException
 * @Description 异常处理日志记录
 * @date 2020/3/14 21:04
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LyException extends RuntimeException{
    public LyException() {
    }

    public LyException(String message) {
        super(message);
    }

    public LyException(String message, Throwable cause) {
        super(message, cause);
    }
}
