package DesignParkingLot.core;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class Spot {

    private boolean isAvailable;
    private Level level;

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
