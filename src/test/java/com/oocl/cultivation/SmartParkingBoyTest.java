package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    // Story 1 AC1 && AC2
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

    //Story 1 AC3
    @Test
    void should_return_null_when_fetchCar() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when
        Car car = smartParkingBoy.fetchCar(null);

        //then
        assertEquals(null , car);
    }

    @Test
    void should_return_null_when_fetchCar_given_wrong_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.goParking(car);

        //when
         car = smartParkingBoy.fetchCar(new Ticket("666665"));

        //then
        assertEquals(null , car);
    }

    // Story 1 AC4


    @Test
    void should_return_null_when_fetchCar_given_used_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = smartParkingBoy.goParking(car);
        smartParkingBoy.fetchCar(ticket);

        //when
        car = smartParkingBoy.fetchCar(ticket);

        //then
        assertEquals(null , car);
    }
}