package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        if (isNotEnoughPostition()) {
            return null;
        }
        selectMaxEmptyPlaceNum();
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
        setCurrentParkCarPlace(maxEmptyPlaceNum);
    }


}
