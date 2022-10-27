package com.messbook.messbook.Entities;

import java.util.Date;

public class Mail{
    private String sender_cmail;
    private String receiver_cmail;
    private String text;
    private java.util.Date sending_date;

    public Mail() {}
    public Mail(String sender_cmail, String receiver_cmail, String text, Date sending_date) {
        this.sender_cmail = sender_cmail;
        this.receiver_cmail = receiver_cmail;
        this.text = text;
        this.sending_date = sending_date;
    }

    public String getSender_cmail(){
        return sender_cmail;
    }

    public void setSender_cmail(String sender_cmail){
        this.sender_cmail=sender_cmail;
    }

    public String getReceiver_cmail(){
        return receiver_cmail;
    }

    public void setReceiver_cmail(String receiver_cmail){
        this.receiver_cmail=receiver_cmail;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text=text;
    }

    public java.util.Date getSending_date(){
        return sending_date;
    }

    public void setSending_date(java.util.Date sending_date){
        this.sending_date=sending_date;
    }
}
