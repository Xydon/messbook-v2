package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Mail;
import com.messbook.messbook.Services.MailService;
import com.messbook.messbook.Services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @Autowired
    SemesterService semesterService;
    /*
     * TODO
     *  1. create api for creating mail --
     *  2. create api for fetching mail {sent and received} -- done
     *  3. create api for setting viewed mail --
     * */

    //* getting the mails -- received
    @GetMapping("api/mails/received")
    public List<Mail> getReceivedMailOf(@RequestParam(value = "cmail_id") String cmail_id, @RequestParam(value = "batchNumber") int batchNumber) {
        String latestSemesterId = semesterService.getLatestSemester().getId();
        List<Mail> listOfMails = mailService.getAllReceivedMailOf(cmail_id, latestSemesterId, batchNumber);
        if (listOfMails == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else return listOfMails;
    }

    //* getting the mails -- sent
    @GetMapping("api/mails/sent")
    public List<Mail> getSentMailOf(@RequestParam(value = "cmail_id") String cmail_id, @RequestParam(value = "batchNumber") int batchNumber) {
        String latestSemesterId = semesterService.getLatestSemester().getId();
        List<Mail> listOfMails = mailService.getAllReceivedMailOf(cmail_id, latestSemesterId, batchNumber);
        if (listOfMails == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else return listOfMails;
    }

    //* creating mails
    @PostMapping("api/mails/create")
    public void createMail(@RequestBody Mail mail) {

    }

}
