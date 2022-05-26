package DesignHotelReservation.core;

import DesignHotelReservation.enums.RoomType;
import DesignHotelReservation.input.ReservationRequest;
import DesignHotelReservation.input.SearchRequest;

import java.util.*;

public class Hotel {
    // all rooms
    private List<Room> rooms;

    // all reservations
    private List<Reservation> reservations;

    // room that has been reserved
    Map<Room, List<Date>> roomReserved;


    // Handle Search request
    public Map<RoomType, Integer> handleSearchRequest(SearchRequest searchRequest) {
        Map<RoomType, Integer> searchResult = new HashMap<>();

        Date startDate = searchRequest.getStartDate();
        Date endDate = searchRequest.getEndDate();

        // 1. go through available rooms
        for (Room room : rooms) {
            // rooms already reserved
            List<Date> schedule = roomReserved.get(room);

            // current room is available (not reserved)
            if (isRequestAvailable(startDate, endDate, schedule)) {
                RoomType roomType = room.getRoomType();
                searchResult.put(roomType, searchResult.getOrDefault(roomType, 0) + 1);
            }
        }

        return searchResult;
    }

    // Auxiliary Function
    private boolean isRequestAvailable(Date startDate, Date endDate, List<Date> schedule) {
        if (startDate.getTime() >= schedule.get(0).getTime()){
            return false;
        }

        if (endDate.getTime() <= schedule.get(schedule.size() - 1).getTime()) {
            return false;
        }

        return true;
    }

    // Make Reservation
    public Reservation makeReservation(ReservationRequest reservationRequest) {
        // 1. reserve criteria
        Map<RoomType, Integer> roomsNeeded = reservationRequest.getRoomsNeeded();
        Date startDate = reservationRequest.getStartDate();
        Date endDate = reservationRequest.getEndDate();

        // 2. new reservation
        Reservation reservation = new Reservation(startDate, endDate);

        // 3. traverse available rooms
        for (Room room : rooms) {
            RoomType curRoomType = room.getRoomType();
            // how many we need for this roomType
            Integer count = roomsNeeded.get(curRoomType);
            // is this room fit
            if (roomsNeeded.containsKey(curRoomType) && count > 0) {
                // check room availability
                // rooms already reserved
                List<Date> schedule = roomReserved.get(room);
                // current room is available (not reserved)
                if (isRequestAvailable(startDate, endDate, schedule)) {
                    // reserve room
                    reservation.addRoom(room);
                    // minus count in roomsNeeded
                    count--;
                    roomsNeeded.put(curRoomType, count);
                    // update roomReserved(TODO use calendar)
                    List<Date> reservedDate = new ArrayList<>();
                    roomReserved.put(room, reservedDate);
                }
            }
        }

        for (Map.Entry<RoomType, Integer> entry : roomsNeeded.entrySet()) {
            Integer needRoomCount = entry.getValue();
            if (needRoomCount > 0) {
                System.out.println("Sorry, We don't have enough Rooms");
            }
        }

        return reservation;
    }

    public void cancelReservation(Reservation reservation) {
        // check valid
        if (isValidReservation(reservation)) {
            System.out.println("The reservation is invalid!");
        }

        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        // cancel room reservation
        for (Room room : reservation.getRooms()) {
            // remove roomReserved
            List<Date> schedule = roomReserved.get(room);
            // loop remove from startDate to endDate
            schedule.remove(startDate);
            schedule.remove(endDate);

            roomReserved.put(room, schedule);
        }
    }

    private boolean isValidReservation(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();
        Date now = new Date();

        if (startDate.getTime() > now.getTime() || endDate.getTime() < now.getTime()) {
            return false;
        }

        return true;
    }

    // constructor
    public Hotel(List<Room> rooms, List<Reservation> reservations, Map<Room, List<Date>> roomReserved) {
        this.rooms = rooms;
        this.reservations = reservations;
        this.roomReserved = roomReserved;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Map<Room, List<Date>> getRoomReserved() {
        return roomReserved;
    }

    public void setRoomReserved(Map<Room, List<Date>> roomReserved) {
        this.roomReserved = roomReserved;
    }
}
