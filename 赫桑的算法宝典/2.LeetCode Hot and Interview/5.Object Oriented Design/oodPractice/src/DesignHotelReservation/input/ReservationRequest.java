package DesignHotelReservation.input;

import DesignHotelReservation.core.Room;
import DesignHotelReservation.enums.RoomType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReservationRequest {
    Map<RoomType, Integer> roomsNeeded;
    Date startDate;
    Date endDate;

    public ReservationRequest(Map<RoomType, Integer> roomsNeeded, Date startDate, Date endDate) {
        this.roomsNeeded = roomsNeeded;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Map<RoomType, Integer> getRoomsNeeded() {
        return roomsNeeded;
    }

    public void setRoomsNeeded(Map<RoomType, Integer> roomsNeeded) {
        this.roomsNeeded = roomsNeeded;
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
        return "ReservationRequest{" +
                "roomsNeeded=" + roomsNeeded +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
