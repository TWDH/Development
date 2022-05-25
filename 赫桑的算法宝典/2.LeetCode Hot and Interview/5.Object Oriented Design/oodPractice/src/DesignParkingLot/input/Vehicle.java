package DesignParkingLot.input;

import DesignParkingLot.enums.VehicleSize;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public abstract class Vehicle {
    protected int spotNeeded;
    protected VehicleSize vehicleSize;
    protected String licencePlate;

    public VehicleSize getVehicleSize() {
        return this.vehicleSize;
    }
}
