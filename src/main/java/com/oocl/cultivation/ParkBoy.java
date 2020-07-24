package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkBoy {

    private List<Ticket> tickets;
    private int carCountInParkRoom;
    private String wrongMes;

    public ParkBoy() {
        this.tickets = new ArrayList<>();
        this.carCountInParkRoom = 0;
    }

    public Ticket goParking(Car car) {
        if(getCarCountInParkRoom() > 10) return null;
        Ticket ticket = new Ticket(car.getCarLicense());
        tickets.add(ticket);
        carCountInParkRoom ++;
        return ticket;
    }

    public Car fetchCar(Ticket ticket) {
        if(ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return null;
        }
        if(ticket.isOutDate()) return null;
        for (Ticket ticket_used : tickets ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) {
                ticket.setOutDate(true);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }

    public int getCarCountInParkRoom() {
        return carCountInParkRoom;
    }

    public void setCarCountInParkRoom(int carCountInParkRoom) {
        this.carCountInParkRoom = carCountInParkRoom;
    }

    public void setWrongMes(String wrongMes) {
        this.wrongMes = wrongMes;
    }

    public String getWrongMes() {
        return wrongMes;
    }
}
