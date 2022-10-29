package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Feedback;
import com.messbook.messbook.Entities.Mess;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class MessDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    *   TODO
    *    1. function to get the details for a mess -- done
    *    2. function to get the feedback for a mess given by a student in the current semester -- done
    *    3. function to get all the feedback for the mess for a specific month in the current semester -- done
    * */

    public ResponseWithError<Mess, MessErrors> getDetailsOfMess(String id){
        String query = "SELECT * FROM Mess WHERE id = ?";
        ResponseWithError<Mess, MessErrors> response = new ResponseWithError<Mess, MessErrors>();

        try {
            List<Mess> mess = jdbcTemplate.query(query, new BeanPropertyRowMapper<Mess>(Mess.class),id);
            if(mess.size() != 1) {
                response.config(null, MessErrors.MESS_NOT_FOUND, "mess not found");
            } else {
                response.config(mess.get(0), MessErrors.SUCCESS);
            }
        } catch(Exception e) {
            response.config(null, MessErrors.FAILED, "failed to get the mess");
        }

        return response;
    }

    public List<Feedback> getAllFeedbackForMessByStudent(String mess_id, String student_roll_number, String semester_id) {
        String query = "SELECT * FROM Feedback WHERE student_roll_number = ? AND semester_id = ? AND mess_id = ?";

        List<Feedback> feedbacks = null;

        try {
            feedbacks = jdbcTemplate.query(query, new BeanPropertyRowMapper<Feedback>(Feedback.class), student_roll_number, semester_id, mess_id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return feedbacks;
    }

    public List<Feedback> getAllFeedbackOfMessForTheMonth(String mess_id, String semester_id, Date firstDateOfMonth) {
        String query = "SELECT * FROM Feedback WHERE mess_id = ? and semester_id = ? AND month_of_comment = ?";
        List<Feedback> listOfFeedback = null;

        try {
            listOfFeedback = jdbcTemplate.query(query, new BeanPropertyRowMapper<Feedback>(Feedback.class), mess_id, semester_id, firstDateOfMonth);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return listOfFeedback;
    }
}
