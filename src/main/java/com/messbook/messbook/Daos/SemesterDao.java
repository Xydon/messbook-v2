package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Semester_Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SemesterDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

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
