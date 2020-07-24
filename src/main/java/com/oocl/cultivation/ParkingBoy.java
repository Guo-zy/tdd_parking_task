package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public abstract  class ParkingBoy {
    protected List<Ticket> tickets;
    protected List<ParkCarPlace> parkCarPlaces;
    protected String wrongMes;
    protected  int currentParkCarPlace;

    public ParkingBoy() {
        this.tickets = new ArrayList<>();
        this.parkCarPlaces = new ArrayList<>(2);
        this.currentParkCarPlace = 0;
        this.parkCarPlaces.add(new ParkCarPlace());
        this.parkCarPlaces.add(new ParkCarPlace());
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
}
