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
        Ticket ticket = parKing(car);
        return ticket;
    }

    //停车并给票
    private Ticket parKing(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        int emptyPlace = getParkCarPlaces().get(currentParkCarPlace).getEmptyPlace() - 1;
        getParkCarPlaces().get(currentParkCarPlace).setEmptyPlace(emptyPlace);
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
