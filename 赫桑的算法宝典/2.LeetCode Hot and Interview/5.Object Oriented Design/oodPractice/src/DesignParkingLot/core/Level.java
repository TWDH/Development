package DesignParkingLot.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Level {
    private List<Row> rows;
    private int availableCount;

    // constructor
    public Level (int numRow, int numSpots, int curLevel){
        rows = new ArrayList<>();
        for (int i = 0; i < numRow; i++) {
            Row row = new Row(numSpots, curLevel);
            rows.add(row);
        }
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getAvailableCount() {
        return this.availableCount;
    }

    public void updateAvailableCount(int diff) {

    }

}
