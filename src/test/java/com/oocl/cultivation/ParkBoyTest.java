package com.oocl.cultivation;

import javafx.scene.shape.TriangleMesh;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ParkBoyTest {
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
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = parkBoy.goParking(car);

        //then
        assertEquals("666666", ticket.getTicketId());

    }

    @Test
    void should_return_car_when_fetchCar_given_ticket() {
        //given
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");
        Ticket ticket = parkBoy.goParking(car);

        //when
        Car fetched_car = parkBoy.fetchCar(ticket);

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
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");

        //when
        Car fetched_car = parkBoy.fetchCar(null);

        //then
        assertEquals(null , fetched_car);
    }

    @Test
    void should_return_null_when_fetchCar_given_wrong_ticket() {
        //given
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");
        Ticket ticket = parkBoy.goParking(car);

        //when
        Car fetched_car = parkBoy.fetchCar(new Ticket("666665"));

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
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");
        Ticket ticket = parkBoy.goParking(car);
        parkBoy.fetchCar(ticket);

        //when
        Car fetched_car = parkBoy.fetchCar(ticket);

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
        ParkBoy parkBoy = new ParkBoy();
        parkBoy.setCarCountInParkRoom(11);
        Car car = new Car("666666");

        //when
        Ticket ticket = parkBoy.goParking(car);

        //then
        assertEquals(null, ticket);
    }


    /*
    * Story 2 AC1
    *   given : parkBoy  ,  wrong ticket
    *   when :  queryMes
    *   then : Unrecognized parking ticket
    * */


    /* Story 2 AC2
    *  given : parkBoy
    *  when : queryMes
    *  then : Please provide your parking ticket.
    * */


    /*
    * Story 2 AC3
    * given : parkBoy , parking zoom(have 10 car) , car
    * when : goParking , queryMes
    * then : Not enough position.
    * */
}