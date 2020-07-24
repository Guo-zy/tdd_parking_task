package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

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
    void shoule_return_ticket_when_goParking_given_car() {
        //given
        ParkBoy parkBoy = new ParkBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = parkBoy.goParking(car);

        //then
        assertEquals("666666", ticket.getTicketId());

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


 /*
 * Story 1 AC4
 * given : usedTicket , parkBoy
 * when : fetchCar
 * then : return null
 *
 * */


    /*
    * Story 1 AC5
    * given : parkBoy , parking zoom(have 10 car) , car
    * when : goParking
    * then : return null
    *
    * */

}