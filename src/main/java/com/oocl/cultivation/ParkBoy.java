package com.oocl.cultivation;

public class ParkBoy {

    public Ticket goParking(Car car) {
        return new Ticket(car.getCarLicense());
    }

    public Car fetchCar(Ticket ticket) {
        return new Car("123456");
    }
}
