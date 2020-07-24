package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket goParking(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        return new Car(ticket.getTicketId());
    }
}
