package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkBoy {

    private List<Ticket> tickets;
    private List<ParkCarPlace> parkCarPlaces;
    private String wrongMes;
    protected  int currentParkCarPlace;

    public ParkBoy() {
        this.tickets = new ArrayList<>();
        this.parkCarPlaces = new ArrayList<>(2);
        this.currentParkCarPlace = 0;
        this.parkCarPlaces.add(new ParkCarPlace());
        this.parkCarPlaces.add(new ParkCarPlace());
    }

    public Ticket goParking(Car car) {
        if(this.parkCarPlaces.get(this.parkCarPlaces.size() - 1).carCountInParkRoom >= 10){
            setWrongMes("Not enough position");
            return null;
        }
        if((this.parkCarPlaces.get(currentParkCarPlace).carCountInParkRoom >= 10)){
            currentParkCarPlace ++;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        tickets.add(ticket);
       this.parkCarPlaces.get(currentParkCarPlace).carCountInParkRoom ++;
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

    public void setWrongMes(String wrongMes) {
        this.wrongMes = wrongMes;
    }

    public String getWrongMes() {
        return wrongMes;
    }

    public List<ParkCarPlace> getParkCarPlaces() {
        return parkCarPlaces;
    }
}
