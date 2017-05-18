package com.rabbit.free.controller;

import com.rabbit.free.vo.HolidayVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidayControllerTest {

    @Autowired
    private HolidayController holidayController;

    @Test
    public void getHolidays() throws ParseException {
        Object object = holidayController.getHolidays("2017-01-01","2017-05-16");
        List<HolidayVO> holidayVOList = (List<HolidayVO>)object;
        holidayVOList.forEach(  holidayVO -> {
            System.out.println(holidayVO);
        });
    }

    @Test
    public void getFestivalHolidays() throws ParseException {
        Object object = holidayController.getFestivalHolidays("2017-01-01","2017-05-16");
        List<HolidayVO> holidayVOList = (List<HolidayVO>)object;
        holidayVOList.forEach(  holidayVO -> {
            System.out.println(holidayVO);
        });
    }
}
