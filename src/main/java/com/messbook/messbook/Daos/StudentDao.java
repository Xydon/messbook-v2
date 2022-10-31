package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Student getStudent(String student_roll_number) {
        String query = "SELECT * FROM Student WHERE roll_number = ?";
        Student student = null;

        try {
            student = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Student.class), student_roll_number).get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return student;
    }



}
