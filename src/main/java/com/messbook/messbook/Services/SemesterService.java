package com.messbook.messbook.Services;

import com.messbook.messbook.Entities.Semester_Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    /*
    * TODO
    *  1. get latest semester
    * */

    public Semester_Details getLatestSemester() {
        String query = "SELECT * FROM Semester_Details ORDER BY start_date;";
        Semester_Details latestSemester = null;

        try {
            latestSemester = jdbcTemplate.query(query, new BeanPropertyRowMapper<Semester_Details>(Semester_Details.class)).get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return latestSemester;
    }
}
