package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    // Story AC1
    @Test
    void should_return_ticket_when_goParking_given_car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = smartParkingBoy.goParking(car);

        //then
        assertEquals("666666", ticket.getTicketId());
    }

    //Story AC1

    @Test
    void should_return_car_when_fetchCar_given_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = smartParkingBoy.goParking(car);

        //when
        car = smartParkingBoy.fetchCar(ticket);

        //then
        assertEquals("666666", car.getCarLicense());
    }

}