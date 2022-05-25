package DesignParkingLot.core;

import DesignParkingLot.input.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Ticket {
    private Vehicle vehicle;

    private List<Spot> spots;

    private Date startTime;

    public Ticket(Vehicle vehicle, List<Spot> spots, Date startTime) {
        this.vehicle = vehicle;
        this.spots = spots;
        this.startTime = startTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
