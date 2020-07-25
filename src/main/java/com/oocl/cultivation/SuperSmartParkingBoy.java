package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket goParking(Car car) {
        if(getParkCarPlaces().get(0).getEmptyPlace() <= 0
            && getParkCarPlaces().get(1).getEmptyPlace() >= 0){
            setWrongMes("Not enough position");
            return null;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if(ticket == null){
            setWrongMes("Please provide your parking ticket");
            return null;
        }
        if(ticket.isOutDate()) return  null;
        for(Ticket ticket1 : tickets){
            if(ticket1.getTicketId().equals(ticket.getTicketId())){
                ticket.setOutDate(true);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
