package com.messbook.messbook.ResponseStructures;

public class FeedbackPresence {
    private String monthName;
    private boolean hasGivenFeedback;

    public FeedbackPresence() {}

    public FeedbackPresence(String monthName, boolean hasGivenFeedback) {
        this.monthName = monthName;
        this.hasGivenFeedback = hasGivenFeedback;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public boolean isHasGivenFeedback() {
        return hasGivenFeedback;
    }

    public void setHasGivenFeedback(boolean hasGivenFeedback) {
        this.hasGivenFeedback = hasGivenFeedback;
    }
}
