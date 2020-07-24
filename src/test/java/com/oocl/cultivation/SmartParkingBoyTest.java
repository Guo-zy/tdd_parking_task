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

    //Story 1 AC5
    @Test
    void should_return_null_when_goParking_given_park_10_car_in_park_room_and_car() {
        //given
        SmartParkingBoy smartParkingBoy  = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.getParkCarPlaces().get(1).setCarCountInParkRoom(10);

        //when
        Ticket ticket = smartParkingBoy.goParking(car);

        //then
        assertEquals(null , ticket);
    }
    
    //Story 2 AC1

    @Test
    void should_return_unrecognized_parking_ticket_when_fetchCar_and_queryMes_given_wrong_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.goParking(car);

        //when
        smartParkingBoy.fetchCar(new Ticket("666665"));
        String mes = smartParkingBoy.getWrongMes();

        //then
        assertEquals("Unrecognized parking ticket" , mes);
    }

    //Story 2 AC2


    @Test
    void should_return_please_provide_your_parking_ticket_ticket_when_fetchCar_and_queryMes_given() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when
        smartParkingBoy.fetchCar(null);
        String mes = smartParkingBoy.getWrongMes();

        //then
        assertEquals("Please provide your parking ticket" , mes);

    }
}