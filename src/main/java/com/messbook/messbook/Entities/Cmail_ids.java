package com.messbook.messbook.Entities;

public class Cmail_ids {
    public String assignee_id;
    public String cmail_id;

    public Cmail_ids() {}

    public Cmail_ids(String assignee_id, String cmail_id) {
        this.assignee_id = assignee_id;
        this.cmail_id = cmail_id;
    }

    public String getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(String assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getCmail_id() {
        return cmail_id;
    }

    public void setCmail_id(String cmail_id) {
        this.cmail_id = cmail_id;
    }
}
