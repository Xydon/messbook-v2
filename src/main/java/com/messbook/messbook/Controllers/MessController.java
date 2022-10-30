package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Feedback;
import com.messbook.messbook.Entities.Mess;
import com.messbook.messbook.Entities.Semester_Details;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.Enums.SemesterErrors;
import com.messbook.messbook.Services.MessService;
import com.messbook.messbook.Services.SemesterService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
public class MessController {
    @Autowired
    MessService messService;

    @Autowired
    SemesterService semesterService;

    /// checked
    @GetMapping("api/mess/fetch")
    public ResponseWithError<Mess, MessErrors> getDetailsOfMess(@RequestParam(value = "mess_id") String mess_id) {
        return messService.getDetailsOfMess(mess_id);
    }

    //////////////////// FEEDBACKS //////////////////////
    @GetMapping("api/mess/feedback/fetch")
    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackForMessByStudent(
            @RequestParam(value = "mess_id") String mess_id,
            @RequestParam(value= "student_id") String student_roll_number
    ) {


        // the response
        ResponseWithError<List<Feedback>, MessErrors> response = new ResponseWithError<List<Feedback>, MessErrors>();

        // getting the current semester id
        ResponseWithError<Semester_Details, SemesterErrors> currentSemesterResponse = semesterService.getLatestSemester();

        // error gate for current semester details
        if(currentSemesterResponse.getError().getErrorCode().equals(SemesterErrors.FAILED)) {
            response.config(null, MessErrors.FAILED);
            response.getError().setErrorMessages(currentSemesterResponse.getError().getErrorMessages());
            return response;
        }

        // getting the data;
        String currentSemesterId = currentSemesterResponse.getResponse().getId();

        // returning the response;
        return messService.getAllFeedbackForMessByStudent(mess_id, student_roll_number, currentSemesterId);

    }

    @GetMapping("api/mess/feedback/fetch/all")
    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackOfMessForTheMonth(
            @RequestParam(value="mess_id") String mess_id,
            @RequestParam(value="month") String month
    ) {
        ResponseWithError<List<Feedback>, MessErrors> response = new ResponseWithError<List<Feedback>, MessErrors>();
        ResponseWithError<Semester_Details, SemesterErrors> latestSemesterResponse = semesterService.getLatestSemester();

        if(latestSemesterResponse.getResponse() == null) {
            response.configAsFailed(latestSemesterResponse.getError().getErrorMessages());
            return response;
        }

        String currentSemesterId = latestSemesterResponse.getResponse().getId();
        Date monthDate;
        try {
            monthDate = Date.valueOf(month);
        } catch(Exception e) {
            response.config(null, Errors.INVALID_DATE, "supplied month is invalid");
            return response;
        }
        return messService.getAllFeedbackOfMessForTheMonth(mess_id, currentSemesterId, monthDate);
    }

    @GetMapping("api/mess/feedback/fetch/{month}")
    public ResponseWithError<Feedback, MessErrors> getAllFeedbackByStudentForMonth(
            @RequestParam(value="mess_id") String mess_id,
            @RequestParam(value="student_roll_number") String student_roll_number,
            @PathVariable Date month
    ) {
        ResponseWithError<Semester_Details, SemesterErrors> semesterResponse = semesterService.getLatestSemester();
        ResponseWithError<Feedback, MessErrors> response = new ResponseWithError<Feedback, MessErrors>();
        response.configAsFailed();

        if(semesterResponse.getError().getErrorCode().equals(Errors.FAILED)) {
            return response;
        }

        return messService.getAllFeedbackOfStudentForTheMonth(student_roll_number, mess_id,  semesterResponse.getResponse().getId(), month);
    }

    @PostMapping("api/mess/feedback/create")
    public ResponseWithError<Boolean, MessErrors> createFeedback(@RequestBody Feedback feedback) {
        return null;
    }
}
