package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        getMaxPositionRateNum();
        if(isNotEnoughPostition()){
            return null;
        }
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        return ticket;
    }

    //计算是否有足够的停车位置
    private boolean isNotEnoughPostition() {
        for(int i = 0; i < getParkCarPlaces().size(); i ++){
            if(getParkCarPlaces().get(i).getEmptyPlace() != 0)  return false;
        }
        setWrongMes("Not enough position");
        return true;
    }

    //计算最大可用位置可用率
    private void getMaxPositionRateNum() {
        double currentPositionRate = getParkCarPlaces().get(currentParkCarPlace).getEmptyPlace() / (double) getParkCarPlaces().get(0).ALLPALCE;
        int maxPositionRateNum = currentParkCarPlace;
        double maxPositionRate = currentPositionRate;
        for (int i = 0; i < this.getParkCarPlaces().size(); i++) {
            double positionRate = getParkCarPlaces().get(i).getEmptyPlace() / (double) getParkCarPlaces().get(i).ALLPALCE;
            if (positionRate > maxPositionRate) {
                maxPositionRate = positionRate;
                maxPositionRateNum = i;
            }
        }
        currentParkCarPlace = maxPositionRateNum;
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if (ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return null;
        }
        if (ticket.isOutDate()) return null;
        for (Ticket ticket1 : tickets) {
            if (ticket1.getTicketId().equals(ticket.getTicketId())) {
                ticket.setOutDate(true);
                int emptyPlace = this.getParkCarPlaces().get(this.currentParkCarPlace).getEmptyPlace() + 1;
                this.getParkCarPlaces().get(this.currentParkCarPlace).setEmptyPlace(emptyPlace);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
