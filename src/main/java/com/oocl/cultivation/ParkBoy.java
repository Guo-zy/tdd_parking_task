package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkBoy {

    private List<Ticket> tickets;

    public ParkBoy() {
        this.tickets = new ArrayList<>();
    }

    public Ticket goParking(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        tickets.add(ticket);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) {
        if(ticket == null) return null;
        for (Ticket ticket_used : tickets ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) return new Car(ticket.getTicketId());
        }
        return null;
    }
}
