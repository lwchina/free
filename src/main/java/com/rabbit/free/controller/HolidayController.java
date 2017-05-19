package com.rabbit.free.controller;

import com.rabbit.free.service.HolidayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by wei.liu on 2017/5/15.
 */
@RequestMapping("holiday")
@RestController
public class HolidayController {

  @Autowired
  private HolidayService holidayService;

  @ApiOperation(value = "获取放假日期",notes = "根据时间范围获取假日日期")
  @ApiImplicitParams({
        @ApiImplicitParam(name = "startDate", value = "查询起始日期(yyyy-MM-dd)", dataType = "String"),
        @ApiImplicitParam(name = "endDate", value = "查询截止日期(yyyy-MM-dd)", dataType = "String")
      })
  @RequestMapping(value = "getHolidays",method = RequestMethod.GET)
  public Object getHolidays(String startDate,String endDate) throws ParseException {
    long[] longs = getStartTimeAndEndTime(startDate,endDate);
    return holidayService.getHolidays(longs[0],longs[1]);
  }

  @ApiOperation(value = "获取节日放假日期",notes = "根据时间范围获取节日假日日期")
  @ApiImplicitParams({
        @ApiImplicitParam(name = "startDate", value = "查询起始日期(yyyy-MM-dd)", dataType = "String"),
        @ApiImplicitParam(name = "endDate", value = "查询截止日期(yyyy-MM-dd)", dataType = "String")
      })
  @ApiResponse(code = 200, message = "日期",
          responseHeaders = {@ResponseHeader(name = "application/json")})
  @RequestMapping(value = "getFestivalHolidays",method = RequestMethod.GET)
  public Object getFestivalHolidays(String startDate,String endDate) throws ParseException {
    long[] longs = getStartTimeAndEndTime(startDate,endDate);
    return holidayService.getFestivalHolidays(longs[0],longs[1]);
  }

  /**
   * 获取起始时间和截止时间的时间戳(整天) 参数都为空时，则返回今年第一天与最后一天的时间戳.
   * @param startDate 起始时间字符串(整天)
   * @param endDate   截止时间字符串(整天)
   * @return  long[]: long[0] 起始时间时间戳(整天)     long[1] 截止时间时间戳(整天)
   * @throws ParseException throw Exception
   */
  private long[] getStartTimeAndEndTime(String startDate,String endDate) throws ParseException {
    long startDateTime;
    long endDateTime;
    if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
      startDateTime = new DateTime().withDayOfYear(1).getMillis();
      endDateTime = new DateTime().withDayOfYear(1).plusYears(1).plusDays(-1).getMillis();
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      startDateTime = sdf.parse(startDate).getTime();
      endDateTime = sdf.parse(endDate).getTime();
    }
    return new long[]{startDateTime,endDateTime};
  }
}
