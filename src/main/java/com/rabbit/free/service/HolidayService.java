package com.rabbit.free.service;

import com.rabbit.free.vo.HolidayVO;

import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
public interface HolidayService {
    List<HolidayVO> getHolidays(long startDateTime, long endDateTime);

    List<HolidayVO> getFestivalHolidays(long startDateTime, long endDateTime);
}
