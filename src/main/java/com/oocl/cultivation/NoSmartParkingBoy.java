package com.oocl.cultivation;



public class NoSmartParkingBoy extends ParkingBoy{


    public NoSmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        if(this.parkCarPlaces.get(this.parkCarPlaces.size() - 1).getEmptyPlace() <= 0){
            setWrongMes("Not enough position");
            return null;
        }
        if((this.parkCarPlaces.get(currentParkCarPlace).getEmptyPlace() >= 0)){
            currentParkCarPlace ++;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        tickets.add(ticket);
        int emptyPlace =   this.parkCarPlaces.get(currentParkCarPlace).getEmptyPlace() - 1;
        this.parkCarPlaces.get(currentParkCarPlace).setEmptyPlace(emptyPlace);
        return ticket;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if(ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return null;
        }
        if(ticket.isOutDate()) return null;
        for (Ticket ticket_used : tickets ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) {
                ticket.setOutDate(true);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }


}
