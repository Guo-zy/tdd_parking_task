package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.TIMEOUT;

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
        smartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(0);
        smartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(0);

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

    //Story 2 AC3

    @Test
    void should_return_not_enough_position_when_goParking_and_queryMes_given_car_and_park_zoom_have_10() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(0);
        smartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(0);

        //when
        Ticket ticket = smartParkingBoy.goParking(car);
        String mes = smartParkingBoy.getWrongMes();

        //then
        assertEquals("Not enough position", mes);
    }

    /*  Story 4 AC1
    *   given SmartParkingBoy, carParkPlace0(have 3 car) , carParkPlace1(have 5 car)
    *   when goParking
    *   then return 0
    *
    *   given SmartParkingBoy, carParkPlace0(have 5 car) , carParkPlace1(have 3 car)
    *   when goParking
    *   then return 1
    * */

    @Test
    void should_return_0_when_goParking_given_parkCarPlace0Have3Car_and_parkCarPlace1Have5Car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(7);
        smartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(5);

        //when
        smartParkingBoy.goParking(car);
        int selectParkCarPlaceNum = smartParkingBoy.getCurrentParkCarPlace();

        //then
        assertEquals(0 , selectParkCarPlaceNum);
    }

    @Test
    void should_return_1_when_goParking_given_parkCarPlace0Have5Car_and_parkCarPlace1Have3Car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        smartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(5);
        smartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(7);

        //when
        smartParkingBoy.goParking(car);
        int selectParkCarPlaceNum = smartParkingBoy.getCurrentParkCarPlace();

        //then
        assertEquals(1 , selectParkCarPlaceNum);
    }

    /*
     * given parkBoy , car , parkCarPlace0Has7Place
     * when fecthCar
     * then return 8
     * */

    @Test
    void should_return_6_when_fetchCar_given_parCarPlace0Has7Place_and_car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = smartParkingBoy.goParking(car);
        smartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(7);

        //when
        smartParkingBoy.fetchCar(ticket);

        //then
        assertEquals(8 , smartParkingBoy.getParkCarPlaces().get(0).getEmptyPlace());
    }


}