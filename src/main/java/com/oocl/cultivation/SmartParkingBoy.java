package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket goParking(Car car) {
        int parkCarPlace0HasCar = this.getParkCarPlaces().get(0).getCarCountInParkRoom();
        int parkCarPlace1HasCar = this.getParkCarPlaces().get(1).getCarCountInParkRoom();
        if(parkCarPlace0HasCar != parkCarPlace1HasCar){
            currentParkCarPlace = parkCarPlace0HasCar < parkCarPlace1HasCar ? 0 : 1;
        }
        if (this.getParkCarPlaces().get(0).getCarCountInParkRoom() >= 10
                && this.getParkCarPlaces().get(1).getCarCountInParkRoom() >= 10) {
            setWrongMes("Not enough position");
            return null;
        }
        if (this.getParkCarPlaces().get(0).getCarCountInParkRoom() >= 10) {
            this.currentParkCarPlace++;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        this.getParkCarPlaces().get(currentParkCarPlace).carCountInParkRoom++;
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if (ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return null;
        }
        if (ticket.isOutDate()) return null;
        for (Ticket ticket1 : this.tickets) {
            if (ticket1.getTicketId().equals(ticket.getTicketId())) {
                ticket.setOutDate(true);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
