package com.oocl.cultivation;

public class SmartParkingBoy  extends ParkingBoy{

    @Override
    public Ticket goParking(Car car) {
        return new Ticket(car.getCarLicense());
    }

    @Override
    public Car fetchCar(Ticket ticket) {

        return new Car(ticket.getTicketId());
    }
}
