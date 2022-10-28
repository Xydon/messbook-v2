package com.messbook.messbook.Services;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.messbook.messbook.Daos.MailDao;
import com.messbook.messbook.Entities.Mail;
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
    public boolean createMail(Mail mail) {

        // assign id
        mail.setId(NanoIdUtils.randomNanoId());

        // assigning delivery
        mail.setHasDelivered(true);

        // assigning reading status
        mail.setHasRead(false);

        // assigning date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
        LocalDate currentDate = LocalDate.now();
        int date = currentDate.getDayOfMonth();
        int month = currentDate.getMonth().getValue();
        int year = currentDate.getYear();

        mail.setSending_date(new Date(date, month, year));

        // saving it in the database
        return mailDao.createMail(mail);
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
