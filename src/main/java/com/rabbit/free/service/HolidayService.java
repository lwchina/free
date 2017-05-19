package com.rabbit.free.service;

import com.rabbit.free.vo.HolidayVo;

import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
public interface HolidayService {
  List<HolidayVo> getHolidays(long startDateTime, long endDateTime);

  List<HolidayVo> getFestivalHolidays(long startDateTime, long endDateTime);
}
