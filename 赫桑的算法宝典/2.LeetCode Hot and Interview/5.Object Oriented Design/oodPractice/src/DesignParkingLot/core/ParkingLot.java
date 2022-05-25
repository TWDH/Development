package DesignParkingLot.core;

import DesignParkingLot.input.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class ParkingLot {
    List<Level> levels;
    float hourlyRate;

    // constructor
    public ParkingLot(int numLevels, int numRows, int numSpots){
        levels = new ArrayList<>();
        for(int i = 0; i < numLevels; i++){
            Level level = new Level(numRows, numSpots);
            levels.add(level);
        }

    }

    public ParkingLot(List<Level> levels, float hourlyRate) {
        this.levels = levels;
        this.hourlyRate = hourlyRate;
    }

    public int getAvailableCount() {
        int totalCount = 0;

        // traverse Level
        for (Level level : levels) {
            List<Row> rows = level.getRows();
            // traverse Row
            for (Row row : rows) {
                int rowSpotSize = row.getSpots().size();
                totalCount += rowSpotSize;
            }
        }

        return totalCount;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        return null;
    }

    public List<Spot> findSpotForVehicle(Vehicle vehicle) {
        return null;
    }

    public void clearSpot(Ticket ticket) {

    }

    public float calculatePrice(Ticket ticket) {

        return 0;
    }


}
