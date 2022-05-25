package DesignParkingLot.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Row {
    List<Spot> spots;

    public Row(int numSpots) {
        spots = new ArrayList<>();
        for(int i = 0; i < numSpots; i++){
            Spot spot = new Spot();
            spots.add(spot);
        }
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
