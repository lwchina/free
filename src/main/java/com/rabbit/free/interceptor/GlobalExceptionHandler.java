package com.rabbit.free.interceptor;

import com.rabbit.free.vo.ExceptionVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * Created by wei.liu on 2017/5/15.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletResponse response) {
        ExceptionVO exceptionVO = ExceptionVO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("出错了,请稍后在试").build();
        return exceptionVO;
    }

    @ExceptionHandler(value = ParseException.class)
    public Object parseExceptionHandler(ParseException parseException, HttpServletResponse response) {
        ExceptionVO exceptionVO = ExceptionVO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("日期格式错误,请检查参数信息").build();
        return exceptionVO;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object methodNotSupported(HttpServletResponse response) {
        ExceptionVO exceptionVO = ExceptionVO.builder().status(HttpStatus.METHOD_NOT_ALLOWED.value()).message("请求方法错误").build();
        return exceptionVO;
    }
}
