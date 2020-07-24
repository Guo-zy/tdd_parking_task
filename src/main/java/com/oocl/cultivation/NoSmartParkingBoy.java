package com.oocl.cultivation;



public class NoSmartParkingBoy extends ParkingBoy{



    @Override
    public Ticket goParking(Car car) {
        if(this.parkCarPlaces.get(this.parkCarPlaces.size() - 1).carCountInParkRoom >= 10){
            setWrongMes("Not enough position");
            return null;
        }
        if((this.parkCarPlaces.get(currentParkCarPlace).carCountInParkRoom >= 10)){
            currentParkCarPlace ++;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        tickets.add(ticket);
        this.parkCarPlaces.get(currentParkCarPlace).carCountInParkRoom ++;
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
