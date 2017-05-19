package com.rabbit.free.service.impl;

import com.rabbit.free.entity.Holiday;
import com.rabbit.free.repository.HolidayRepository;
import com.rabbit.free.service.HolidayService;
import com.rabbit.free.vo.HolidayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
@Service
public class HolidayServiceImpl implements HolidayService {

  /**
   * holiday repository.
   */
  @Autowired
  private HolidayRepository holidayRepository;

  /**
   * get holiday date.
   * @param startDateTime   start timestamp (YYYY-MM-DD)
   * @param endDateTime     end timestamp   (YYYY-MM-DD)
   * @return  List HolidayVo
   */
  @Override
  public List<HolidayVo> getHolidays(final long startDateTime, final long endDateTime) {
    Date startDate = new Date(startDateTime);
    Date endDate = new Date(endDateTime);
    List<Holiday> holidayList =
            holidayRepository.findByIsHolidayAndDateBetween(1, startDate, endDate);
    holidayList.sort((o1, o2) ->
            Long.valueOf(o1.getDate().getTime()).compareTo(o2.getDate().getTime()));
    return pack(holidayList);
  }

  /**
   * get festival holiday date.
   * @param startDateTime start timestamp (YYYY-MM-DD)
   * @param endDateTime   end timestamp   (YYYY-MM-DD)
   * @return List HolidayVo
   */
  @Override
  public List<HolidayVo> getFestivalHolidays(final long startDateTime, final long endDateTime) {
    Date startDate = new Date(startDateTime);
    Date endDate = new Date(endDateTime);
    List<Holiday> holidayList =
            holidayRepository.findByIsFestivalHolidayAndDateBetween(1, startDate, endDate);
    holidayList.sort((o1, o2) ->
            Long.valueOf(o1.getDate().getTime()).compareTo(o2.getDate().getTime()));
    return pack(holidayList);
  }

  /**
   * List Holiday to List HolidayVo.
   * @param holidayList holiday集合
   * @return List HolidayVo
   */
  private List<HolidayVo> pack(final List<Holiday> holidayList) {
    List<HolidayVo> holidayVoList = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    holidayList.forEach(holiday -> {
      HolidayVo holidayVo = new HolidayVo();
      holidayVo.setDate(sdf.format(holiday.getDate()));
      holidayVo.setFestival(holiday.getIsFestivalHoliday());
      holidayVoList.add(holidayVo);
    });
    return holidayVoList;
  }
}
