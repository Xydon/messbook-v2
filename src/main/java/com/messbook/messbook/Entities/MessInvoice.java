package com.messbook.messbook.Entities;

public class MessInvoice {
    private String mess_id;
    private int base_amount;
    private int extra_amount;
    private int previous_due;

    public MessInvoice() {
    }

    public MessInvoice(String mess_id, int base_amount, int extra_amount, int previous_due) {
        this.mess_id = mess_id;
        this.base_amount = base_amount;
        this.extra_amount = extra_amount;
        this.previous_due = previous_due;
    }

    public String getMess_id() {
        return mess_id;
    }

    public void setMess_id(String mess_id) {
        this.mess_id = mess_id;
    }

    public int getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(int base_amount) {
        this.base_amount = base_amount;
    }

    public int getExtra_amount() {
        return extra_amount;
    }

    public void setExtra_amount(int extra_amount) {
        this.extra_amount = extra_amount;
    }

    public int getPrevious_due() {
        return previous_due;
    }

    public void setPrevious_due(int previous_due) {
        this.previous_due = previous_due;
    }
}