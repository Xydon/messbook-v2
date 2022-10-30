package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.MessDao;
import com.messbook.messbook.Entities.Feedback;
import com.messbook.messbook.Entities.Mess;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
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


}
