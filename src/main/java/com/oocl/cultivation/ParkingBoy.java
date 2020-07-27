package com.oocl.cultivation;


import java.util.ArrayList;
import java.util.List;

public abstract  class ParkingBoy {
    private List<Ticket> tickets;
    private List<ParkCarPlace> parkCarPlaces;
    private String wrongMes;
    private  int currentParkCarPlace; // 当前停车男孩应该停车的停车场编号

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

    //停车并给票
    protected Ticket parKing(Car car) {
        Ticket ticket = new Ticket(car.getCarLicense());
        getTickets().add(ticket);
        int emptyPlace = getParkCarPlaces().get(getCurrentParkCarPlace()).getEmptyPlace() - 1;
        getParkCarPlaces().get(getCurrentParkCarPlace()).setEmptyPlace(emptyPlace);
        return ticket;
    }

    //根据票给客户取车
    protected Car fetch(Ticket ticket) {
        ticket.setOutDate(true);
        int emptyPlace = this.getParkCarPlaces().get(getCurrentParkCarPlace()).getEmptyPlace() + 1;
        this.getParkCarPlaces().get(getCurrentParkCarPlace()).setEmptyPlace(emptyPlace);
        return  new Car(ticket.getTicketId());
    }

    //判断票是否错误
    protected boolean ticketIsWrong(Ticket ticket) {
        for (Ticket ticket_used : getTickets() ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) {
                return false;
            }
        }
        setWrongMes("Unrecognized parking ticket");
        return true;
    }

    //判断票为空
    protected boolean ticketIsNull(Ticket ticket) {
        if(ticket == null) {
            setWrongMes("Please provide your parking ticket");
            return true;
        }
        return false;
    }

    //计算是否有足够的停车位置
    protected boolean isNotEnoughPostition() {
        for (int i = 0; i < getParkCarPlaces().size(); i++) {
            if (getParkCarPlaces().get(i).getEmptyPlace() != 0) return false;
        }
        setWrongMes("Not enough position");
        return true;
    }


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

    public void setCurrentParkCarPlace(int currentParkCarPlace) {
        this.currentParkCarPlace = currentParkCarPlace;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
