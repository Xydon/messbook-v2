package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Mail;
import com.messbook.messbook.Entities.Semester_Details;
import com.messbook.messbook.Enums.MailErrors;
import com.messbook.messbook.Enums.SemesterErrors;
import com.messbook.messbook.Services.MailService;
import com.messbook.messbook.Services.SemesterService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MailController {
    @Autowired
    MailService mailService;

    @Autowired
    SemesterService semesterService;
    /*
     * TODO
     *  1. create api for creating mail -- done
     *  2. create api for fetching mail {sent and received} -- done
     *  3. create api for setting viewed mail --
     * */

    //* getting the mails -- received
    @GetMapping("api/mails/received")
    public ResponseWithError<List<Mail>,MailErrors> getReceivedMailOf(@RequestParam(value = "cmail_id") String cmail_id, @RequestParam(value = "batchNumber") int batchNumber) {
        ResponseWithError<List<Mail>, MailErrors> response = new ResponseWithError<List<Mail>, MailErrors>();
        ResponseWithError<Semester_Details, SemesterErrors> latestSemesterResponse = semesterService.getLatestSemester();
        if(latestSemesterResponse.getError().getErrorCode() == SemesterErrors.FAILED) {
            response.configError(MailErrors.FAILED, "failed to get the latest semester");
            response.setResponse(null);
            return response;
        }

        String latestSemesterId = latestSemesterResponse.getResponse().getId();
        response =  mailService.getAllReceivedMailOf(cmail_id, latestSemesterId, batchNumber);

        return response;
    }

    //* getting the mails -- sent
    @GetMapping("api/mails/sent")
    public ResponseWithError<List<Mail>, MailErrors> getSentMailOf(@RequestParam(value = "cmail_id") String cmail_id, @RequestParam(value = "batchNumber") int batchNumber) {
        ResponseWithError<List<Mail>, MailErrors> response = new ResponseWithError<List<Mail>, MailErrors>();
        ResponseWithError<Semester_Details, SemesterErrors> latestSemesterResponse = semesterService.getLatestSemester();
        if(latestSemesterResponse.getError().getErrorCode() == SemesterErrors.FAILED) {
            response.configError(MailErrors.FAILED, "failed to get the latest semester");
            response.setResponse(null);
            return response;
        }

        String latestSemesterId = latestSemesterResponse.getResponse().getId();
        response =  mailService.getAllSentMailOf(cmail_id, latestSemesterId, batchNumber);

        return response;
    }

    //* creating mails
    @PostMapping("api/mails/create")
    public ResponseWithError<Boolean, MailErrors> createMail(@RequestBody Mail mail) {
        return mailService.createMail(mail);
    }

    //* setting the view on the mail
    @PostMapping("api/mails/setViewed")
    public ResponseWithError<Boolean, MailErrors> setViewedOn(@RequestParam(value = "mailId") String mailId) {
        return mailService.setWatchedForMail(mailId);
    }
}
