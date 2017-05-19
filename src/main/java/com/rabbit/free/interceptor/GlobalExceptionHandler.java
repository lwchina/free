package com.rabbit.free.interceptor;

import com.rabbit.free.vo.ExceptionVo;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by wei.liu on 2017/5/15.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private Logger logger = Logger.getLogger(this.getClass());

  /**
   * catch  Exception.
   * @param exception  exception
   * @param response  response
   * @return  response body
   */
  @ExceptionHandler(Exception.class)
  public Object exceptionHandler(Exception exception,HttpServletResponse response) {
    ExceptionVo exceptionVo = ExceptionVo.builder().status(
            HttpStatus.INTERNAL_SERVER_ERROR.value()).message("出错了,请稍后在试").build();
    logger.error("出错了",exception);
    return exceptionVo;
  }

  /**
   * catch  ParseException.
   * @param exception  exception
   * @param response  response
   * @return  response body
   */
  @ExceptionHandler(value = ParseException.class)
  public Object parseExceptionHandler(Exception exception, HttpServletResponse response) {
    ExceptionVo exceptionVo = ExceptionVo.builder().status(
            HttpStatus.INTERNAL_SERVER_ERROR.value()).message("日期格式错误,请检查参数信息").build();
    logger.error("日期格式错误",exception);
    return exceptionVo;
  }

  /**
   * catch  HttpRequestMethodNotSupportedException.
   * @param exception exception
   * @param response response
   * @return  response body
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public Object methodNotSupported(Exception exception, HttpServletResponse response) {
    ExceptionVo exceptionVo = ExceptionVo.builder().status(
            HttpStatus.METHOD_NOT_ALLOWED.value()).message("请求方法错误").build();
    logger.error("请求方法错误",exception);
    return exceptionVo;
  }
}
