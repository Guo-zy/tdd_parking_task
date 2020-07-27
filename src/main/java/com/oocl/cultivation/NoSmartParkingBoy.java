package com.oocl.cultivation;

public class NoSmartParkingBoy extends ParkingBoy{


    public NoSmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        //TODO 按顺序找第一个空
        if(isFullInLastPlace()){
            return null;
        }
        if(isCurrentParkCarPlaceFull()){
            selectNextParkCarPlace();
        }
        return parKing(car);
    }

    @Override
    public Car fetchCar(Ticket ticket) {
        if(ticketIsNull(ticket)){
            return null;
        }
        if(ticket.isOutDate()) return null;
        if(ticketIsWrong(ticket)){
            return null;
        }
        return fetch(ticket);

    }

    //选择下一个停车场
    private void selectNextParkCarPlace() {
        setCurrentParkCarPlace(getCurrentParkCarPlace() + 1);
    }

    //当前停车场是否满了
    private boolean isCurrentParkCarPlaceFull() {
        return getParkCarPlaces().get(getCurrentParkCarPlace()).getEmptyPlace() <= 0;
    }


    //判断最后一个停车场是否满
    private boolean isFullInLastPlace() {
        if (getParkCarPlaces().get(getParkCarPlaces().size() - 1).getEmptyPlace() <= 0){
            setWrongMes("Not enough position");
            return true;
        }
        return false;
    }



}
