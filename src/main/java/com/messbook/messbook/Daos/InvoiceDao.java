package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.MessInvoice;
import com.messbook.messbook.Entities.StudentInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class InvoiceDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<StudentInvoice> getStudentInvoiceList(
            String student_roll_number,
            String semester_id,
            Date month_of_issue
    ) {
        String query = "SELECT * FROM student_invoice WHERE student_roll_number = ? AND semester_id = ? AND month_of_issue = ?";
        List<StudentInvoice> invoiceList = null;
        try {
            invoiceList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(StudentInvoice.class), student_roll_number, semester_id,month_of_issue);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return invoiceList;
    }

    public List<MessInvoice> getMessInvoiceList(
            String mess_id,
            String semester_id,
            Date month_of_issue
    ) {
        String query = "SELECT * FROM messInvoice WHERE semester_id = ? AND mess_id = ? AND month_of_issue = ?";
        List<MessInvoice> messInvoiceList = null;

        try {
            messInvoiceList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(MessInvoice.class), semester_id, mess_id, month_of_issue);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return messInvoiceList;
    }

}
