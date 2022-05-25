package DesignParkingLot;

import DesignParkingLot.core.ParkingLot;
import DesignParkingLot.core.Ticket;
import DesignParkingLot.input.Car;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class mainParkingLot {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5, 10, 10);
        Car car = new Car();
        parkingLot.getAvailableCount();
        Ticket ticket = parkingLot.parkVehicle(car);
        Ticket ticket2 = parkingLot.parkVehicle(car);
        Ticket ticket3 = parkingLot.parkVehicle(car);
        parkingLot.getAvailableCount();
        parkingLot.clearSpot(ticket);
        parkingLot.getAvailableCount();
    }
}
