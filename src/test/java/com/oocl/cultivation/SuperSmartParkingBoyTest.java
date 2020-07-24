package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {

        // test AC1_1
    @Test
    void should_return_ticket_when_goParking_given_car() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("666666");

        //when
        Ticket ticket = superSmartParkingBoy.goParking(car);

        //then
        assertEquals("666666",ticket.getTicketId());
    }
}