package DesignParkingLot.input;

import DesignParkingLot.enums.VehicleSize;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Car extends Vehicle{
    public Car(){
        this.spotNeeded = 1;
        this.vehicleSize = VehicleSize.Compact;
    }
}
