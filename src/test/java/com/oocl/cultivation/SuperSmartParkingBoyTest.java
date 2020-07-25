package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {

    // test AC1_1 && AC1_2
    @Test
    void should_return_ticket_when_goParking_given_car() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = superSmartParkingBoy.goParking(car);

        //then
        assertEquals("666666", ticket.getTicketId());
    }

    @Test
    void should_return_car_when_fetchCar_given_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.goParking(new Car("666666"));

        //when
        Car car = superSmartParkingBoy.fetchCar(new Ticket("666666"));

        //then
        assertEquals("666666", car.getCarLicense());
    }

    //test AC1_3
    @Test
    void should_return_null_when_fetchCar() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when
        Car car = superSmartParkingBoy.fetchCar(null);

        //then
        assertEquals(null, car);

    }

    @Test
    void should_return_null_when_fetchCar_given_wrong_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("666666");
        superSmartParkingBoy.goParking(car);

        //when
        car = superSmartParkingBoy.fetchCar(new Ticket("666665"));

        //then
        assertEquals(null, car);
    }

    //test AC1_4

    @Test
    void should_return_null_when_fetchCar_given_used_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = superSmartParkingBoy.goParking(car);
        superSmartParkingBoy.fetchCar(ticket);

        //when
        car = superSmartParkingBoy.fetchCar(ticket);

        //then
        assertEquals(null , car);
    }

    //test AC1_5

    @Test
    void should_return_null_when_goParking_given_park_10_car_in_park_room_and_car() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("666666");
        superSmartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(0);
        superSmartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(0);

        //when
        Ticket ticket = superSmartParkingBoy.goParking(car);

        //then
        assertEquals(null , ticket);

    }

    // test AC2_1
    @Test
    void should_return_unrecognized_parking_ticket_when_fetchCar_and_queryMes_given_wrong_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car =  new Car("666666");
        Ticket ticket = superSmartParkingBoy.goParking(car);

        //when
        superSmartParkingBoy.fetchCar(new Ticket("666665"));
        String mes = superSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Unrecognized parking ticket" , mes);
    }

    //test AC2_2

    @Test
    void should_return_please_provide_your_parking_ticket_ticket_when_fetchCar_and_queryMes_given() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when
        superSmartParkingBoy.fetchCar(null);
        String mes = superSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Please provide your parking ticket" , mes);
    }

    //test AC2_3

    @Test
    void should_return_not_enough_position_when_goParking_and_queryMes_given_car_and_park_zoom_have_10() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.getParkCarPlaces().get(0).setEmptyPlace(0);
        superSmartParkingBoy.getParkCarPlaces().get(1).setEmptyPlace(0);
        Car car = new Car("666666");

        //when
        superSmartParkingBoy.goParking(car);
        String mes = superSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Not enough position" , mes);
    }

    // test AC5_1
    /*  Story 5
    *   given : parkBoy , parkCarPlace0Has7Place , parkCarPlace1Has3Place;
    *   when : goParking
    *   then : return 0;
    *
    *   given : parkBoy , parkCarPlace0Has3Place , parkCarPlace1Has7Place;
    *   when : goParking
    *   then : return 1;
    * */


}

