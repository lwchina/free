package com.rabbit.free.vo;

import lombok.Builder;
import lombok.Data;

/**
 * Created by wei.liu on 2017/5/15.
 */
@Builder  //会将构造器私有化，注意@RequestBody会失效
@Data
public class ExceptionVo {

  private int status;

  private String message;
}
