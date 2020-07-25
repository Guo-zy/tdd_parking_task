package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        if(isNotEnoughPostition()){
            return null;
        }
        getMaxPositionRateNum();
        return parKing(car);
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if (ticketIsNull(ticket)) {
            return null;
        }
        if (ticket.isOutDate()) return null;
        if(ticketIsWrong(ticket)){
            return null;
        }
        return fetch(ticket);
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


    //根据票给客户取车
    private Car fetch(Ticket ticket) {
        ticket.setOutDate(true);
        int emptyPlace = this.getParkCarPlaces().get(this.currentParkCarPlace).getEmptyPlace() + 1;
        this.getParkCarPlaces().get(this.currentParkCarPlace).setEmptyPlace(emptyPlace);
        return  new Car(ticket.getTicketId());
    }

    //判断票是否错误
    private boolean ticketIsWrong(Ticket ticket) {
        for (Ticket ticket_used : tickets ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) {
                return false;
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return true;
    }

    //判断票为空
    private boolean ticketIsNull(Ticket ticket) {
        if(ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return true;
        }
        return false;
    }

}
