package DesignParkingLot.core;

import DesignParkingLot.input.Vehicle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
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
    public ParkingLot(int numLevels, int numRows, int numSpots) {
        levels = new ArrayList<>();
        for (int i = 0; i < numLevels; i++) {
            // i: current level
            Level level = new Level(numRows, numSpots, i);
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
                List<Spot> spots = row.getSpots();
                for (Spot spot : spots) {
                    if (spot.isAvailable()) {
                        totalCount += 1;
                    }
                }
            }
        }

        System.out.println("The total available spots: " + totalCount);

        return totalCount;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        List<Spot> spotForVehicle = findSpotForVehicle(vehicle);
        Date now = new Date();

        Ticket ticket = new Ticket(vehicle, spotForVehicle, now);

        System.out.println("Park Vehicle Successfully!" );
        System.out.println(ticket.toString());

        return ticket;
    }

    private List<Spot> findSpotForVehicle(Vehicle vehicle) {
        List<Spot> resultSpots = new ArrayList<>();
        for (Level level : levels) {
            List<Row> rows = level.getRows();
            for (Row row : rows) {
                List<Spot> spots = row.getSpots();
                // find spots in each Level and each Row
                Pair<Integer, List<Spot>> findAvailableSpots = findAvailableSpots(spots);
                Integer availableSpotsCount = findAvailableSpots.getKey();
                List<Spot> availableSpots = findAvailableSpots.getValue();

                // make sure enough parking spot
                if (availableSpotsCount >= vehicle.getVehicleSize()) {
                    // set spot to unavailable
                    int vehicleSize = vehicle.getVehicleSize();
                    for (int i = 0; i < vehicleSize; i++) {
                        Spot spot = availableSpots.get(i);
                        spot.setAvailable(false);
                        resultSpots.add(spot);
                    }
                    return resultSpots;
                }
            }
        }
        return resultSpots;
    }

    private Pair<Integer, List<Spot>> findAvailableSpots(List<Spot> spots) {
        int availableSpotsCount = 0;
        List<Spot> availableSpots = new ArrayList<>();
        for (Spot spot : spots) {
            if (spot.isAvailable()) {
                availableSpotsCount++;
                availableSpots.add(spot);
            }
        }

        return new Pair<Integer, List<Spot>>(availableSpotsCount, availableSpots);
    }

    public void clearSpot(Ticket ticket) {
        List<Spot> spots = ticket.getSpots();
        for (Spot spot : spots) {
            spot.setAvailable(true);
        }
    }

    public float calculatePrice(Ticket ticket) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        Date startTime = ticket.getStartTime();
        Date now = new Date();

        long parkTime = now.getTime() - startTime.getTime();

        long hour = parkTime % nd / nh;

        float fee = hour * hourlyRate;

        return fee;
    }
}
