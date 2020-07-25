package com.oocl.cultivation;


import java.util.ArrayList;
import java.util.List;

public abstract  class ParkingBoy {
    protected List<Ticket> tickets;
    protected List<ParkCarPlace> parkCarPlaces;
    protected String wrongMes;
    protected  int currentParkCarPlace; // 当前停车男孩应该停车的停车场编号

    public ParkingBoy(int parkCarPlaceCount) {
        this.tickets = new ArrayList<>();
        this.parkCarPlaces = new ArrayList<>(parkCarPlaceCount);
        this.currentParkCarPlace = 0;
        for(int i = 0; i < parkCarPlaceCount; i ++){
            this.parkCarPlaces.add(new ParkCarPlace());
        }
    }

    public abstract  Ticket goParking(Car car);


    public abstract Car fetchCar(Ticket ticket) ;


    public void setWrongMes(String wrongMes) {
        this.wrongMes = wrongMes;
    }

    public String getWrongMes() {
        return wrongMes;
    }

    public List<ParkCarPlace> getParkCarPlaces() {
        return parkCarPlaces;
    }

    public int getCurrentParkCarPlace() {
        return currentParkCarPlace;
    }
}
