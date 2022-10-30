package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.MessDao;
import com.messbook.messbook.Entities.Feedback;
import com.messbook.messbook.Entities.Mess;
import com.messbook.messbook.Entities.Mess_Extra_Entry;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.ResponseStructures.ExtraItemWithCost;
import com.messbook.messbook.ResponseStructures.FeedbackPresence;
import com.messbook.messbook.ResponseStructures.MessPresent;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MessService {

    @Autowired
    MessDao messDao;


    public ResponseWithError<Mess, MessErrors> getDetailsOfMess(String id) {
        return messDao.getDetailsOfMess(id);
    }
    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackForMessByStudent(String mess_id, String student_roll_number, String semester_id) {
        List<Feedback> feedbackList = messDao.getAllFeedbackForMessByStudent(mess_id, student_roll_number, semester_id);

        ResponseWithError<List<Feedback>,MessErrors> response = new ResponseWithError<List<Feedback>, MessErrors>();
        response.setResponse(feedbackList);

        if(feedbackList == null) {
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

        if(feedbackList == null) {
            response.configError(Errors.FAILED, "failed to get the feedbacks, try again");
        }

        return response;
    }

    public ResponseWithError<Feedback, MessErrors> getAllFeedbackOfStudentForTheMonth(String student_roll_number, String mess_id, String semester_id, Date firstDateOfMonth) {
        List<Feedback> feedback = messDao.getFeedbackByStudentForMonth(student_roll_number, semester_id, mess_id, firstDateOfMonth);

        ResponseWithError<Feedback, MessErrors> response = new ResponseWithError<Feedback, MessErrors>();

        if(feedback == null) {
            response.configAsFailed();
            return response;
        }

        if(feedback.size() == 0) {
            response.config(null, MessErrors.FEEDBACK_NOT_PRESENT);
            return response;
        }

        response.setResponse(feedback.get(0));
        return response;
    }

    public ResponseWithError<Boolean, MessErrors> createFeedback(Feedback feedback) {
        ResponseWithError<Boolean, MessErrors> response = new ResponseWithError<Boolean, MessErrors>();
        ResponseWithError<Feedback, MessErrors> previousFeedback = this.getAllFeedbackOfStudentForTheMonth(feedback.getStudent_roll_number(), feedback.getMess_id(), feedback.getSemester_id(), feedback.getMonth_of_comment());

        if(previousFeedback.getError().getErrorCode().equals(Errors.SUCCESS)) {
            response.configAsFailed("feedback for this month is already present");
            return response;
        }

        Boolean verdict = messDao.createFeedback(feedback);
        response.setResponse(verdict);

        if(Boolean.compare(verdict, Boolean.FALSE) == 0) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<List<MessPresent>, MessErrors> getPresentList(
            String student_roll_number,
            String mess_id,
            String semester_id,
            Date firstDateOfMonth
    ){
        ResponseWithError<List<MessPresent>, MessErrors> response = new ResponseWithError<>();
        List<MessPresent> messPresentList = messDao.getPresentInfoForMonth(student_roll_number, mess_id, semester_id, firstDateOfMonth, null);

        response.setResponse(messPresentList);

        if(messPresentList == null) {
            response.configAsFailed();
        }

        return response;
    }

    public ResponseWithError<List<ExtraItemWithCost>, MessErrors> getExtraEntryForDate(String student_roll_number, String mess_id, String semester_id, Date date) {
        ResponseWithError<List<ExtraItemWithCost>, MessErrors> response= new ResponseWithError<>();
        List<ExtraItemWithCost> messExtraEntries = messDao.getExtraEntryForDate(
                student_roll_number,
                mess_id,
                semester_id,
                date
        );

        response.setResponse(messExtraEntries);

        if(messExtraEntries == null) {
            response.configAsFailed();
        }

        return response;
    }
}
