package com.messbook.messbook.ResponseStructures;

public class MessPresent {
    private boolean hasAte;

    public boolean isHasAte() {
        return hasAte;
    }

    public void setHasAte(boolean hasAte) {
        this.hasAte = hasAte;
    }

    public MessPresent(){ }
    public MessPresent(boolean hasAte) {
        this.hasAte = hasAte;
    }
}
