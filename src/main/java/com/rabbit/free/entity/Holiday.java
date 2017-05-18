package com.rabbit.free.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wei.liu on 2017/5/15.
 */
@Data
@Entity
@Table(name = "holiday")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "is_holiday")
    private int isHoliday;

    @Column(name = "is_festival_holiday")
    private int isFestivalHoliday;
}
