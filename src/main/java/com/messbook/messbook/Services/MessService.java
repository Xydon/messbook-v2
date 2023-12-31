package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.MessDao;
import com.messbook.messbook.Entities.*;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.ResponseStructures.ExtraItemWithCost;
import com.messbook.messbook.ResponseStructures.MessPresent;
import com.messbook.messbook.UtilsClasses.DateUtils;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;

@Service
public class MessService {

    @Autowired
    MessDao messDao;


    public ResponseWithError<Mess, MessErrors> getDetailsOfMess(String id) {
        return messDao.getDetailsOfMess(id);
    }

    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackForMessByStudent(String mess_id, String student_roll_number, String semester_id) {
        List<Feedback> feedbackList = messDao.getAllFeedbackForMessByStudent(mess_id, student_roll_number, semester_id);

        ResponseWithError<List<Feedback>, MessErrors> response = new ResponseWithError<List<Feedback>, MessErrors>();
        response.setResponse(feedbackList);

        if (feedbackList == null) {
            response.configError(MessErrors.FAILED, "Cannot fetch feedbacks");
        } else {
            response.configError(MessErrors.SUCCESS);
        }

        return response;
    }

    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackOfMessForTheMonth(String mess_id, String semester_id, Date firstDateOfMonth) {
        List<Feedback> feedbackList = messDao.getAllFeedbackOfMessForTheMonth(mess_id, semester_id, firstDateOfMonth);

        ResponseWithError<List<Feedback>, MessErrors> response = new ResponseWithError<List<Feedback>, MessErrors>();
        response.config(feedbackList, Errors.SUCCESS);

        if (feedbackList == null) {
            response.configError(Errors.FAILED, "failed to get the feedbacks, try again");
        }

        return response;
    }

    public ResponseWithError<Feedback, MessErrors> getAllFeedbackOfStudentForTheMonth(
            String student_roll_number,
            String mess_id,
            String semester_id,
            Date firstDateOfMonth
    ) {
        List<Feedback> feedback = messDao.getFeedbackByStudentForMonth(student_roll_number, semester_id, mess_id, firstDateOfMonth);

        ResponseWithError<Feedback, MessErrors> response = new ResponseWithError<Feedback, MessErrors>();

        if (feedback == null) {
            response.configAsFailed();
            return response;
        }

        if (feedback.size() == 0) {
            response.config(null, MessErrors.FEEDBACK_NOT_PRESENT);
            return response;
        }

        response.setResponse(feedback.get(0));
        return response;
    }

    public ResponseWithError<Boolean, MessErrors> createFeedback(Feedback feedback) {
        ResponseWithError<Boolean, MessErrors> response = new ResponseWithError<Boolean, MessErrors>();
        ResponseWithError<Feedback, MessErrors> previousFeedback = this.getAllFeedbackOfStudentForTheMonth(
                feedback.getStudent_roll_number(),
                feedback.getMess_id(),
                feedback.getSemester_id(),
                feedback.getMonth_of_comment()
        );

        if (previousFeedback.getError().getErrorCode().equals(Errors.SUCCESS)) {
            response.configAsFailed("feedback for this month is already present");
            return response;
        }

        Boolean verdict = messDao.createFeedback(feedback);
        response.setResponse(verdict);

        if (Boolean.compare(verdict, FALSE) == 0) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<List<MessPresent>, MessErrors> getPresentList(
            String student_roll_number,
            String mess_id,
            String semester_id,
            Date firstDateOfMonth
    ) {
        ResponseWithError<List<MessPresent>, MessErrors> response = new ResponseWithError<>();
        List<MessPresent> messPresentList = messDao.getPresentInfoForMonth(student_roll_number, mess_id, semester_id, firstDateOfMonth, null);

        response.setResponse(messPresentList);

        if (messPresentList == null) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<List<ExtraItemWithCost>, MessErrors> getExtraEntryForDate(String student_roll_number, String mess_id, String semester_id, Date date) {
        ResponseWithError<List<ExtraItemWithCost>, MessErrors> response = new ResponseWithError<>();
        List<ExtraItemWithCost> messExtraEntries = messDao.getExtraEntryForDate(
                student_roll_number,
                mess_id,
                semester_id,
                date
        );

        response.setResponse(messExtraEntries);

        if (messExtraEntries == null) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<Boolean, MessErrors> markAbsent(
            Date firstDateOfMonth,
            Mess_Absent mess_absent
    ) {

        ResponseWithError<List<MessPresent>, MessErrors> presentListResponse = this.getPresentList(
                mess_absent.getStudent_roll_number(),
                mess_absent.getMess_id(),
                mess_absent.getSemester_id(),
                firstDateOfMonth
        );
        ResponseWithError<Boolean, MessErrors> response = new ResponseWithError<>();

        if (presentListResponse == null) {
            response.configAsFailed();
            return response;
        }

        Date startDate = mess_absent.getStart_date();
        Date endDate = mess_absent.getEnd_date();
        List<MessPresent> messPresentList = presentListResponse.getResponse();
        Date lastDateOfMonthValue = DateUtils.getLastDateOfMonth(firstDateOfMonth);

        // valid range
        if(startDate.after(endDate)) {
            response.config(null, MessErrors.MESS_ABSENT_RANGE_OUT_OF_BOUNDS, "supplied date is not valid as start is greater than end");
            return response;
        }

        // greater than today
        java.util.Date today = new java.util.Date();
        if(today.after(startDate)) {
            response.config(null, MessErrors.MESS_ABSENT_CANNOT_BE_BEFORE, "supplied date cannot be before today");
            return response;
        }

        // belongs to the month
        if (startDate.before(firstDateOfMonth) || endDate.after(lastDateOfMonthValue)) {
            response.config(null, MessErrors.MESS_ABSENT_RANGE_OUT_OF_BOUNDS,
                    "supplied date doesn't belongs to current month, consider supplying the date in the current month");
            return response;
        }

        // not intersecting
        for (int i = startDate.getDate(); i <= endDate.getDate(); ++i) {
            if (messPresentList.get(i - 1).isHasAte()) {
                response.config(null, MessErrors.INTERSECTING_MESS_ABSENT, "supplied date intersects with previous entry");
                return response;
            }
        }

        // saving
        Boolean verdict = messDao.markAbsent(mess_absent);

        if(Boolean.compare(verdict, FALSE) == 0) {
            response.configAsFailed();
            return response;
        }

        response.config(Boolean.TRUE, MessErrors.SUCCESS, "successfully inserted");
        return response;
    }

    public ResponseWithError<List<Student>, MessErrors> getStudentsOfMess(String mess_id, String semester_id) {
        ResponseWithError<List<Student>, MessErrors> response = new ResponseWithError<>();
        List<Student> studentList = messDao.getAllStudentOfMess(mess_id, semester_id);

        response.setResponse(studentList);

        if(studentList == null) {
            response.configAsFailed("cannot get students of the mess");
        }

        return response;
    }

    public ResponseWithError<Boolean, MessErrors> createExtraEntry(Mess_Extra_Entry mess_extra_entry) {
        ResponseWithError<Boolean, MessErrors> response = new ResponseWithError<>();
        Boolean verdict = messDao.createExtraEntry(mess_extra_entry);

        response.setResponse(verdict);

        if(Boolean.compare(verdict, FALSE) == 0) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<List<Student>, MessErrors> getStudentsNotEatingOn(String mess_id, String semester_id, Date date) {
        ResponseWithError<List<Student>, MessErrors> response = new ResponseWithError<>();
        List<Student> studentList = messDao.getStudentsNotEatingOn(mess_id, semester_id, date);

        response.setResponse(studentList);

        if(studentList == null) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<Boolean, MessErrors> hasFiledMessChangeApplication(
            String mess_id,
            String student_roll_number,
            String semester_id
    ) {
        int verdict = messDao.hasFiledMessChangeApplication(mess_id, student_roll_number, semester_id);
        ResponseWithError<Boolean, MessErrors> response = new ResponseWithError<>();
        if(verdict == -1) {
            response.configAsFailed("error in determining");
        } else if(verdict == 0) {
            response.setResponse(FALSE);
        } else {
            response.setResponse(Boolean.TRUE);
        }
        return response;
    }

    public ResponseWithError<Boolean, MessErrors> fileMessChangeApplication(Mess_Change_Application mess_change_application) {
        ResponseWithError<Boolean, MessErrors> hasFiledMessChangeResponse = this.hasFiledMessChangeApplication(
                mess_change_application.getMess_id(), mess_change_application.getStudent_roll_number(), mess_change_application.getSemester_id());

        if(hasFiledMessChangeResponse.hasFailed()) return hasFiledMessChangeResponse;

        if(hasFiledMessChangeResponse.getResponse().compareTo(Boolean.TRUE) == 0) {
            hasFiledMessChangeResponse.config(FALSE, MessErrors.MESS_CHANGE_APPLICATION_ALREADY_PRESENT, "mess change application already present");
            return hasFiledMessChangeResponse;
        }

        boolean verdict = messDao.fileMessApplication(mess_change_application);

        if(!verdict) {
            hasFiledMessChangeResponse.configAsFailed();
            return hasFiledMessChangeResponse;
        }

        hasFiledMessChangeResponse.config(Boolean.TRUE, MessErrors.SUCCESS);
        return hasFiledMessChangeResponse;
    }

}
