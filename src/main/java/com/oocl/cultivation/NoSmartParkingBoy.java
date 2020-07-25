package com.oocl.cultivation;



public class NoSmartParkingBoy extends ParkingBoy{


    public NoSmartParkingBoy(int parkCarPlaceCount) {
        super(parkCarPlaceCount);
    }

    @Override
    public Ticket goParking(Car car) {
        if(isFullInLastPlace()){
            return null;
        }
        if(isCurrentParkCarPlaceFull()){
            selectNextParkCarPlace();
        }
        return parKing(car);
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
        if(ticketIsNull(ticket)){
            return null;
        }
        if(ticket.isOutDate()) return null;
        if(ticketIsWrong(ticket)){
            return null;
        }
            return new Car(ticket.getTicketId());

    }


    //判断票是否错误
    private boolean ticketIsWrong(Ticket ticket) {
        for (Ticket ticket_used : tickets ){
            if (ticket.getTicketId() == ticket_used.getTicketId()) {
                ticket.setOutDate(true);
                int emptyPlace = this.getParkCarPlaces().get(this.currentParkCarPlace).getEmptyPlace() + 1;
                this.getParkCarPlaces().get(this.currentParkCarPlace).setEmptyPlace(emptyPlace);
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
