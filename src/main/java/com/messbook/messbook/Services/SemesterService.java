package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.SemesterDao;
import com.messbook.messbook.Entities.Semester_Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {

    @Autowired
    SemesterDao semesterDao;
    /*
    * TODO
    *  1. get latest semester
    * */

    public Semester_Details getLatestSemester() {
        return semesterDao.getLatestSemester();
    }
}
