package com.oocl.cultivation;

public class Ticket {
    private String ticketId;
    private boolean isOutDate;

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
        this.isOutDate = false;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isOutDate() {
        return isOutDate;
    }

    public void setOutDate(boolean outDate) {
        isOutDate = outDate;
    }
}
