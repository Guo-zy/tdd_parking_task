package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket goParking(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if(ticket == null) return null;
        for(Ticket ticket1 : tickets){
            if(ticket1.getTicketId().equals(ticket.getTicketId())){
                return new Car(ticket.getTicketId());
            }
        }
        return null;
    }
}
