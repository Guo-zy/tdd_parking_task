package com.oocl.cultivation;

public class ParkBoy {

    public Ticket goParking(Car car) {
        return new Ticket(car.getCarLicense());
    }

    public Car fetchCar(Ticket ticket) {
        if(ticket == null) return null;
        return new Car(ticket.getTicketId());
    }
}
