package com.rabbit.free.repository;

import com.rabbit.free.entity.Holiday;
import com.rabbit.free.repository.HolidayRepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidayRepositoryTest {

    @Autowired
    private HolidayRepository holidayRepository;

    @Test
    public void findByIsHolidayAndDateBetween() {
        DateTime startDateTime = new DateTime().withTimeAtStartOfDay().plusDays(-1);
        DateTime endDateTime = new DateTime().withTimeAtStartOfDay().plusDays(5);
        Date startDate = startDateTime.toDate();
        Date endDate = endDateTime.toDate();
        List<Holiday> holidays = holidayRepository.findByIsHolidayAndDateBetween(1,startDate,endDate);
        holidays.forEach( holiday -> {
            System.out.println(holiday);
        });
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
    }


    /*@Test
    public void addHolidayAllDay(){
        DateTime yearStartDateTime = new DateTime().withDayOfYear(1);
        List<Holiday> holidays = new ArrayList<>();
        for (int i = 0; i < 365; i ++) {
            Holiday holiday = new Holiday();
            DateTime dateTime = yearStartDateTime.plusDays(i);
            Date date = dateTime.toDate();
            holiday.setDate(date);
            switch (dateTime.getDayOfWeek()) {
                case 6:holiday.setIsHoliday(1);break;
                case 7:holiday.setIsHoliday(1);break;
                default: holiday.setIsHoliday(0);
            }
            holidays.add(holiday);
        }
        holidayRepository.save(holidays);
    }*/
}
