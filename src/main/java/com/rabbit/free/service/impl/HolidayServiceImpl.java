package com.rabbit.free.service.impl;

import com.rabbit.free.entity.Holiday;
import com.rabbit.free.repository.HolidayRepository;
import com.rabbit.free.service.HolidayService;
import com.rabbit.free.vo.HolidayVO;
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

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public List<HolidayVO> getHolidays(long startDateTime, long endDateTime) {
        Date startDate = new Date(startDateTime);
        Date endDate = new Date(endDateTime);
        List<Holiday> holidayList = holidayRepository.findByIsHolidayAndDateBetween(1,startDate,endDate);
        holidayList.sort( (o1,o2) -> Long.valueOf(o1.getDate().getTime()).compareTo(o2.getDate().getTime()));
        return pack(holidayList);
    }

    @Override
    public List<HolidayVO> getFestivalHolidays(long startDateTime, long endDateTime) {
        Date startDate = new Date(startDateTime);
        Date endDate = new Date(endDateTime);
        List<Holiday> holidayList = holidayRepository.findByIsFestivalHolidayAndDateBetween(1,startDate,endDate);
        holidayList.sort( (o1,o2) -> Long.valueOf(o1.getDate().getTime()).compareTo(o2.getDate().getTime()));
        return pack(holidayList);
    }

    /**
     * List<Holiday> 转 List<HolidayVO>
     * @param holidayList
     * @return
     */
    private List<HolidayVO> pack (List<Holiday> holidayList) {
        List<HolidayVO> holidayVOList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holidayList.forEach( holiday -> {
            HolidayVO holidayVO = new HolidayVO();
            holidayVO.setDate(sdf.format(holiday.getDate()));
            holidayVO.setFestival(holiday.getIsFestivalHoliday());
            holidayVOList.add(holidayVO);
        });
        return holidayVOList;
    }
}
