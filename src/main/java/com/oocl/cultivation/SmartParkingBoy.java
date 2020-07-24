package com.oocl.cultivation;

public class SmartParkingBoy  extends ParkingBoy{

    @Override
    public Ticket goParking(Car car) {
        if(this.getParkCarPlaces().get(1).getCarCountInParkRoom() >= 10) return null;
        if(this.getParkCarPlaces().get(0).getCarCountInParkRoom() >= 10){
            this.currentParkCarPlace ++;
        }
        Ticket ticket =  new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        this.getParkCarPlaces().get(currentParkCarPlace).carCountInParkRoom ++;
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if(ticket == null) return null;
        if(ticket.isOutDate()) return null;
        for(Ticket ticket1 : this.tickets){
            if(ticket1.getTicketId().equals(ticket.getTicketId())){
                ticket.setOutDate(true);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
