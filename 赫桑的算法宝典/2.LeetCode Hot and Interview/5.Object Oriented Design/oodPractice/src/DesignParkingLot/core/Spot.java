package DesignParkingLot.core;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Spot {

    private boolean isAvailable;
    private int level;

    public Spot(boolean isAvailable, int level) {
        this.isAvailable = isAvailable;
        this.level = level;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
