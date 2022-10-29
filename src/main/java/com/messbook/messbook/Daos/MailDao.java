package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Cmail_ids;
import com.messbook.messbook.Entities.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MailDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    /*
     * TODO
     *  1. create function for creating mail --done
     *  2. create function for fetching mail {sent and received} --done
     *  4. create function for setting viewed mail --done
     * */

    // getting mail with batch -- which are received
    public List<Mail> getAllReceivedMailOf(String receiver_cmail, String semester_id, int batchNumber) {
        String query = "SELECT * FROM mail WHERE receiver_cmail = ? AND semester_id = ? ORDER BY sending_date LIMIT ? , 50";
        List<Mail> listOfMail = null;
        try {
            listOfMail = jdbcTemplate.query(query, new BeanPropertyRowMapper<Mail>(Mail.class), receiver_cmail, semester_id, (batchNumber - 1) * 50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listOfMail;
    }

    // getting mail with batch -- which are received
    public List<Mail> getAllSentMailOf(String sender_cmail, String semester_id, int batchNumber) {
        String query = "SELECT * FROM mail WHERE receiver_cmail = ? AND semester_id = ? ORDER BY sending_date LIMIT ? , 50";
        List<Mail> listOfMail = null;
        try {
            listOfMail = jdbcTemplate.query(query, new BeanPropertyRowMapper<Mail>(Mail.class), sender_cmail, semester_id, (batchNumber - 1) * 50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listOfMail;
    }

    public boolean doesCMailExists(String cmail_id) {
        // checking if the mails exits
        try {
            List<Cmail_ids> cmailIds = jdbcTemplate.query("SELECT * FROM cmail_ids WHERE cmail_id = ?", new BeanPropertyRowMapper<Cmail_ids>(Cmail_ids.class), cmail_id);
            return cmailIds.size() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    // creating the mail
    public boolean createMail(Mail mail) {
        String query = "INSERT INTO mail values (?, ?, ?, ?, ?, ?, ?)";
        boolean verdict = false;

        try {
            jdbcTemplate.update(query, mail.getId(), mail.getSender_cmail(), mail.getReceiver_cmail(), mail.getText(), mail.getSending_date(), mail.isHasDelivered(), mail.isHasRead());
            verdict = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return verdict;
    }

    // set watched for the mail
    public boolean setWatchedForMail(String mailId) {
        String query = "UPDATE mail SET hasRead = true WHERE id = ?";
        boolean verdict = false;

        try {
            jdbcTemplate.update(query, mailId);
            verdict = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return verdict;
    }
}
