package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.*;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.ResponseStructures.ExtraItemWithCost;
import com.messbook.messbook.ResponseStructures.MessPresent;
import com.messbook.messbook.UtilsClasses.DateUtils;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.LinkedList;
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
     *    4. function to get all the entries for the month -- done
     *    5. function to get all the extra entries for a particular date -- done
     *    6. function to mark absent for a range -- done
     *    7. function to create a feedback
     *    8. function to get all the feedback by student for a specific month
     * */

    public ResponseWithError<Mess, MessErrors> getDetailsOfMess(String id) {
        String query = "SELECT * FROM Mess WHERE id = ?";
        ResponseWithError<Mess, MessErrors> response = new ResponseWithError<Mess, MessErrors>();

        try {
            List<Mess> mess = jdbcTemplate.query(query, new BeanPropertyRowMapper<Mess>(Mess.class), id);
            if (mess.size() != 1) {
                response.config(null, MessErrors.MESS_NOT_FOUND, "mess not found");
            } else {
                response.config(mess.get(0), MessErrors.SUCCESS);
            }
        } catch (Exception e) {
            response.config(null, MessErrors.FAILED, "failed to get the mess");
        }

        return response;
    }

    public List<Feedback> getAllFeedbackForMessByStudent(String mess_id, String student_roll_number, String semester_id) {
        String query = "SELECT * FROM Feedback WHERE student_roll_number = ? AND semester_id = ? AND mess_id = ?";

        List<Feedback> feedbacks = null;

        try {
            feedbacks = jdbcTemplate.query(query, new BeanPropertyRowMapper<Feedback>(Feedback.class), student_roll_number, semester_id, mess_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return feedbacks;
    }

    public List<Feedback> getAllFeedbackOfMessForTheMonth(String mess_id, String semester_id, Date firstDateOfMonth) {
        String query = "SELECT * FROM Feedback WHERE mess_id = ? and semester_id = ? AND month_of_comment = ?";
        List<Feedback> listOfFeedback = null;

        try {
            listOfFeedback = jdbcTemplate.query(query, new BeanPropertyRowMapper<Feedback>(Feedback.class), mess_id, semester_id, firstDateOfMonth);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listOfFeedback;
    }

    /// checked
    // getting all the entries for the month
    public List<MessPresent> getPresentInfoForMonth(String studentRoll, String messId, String semesterId, Date firstDayOfMonth, Date lastDateOfMonth) {
        // last date of month will be provided by the service as it can also be the semester ending date
        if(lastDateOfMonth == null) {
            lastDateOfMonth = DateUtils.getLastDateOfMonth(firstDayOfMonth);
        }
        String query = "SELECT start_date, end_date FROM mess_absent WHERE start_date >= ? AND end_date <= ?";

        List<Mess_Absent> messAbsentList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Mess_Absent>(Mess_Absent.class),
                firstDayOfMonth, lastDateOfMonth);

        LinkedList<MessPresent> messMonthList = new LinkedList<MessPresent>();

        int curr = 0;
        for (int i = 1; i <= lastDateOfMonth.getDate(); ++i) {
            MessPresent info = new MessPresent();
            info.setHasAte(true);

            if (curr < messAbsentList.size() && i >= messAbsentList.get(curr).getStart_date().getDate()) {
                info.setHasAte(false);
                if (i == messAbsentList.get(curr).getEnd_date().getDate()) curr++;
            }

            messMonthList.add(info);
        }

        return messMonthList;
    }

    // getting the extra entry for the date of the current semester
    public List<ExtraItemWithCost> getExtraEntryForDate(String studentRollNumber, String semesterId, String messId, Date date) {
        String query = "SELECT item_name, price FROM mess_extra_entry, extra_item_menu WHERE student_roll_number = ? AND semester_id = ? AND mess_id = ? AND date = ? AND item_name = name";
        try {
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<ExtraItemWithCost>(ExtraItemWithCost.class), studentRollNumber, semesterId, messId, date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // function to mark absent for a range
    public Boolean markAbsent(Mess_Absent absentInfo) {
        // the checking will happen at the level of service
        String query = "INSERT INTO mess_absent VALUES (?, ?, ?, ?, ?);";

        int count = 0;

        try {
            count = jdbcTemplate.update(query, absentInfo.getStudent_roll_number(), absentInfo.getSemester_id(), absentInfo.getMess_id(), absentInfo.getStart_date(), absentInfo.getEnd_date());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (count != 0) {
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    // function to create a feedback
    public Boolean createFeedback(Feedback feedback) {
        String query = "INSERT INTO feedback VALUES (?, ?, ?, ?, ?, ?)";

        int count = 0;

        try {
            count = jdbcTemplate.update(query, feedback.getStudent_roll_number(), feedback.getSemester_id(), feedback.getMess_id(), feedback.getText(), feedback.getMonth_of_comment(), feedback.getRating());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (count == 0) {
            return Boolean.FALSE;
        } else return Boolean.TRUE;
    }

    // function to get feedback by the student for a specific month
    public List<Feedback> getFeedbackByStudentForMonth(String studentRollNumber, String semesterId, String messId, Date firstDateOfMonth) {
        String query = "SELECT * FROM FEEDBACK WHERE mess_id = ? AND semester_id = ? AND student_roll_number = ? AND month_of_comment = ?";
        List<Feedback> feedback = null;

        try {
            feedback = jdbcTemplate.query(query, new BeanPropertyRowMapper<Feedback>(Feedback.class), messId, semesterId, studentRollNumber, firstDateOfMonth);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return feedback;
    }
}
