package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        selectMaxEmptyPlaceNum();
        if (isNotEnoughPostition()) {
            return null;
        }
        if (isCurrentParkCarPlaceFull()) {
                return null;
        }
        Ticket ticket = parKing(car);
        return ticket;
    }


    //计算当前停车场是否为满
    private  boolean isCurrentParkCarPlaceFull(){
        return this.getParkCarPlaces().get(currentParkCarPlace).getEmptyPlace() <= 0;
    }

    //计算哪个停车场最优
    private void selectMaxEmptyPlaceNum() {
        int currentEmptyPlaceNum = getParkCarPlaces().get(getCurrentParkCarPlace()).getEmptyPlace();
        int maxEmptyPlaceNum = getCurrentParkCarPlace();
        for (int i = 0; i < this.getParkCarPlaces().size(); i++) {
            int emptyPlaceNum = getParkCarPlaces().get(i).getEmptyPlace();
            if (emptyPlaceNum > currentEmptyPlaceNum) {
                currentEmptyPlaceNum = emptyPlaceNum;
                maxEmptyPlaceNum = i;
            }
        }
        currentParkCarPlace = maxEmptyPlaceNum;
    }

    //停车并给票
    private Ticket parKing(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        this.tickets.add(ticket);
        int emptyPlace = getParkCarPlaces().get(currentParkCarPlace).getEmptyPlace() - 1;
        getParkCarPlaces().get(currentParkCarPlace).setEmptyPlace(emptyPlace);
        return ticket;
    }

    //计算是否有足够的停车位置
    private boolean isNotEnoughPostition() {
        for (int i = 0; i < getParkCarPlaces().size(); i++) {
            if (getParkCarPlaces().get(i).getEmptyPlace() != 0) return false;
        }
        setWrongMes("Not enough position");
        return true;
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
                int emptyPlace = this.getParkCarPlaces().get(this.currentParkCarPlace).getEmptyPlace() + 1;
                this.getParkCarPlaces().get(this.currentParkCarPlace).setEmptyPlace(emptyPlace);
                return new Car(ticket.getTicketId());
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return null;
    }
}
