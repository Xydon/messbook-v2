package com.messbook.messbook.Entities;

import java.util.Date;

public class Mail{
    private String id;
    private String semester_id;
    private String sender_cmail;
    private String receiver_cmail;
    private String text;
    private java.util.Date sending_date;
    private boolean hasDelivered;
    private boolean hasRead;

    public Mail() {}
    public Mail(String id, String semester_id, String sender_cmail, String receiver_cmail, String text, Date sending_date, boolean hasDelivered, boolean hasRead) {
        this.id = id;
        this.semester_id = semester_id;
        this.sender_cmail = sender_cmail;
        this.receiver_cmail = receiver_cmail;
        this.text = text;
        this.sending_date = sending_date;
        this.hasDelivered = hasDelivered;
        this.hasRead = hasRead;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public String getSender_cmail() {
        return sender_cmail;
    }

    public void setSender_cmail(String sender_cmail) {
        this.sender_cmail = sender_cmail;
    }

    public String getReceiver_cmail() {
        return receiver_cmail;
    }

    public void setReceiver_cmail(String receiver_cmail) {
        this.receiver_cmail = receiver_cmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSending_date() {
        return sending_date;
    }

    public void setSending_date(Date sending_date) {
        this.sending_date = sending_date;
    }

    public boolean isHasDelivered() {
        return hasDelivered;
    }

    public void setHasDelivered(boolean hasDelivered) {
        this.hasDelivered = hasDelivered;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
