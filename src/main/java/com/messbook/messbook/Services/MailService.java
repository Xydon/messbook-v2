package com.messbook.messbook.Services;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.messbook.messbook.Daos.MailDao;
import com.messbook.messbook.Entities.Mail;
import com.messbook.messbook.Enums.MailErrors;
import com.messbook.messbook.UtilsClasses.ErrorData;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MailService {
    @Autowired
    MailDao mailDao;

    /*
     * TODO
     *  1. create function for creating mail --done
     *  2. create function for fetching mail {sent and received} --done
     *  4. create function for setting viewed mail --done
     * */

    //* creating the mail
    public ResponseWithError<Boolean, MailErrors> createMail(Mail mail) {
        ResponseWithError<Boolean, MailErrors> response = new ResponseWithError<Boolean, MailErrors>();

        // assign id
        mail.setId(NanoIdUtils.randomNanoId());

        // assigning delivery
        mail.setHasDelivered(true);

        // assigning reading status
        mail.setHasRead(false);

        // checking if the mail ids exists;
        boolean cmailExistsVerdict = mailDao.doesCMailExists(mail.getSender_cmail());

        if(!cmailExistsVerdict) {
            response.configError(MailErrors.SENDER_CMAIL_DOES_NOT_EXISTS, "no cmail found for receiver");
            response.setResponse(Boolean.FALSE);
            return response;
        }

        // assigning date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
        LocalDate currentDate = LocalDate.now();
        int date = currentDate.getDayOfMonth();
        int month = currentDate.getMonth().getValue();
        int year = currentDate.getYear();

        mail.setSending_date(new Date(date, month, year));

        // saving it in the database
        boolean savingVerdict = mailDao.createMail(mail);

        if(!savingVerdict) {
            response.configError(MailErrors.FAILED);
            response.setResponse(Boolean.FALSE);
            return response;
        }

        response.configError(MailErrors.SUCCESS);
        response.setResponse(Boolean.TRUE);
        return response;
    }

    // * getting the mail
    public List<Mail> getAllReceivedMailOf(String receiver_cmail, String semester_id, int batchNumber ) {
        return mailDao.getAllReceivedMailOf(receiver_cmail, semester_id, batchNumber);
    }

    public List<Mail> getAllSentMailOf(String sender_cmail, String semester_id, int batchNumber ) {
        return mailDao.getAllSentMailOf(sender_cmail, semester_id, batchNumber);
    }

    // * setting view for mail
    public boolean setWatchedForMail(String mailId) {
        return mailDao.setWatchedForMail(mailId);
    }
}
