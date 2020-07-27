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


    //计算最大可用位置可用率
    private void getMaxPositionRateNum() {
        double currentPositionRate = getParkCarPlaces().get(getCurrentParkCarPlace()).getEmptyPlace() / (double) getParkCarPlaces().get(0).ALLPALCE;
        int maxPositionRateNum = getCurrentParkCarPlace();
        double maxPositionRate = currentPositionRate;
        for (int i = 0; i < this.getParkCarPlaces().size(); i++) {
            double positionRate = getParkCarPlaces().get(i).getEmptyPlace() / (double) getParkCarPlaces().get(i).ALLPALCE;
            if (positionRate > maxPositionRate) {
                maxPositionRate = positionRate;
                maxPositionRateNum = i;
            }
        }
        setCurrentParkCarPlace(maxPositionRateNum);
    }


}
