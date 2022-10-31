package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.*;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.Enums.MessErrors;
import com.messbook.messbook.Enums.SemesterErrors;
import com.messbook.messbook.ResponseStructures.ExtraItemWithCost;
import com.messbook.messbook.ResponseStructures.FeedbackPresence;
import com.messbook.messbook.ResponseStructures.MessPresent;
import com.messbook.messbook.Services.MessService;
import com.messbook.messbook.Services.SemesterService;
import com.messbook.messbook.UtilsClasses.DateUtils;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedList;
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
    /// checked
    @GetMapping("api/mess/feedback/fetch")
    public ResponseWithError<List<Feedback>, MessErrors> getAllFeedbackForMessByStudent(
            @RequestParam(value = "mess_id") String mess_id,
            @RequestParam(value= "student_roll_number") String student_roll_number
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

    /// checked
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

    /// checked
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

    /// checked
    @PostMapping("api/mess/feedback/create")
    public ResponseWithError<Boolean, MessErrors> createFeedback(@RequestBody Feedback feedback) {
        return messService.createFeedback(feedback);
    }

    /// checked
    @GetMapping("api/mess/feedback/presenceList")
    public ResponseWithError<List<FeedbackPresence>, MessErrors> getFeedbackPresenceList(
            @RequestParam(value="mess_id") String mess_id,
            @RequestParam(value = "student_roll_number") String student_roll_number
    ) {
        ResponseWithError<List<Date>, SemesterErrors> listOfMonthsResponse = semesterService.listOfMonthInCurrentSemesterNow();
        ResponseWithError<List<FeedbackPresence>, MessErrors> response = new ResponseWithError<List<FeedbackPresence>, MessErrors>();
        ResponseWithError<Semester_Details, SemesterErrors> currentSemesterResponse = semesterService.getLatestSemester();

        if(listOfMonthsResponse.hasFailed() || currentSemesterResponse.hasFailed()) {
            response.configAsFailed();
            return response;
        }

        List<Date> listOfMonth = listOfMonthsResponse.getResponse();
        String currentSemesterId = currentSemesterResponse.getResponse().getId();

        LinkedList<FeedbackPresence> feedbackPresences = new LinkedList<>();
        for(Date firstDateOfMonth : listOfMonth) {
            ResponseWithError<Feedback, MessErrors> feedbackResponse = messService.getAllFeedbackOfStudentForTheMonth(student_roll_number, mess_id, currentSemesterId, firstDateOfMonth);
            FeedbackPresence presence = new FeedbackPresence();

            if(feedbackResponse.hasFailed()) {
                response.configAsFailed();
                return response;
            }

            presence.setHasGivenFeedback(!feedbackResponse.retrieveErrorCode().equals(MessErrors.FEEDBACK_NOT_PRESENT));
            presence.setMonthName(DateUtils.getMonthAt(firstDateOfMonth.getMonth()));

            feedbackPresences.add(presence);
        }

        response.config(feedbackPresences, Errors.SUCCESS);
        return response;
    }

    ///////////////////// FEEDBACK END ///////////////////////////////

    /// checked
    @GetMapping("api/mess/service/presenceList/{month}")
    public ResponseWithError<List<MessPresent>, MessErrors> getPresenceList(
            @PathVariable Date month,
            @RequestParam(value = "student_roll_number") String student_roll_number,
            @RequestParam(value = "mess_id") String mess_id,
            @RequestParam(value = "semester_id") String semester_id
    ) {
        return messService.getPresentList(student_roll_number, mess_id, semester_id, month);
    }

    /// checked
    @GetMapping("api/mess/extras/{date}")
    public ResponseWithError<List<ExtraItemWithCost>, MessErrors> getExtraEntryForDate(
            @PathVariable Date date,
            @RequestParam(value = "student_roll_number") String student_roll_number,
            @RequestParam(value = "mess_id") String mess_id
    ) {
        ResponseWithError<Semester_Details, SemesterErrors> semesterResponse = semesterService.getLatestSemester();
        ResponseWithError<List<ExtraItemWithCost>, MessErrors> response = new ResponseWithError<>();
        response.configAsFailed();

        if(semesterResponse.getError().getErrorCode().equals(Errors.FAILED)) {
            return response;
        }

        return messService.getExtraEntryForDate(student_roll_number, mess_id, semesterResponse.getResponse().getId(), date);
    }

    /// checked
    @PostMapping("api/mess/markAbsent")
    public ResponseWithError<Boolean, MessErrors> markAbsent(
            @RequestBody Mess_Absent messAbsent,
            @RequestParam(value="firstDateOfMonth") Date firstDateOfMonth
    ) {
        return messService.markAbsent(firstDateOfMonth, messAbsent);
    }

    /// checked
    @GetMapping("api/mess/students")
    public ResponseWithError<List<Student>, MessErrors> getStudentsOfMess(@RequestParam(value = "mess_id") String mess_id, @RequestParam(value = "semester_id") String semester_id) {
        return messService.getStudentsOfMess(mess_id, semester_id);
    }

    /// checked
    @PostMapping("api/mess/createExtraEntry")
    public ResponseWithError<Boolean, MessErrors> createExtraEntry(
            @RequestBody Mess_Extra_Entry extra_entry
    ) {
        return messService.createExtraEntry(extra_entry);
    }

    /// checked
    @GetMapping("api/mess/absentList")
    public ResponseWithError<List<Student>, MessErrors> studentsNotEatingOn (
            @RequestParam(value = "mess_id") String mess_id,
            @RequestParam(value = "date") Date date
    ) {
        ResponseWithError<Semester_Details, SemesterErrors> semesterResponse = semesterService.getLatestSemester();
        ResponseWithError<List<Student>, MessErrors> response = new ResponseWithError<>();

        if(semesterResponse.hasFailed()) {
            response.configAsFailed("cannot get absent list");
            return response;
        }

        String semesterId = semesterResponse.getResponse().getId();

        return messService.getStudentsNotEatingOn(mess_id, semesterId, date);
    }

    @GetMapping("api/mess/change/exists")
    public ResponseWithError<Boolean, MessErrors> hasFiledMessChangeApplication(
            @RequestParam(value="mess_id") String mess_id,
            @RequestParam(value="student_roll_number") String student_roll_number
    ) {
        ResponseWithError<Boolean,MessErrors> response = new ResponseWithError<>();
        ResponseWithError<Semester_Details, SemesterErrors> semesterDetailsResponse = semesterService.getLatestSemester();

        if(semesterDetailsResponse.hasFailed()) {
            response.configAsFailed();
            return response;
        }

        String semesterId = semesterDetailsResponse.getResponse().getId();
        ResponseWithError<Boolean, MessErrors> filingResponse = messService.hasFiledMessChangeApplication(mess_id, student_roll_number, semesterId);

        if(filingResponse.hasFailed()) {
            response.configAsFailed();
            return response;
        }

        return filingResponse;
    }

    /// checked
    @PostMapping("api/mess/change")
    public ResponseWithError<Boolean, MessErrors> createMessChangeApplication(
            @RequestBody Mess_Change_Application mess_change_application
    ) {
        ResponseWithError<Boolean,MessErrors> response = new ResponseWithError<>();
        ResponseWithError<Semester_Details, SemesterErrors> semesterDetailsResponse = semesterService.getLatestSemester();

        if(semesterDetailsResponse.hasFailed()) {
            response.configAsFailed();
            return response;
        }

        String semesterId = semesterDetailsResponse.getResponse().getId();
        ResponseWithError<Boolean, MessErrors> filingResponse = messService.hasFiledMessChangeApplication(
                mess_change_application.getMess_id(), mess_change_application.getStudent_roll_number(), mess_change_application.getSemester_id());

        if(filingResponse.hasFailed()) {
            response.configAsFailed();
            return response;
        }

        return messService.fileMessChangeApplication(mess_change_application);
    }
}
