package com.rabbit.free.repository;

import com.rabbit.free.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.liu on 2017/5/15.
 */
@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Integer> {

    List<Holiday> findByIsHolidayAndDateBetween(int isHoliday, Date startDate, Date endDate);

    List<Holiday> findByIsFestivalHolidayAndDateBetween(int isFestivalHoliday, Date startDate, Date endDate);
}
