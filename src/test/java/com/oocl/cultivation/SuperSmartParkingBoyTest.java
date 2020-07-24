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
}

