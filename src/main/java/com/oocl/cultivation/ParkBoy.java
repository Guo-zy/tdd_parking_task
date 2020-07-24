package com.oocl.cultivation;

public class ParkBoy {

    public Ticket goParking(Car car) {
        return new Ticket(car.getCarLicense());
    }
}
