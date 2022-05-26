package DesignHotelReservation.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private List<Room> rooms;
    private Date startDate;
    private Date endDate;

    public Reservation(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        rooms = new ArrayList<>();
    }

    public Reservation(List<Room> rooms, Date startDate, Date endDate) {
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "rooms=" + rooms +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
