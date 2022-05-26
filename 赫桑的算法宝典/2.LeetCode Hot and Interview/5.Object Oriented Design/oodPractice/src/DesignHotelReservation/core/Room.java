package DesignHotelReservation.core;

import DesignHotelReservation.enums.RoomType;

public class Room {
    RoomType roomType;
    Boolean available;

    public Room(RoomType roomType, Boolean available) {
        this.roomType = roomType;
        this.available = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType=" + roomType +
                ", available=" + available +
                '}';
    }
}
