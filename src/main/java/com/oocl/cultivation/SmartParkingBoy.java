package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        int parkCarPlace0HasCar = this.getParkCarPlaces().get(0).getEmptyPlace();
        int parkCarPlace1HasCar = this.getParkCarPlaces().get(1).getEmptyPlace();
        if(parkCarPlace0HasCar != parkCarPlace1HasCar){
            currentParkCarPlace = parkCarPlace0HasCar > parkCarPlace1HasCar ? 0 : 1;
        }
        if (this.getParkCarPlaces().get(0).getEmptyPlace() <= 0
                && this.getParkCarPlaces().get(1).getEmptyPlace() <= 0) {
            setWrongMes("Not enough position");
            return null;
        }
        if (this.getParkCarPlaces().get(0).getEmptyPlace() <= 0 ) {
            this.currentParkCarPlace++;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        int emptyPlace = this.getParkCarPlaces().get(currentParkCarPlace).getEmptyPlace() - 1;
        this.getParkCarPlaces().get(currentParkCarPlace).setEmptyPlace(emptyPlace);
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
                int emptyPlace  = this.getParkCarPlaces().get(this.currentParkCarPlace).getEmptyPlace() + 1;
                this.getParkCarPlaces().get(this.currentParkCarPlace).setEmptyPlace(emptyPlace);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
