package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoSmartParkingBoyTest {
/*
*  Story 1 AC1 && Story 2 AC2
* given:  car , parkBoy
* when: goParking
* then: return ticket
*
* given: ticket , parkBoy
* when: fetchCar
* then: return car
* */

    @Test
    void should_return_ticket_when_goParking_given_car() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = noSmartParkingBoy.goParking(car);

        //then
        assertEquals("666666", ticket.getTicketId());

    }

    @Test
    void should_return_car_when_fetchCar_given_ticket() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = noSmartParkingBoy.goParking(car);

        //when
        Car fetched_car = noSmartParkingBoy.fetchCar(ticket);

        //then
        assertEquals(fetched_car.getCarLicense() , car.getCarLicense());

    }

    /*
*   Story 1 AC3
* given: wrong ticket , parkBoy
* when: fetchCar
* then: return null
*
* given: parkBoy
* when: fetchCar
* then: return null
* */

    @Test
    void should_return_null_when_fetchCar() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");

        //when
        Car fetched_car = noSmartParkingBoy.fetchCar(null);

        //then
        assertEquals(null , fetched_car);
    }

    @Test
    void should_return_null_when_fetchCar_given_wrong_ticket() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = noSmartParkingBoy.goParking(car);

        //when
        Car fetched_car = noSmartParkingBoy.fetchCar(new Ticket("666665"));

        //given
        assertEquals(null , fetched_car);
    }

    /*
 * Story 1 AC4
 * given : usedTicket , parkBoy
 * when : fetchCar
 * then : return null
 *
 * */

    @Test
    void should_return_null_when_fetchCar_given_used_ticket() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");
        Ticket ticket = noSmartParkingBoy.goParking(car);
        noSmartParkingBoy.fetchCar(ticket);

        //when
        Car fetched_car = noSmartParkingBoy.fetchCar(ticket);

        //then
        assertEquals(null , fetched_car);
    }

    /*
    * Story 1 AC5
    * given : parkBoy , parking zoom(have 10 car) , car
    * when : goParking
    * then : return null
    *
    * */

    @Test
    void should_return_null_when_goParking_given_park_10_car_in_park_room_and_car() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        noSmartParkingBoy.getParkCarPlaces().get(1).carCountInParkRoom = 10;
        Car car = new Car("666666");

        //when
        Ticket ticket = noSmartParkingBoy.goParking(car);

        //then
        assertEquals(null, ticket);
    }


    /*
    * Story 2 AC1
    *   given : parkBoy  ,  wrong ticket
    *   when :  getWrongMes , fetchCar
    *   then : Unrecognized parking ticket
    * */

    @Test
    void should_return_unrecognized_parking_ticket_when_fetchCar_and_queryMes_given_wrong_ticket() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");
        noSmartParkingBoy.goParking(car);
        Ticket wrongTicket = new Ticket("666665");

        //when
        noSmartParkingBoy.fetchCar(wrongTicket);
        String mes = noSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Unrecognized parking ticket" , mes);
    }

    /* Story 2 AC2
    *  given : parkBoy
    *  when : getWrongMes
    *  then : Please provide your parking ticket.
    * */

    @Test
    void should_return_please_provide_your_parking_ticket_ticket_when_fetchCar_and_queryMes_given() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();

        //when
        noSmartParkingBoy.fetchCar(null);
        String mes = noSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Please provide your parking ticket" , mes);
    }

    /*
    * Story 2 AC3
    * given : parkBoy , parking zoom(have 10 car) , car
    * when : goParking , getWrongMes
    * then : Not enough position.
    * */

    @Test
    void should_return_not_enough_position_when_goParking_and_queryMes_given_car_and_park_zoom_have_10() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        noSmartParkingBoy.getParkCarPlaces().get(1).carCountInParkRoom = 10;
        Car car = new Car("666666");

        //when
        Ticket ticket = noSmartParkingBoy.goParking(car);
        String mes = noSmartParkingBoy.getWrongMes();

        //then
        assertEquals("Not enough position", mes);
    }

    /* Story 3 AC1
    *  given car , parkCarPlace1(have 10 car) , parkBoy
    *  when goParking
    *  then return ticket
    *
    * */

    @Test
    void should_return_ticket_when_goParking_given_car_and_park_car_place1_have_10() {
        //given
        ParkingBoy noSmartParkingBoy = new NoSmartParkingBoy();
        Car car = new Car("666666");
        noSmartParkingBoy.getParkCarPlaces().get(0).setCarCountInParkRoom(10);

        //when
        Ticket ticket = noSmartParkingBoy.goParking(car);

        //then
        assertEquals("666666" , ticket.getTicketId());
    }


}