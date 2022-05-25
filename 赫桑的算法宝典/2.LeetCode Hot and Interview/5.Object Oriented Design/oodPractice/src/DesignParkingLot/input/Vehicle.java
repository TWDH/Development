package DesignParkingLot.input;

import DesignParkingLot.enums.VehicleSize;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public abstract class Vehicle {
    protected int vehicleSize;

    public int getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(int vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
}
