package DesignRestaurant.core;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Table {
    private boolean available;
    private int availableSeats;

    public Table(boolean available, int availableSeats) {
        this.available = available;
        this.availableSeats = availableSeats;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
