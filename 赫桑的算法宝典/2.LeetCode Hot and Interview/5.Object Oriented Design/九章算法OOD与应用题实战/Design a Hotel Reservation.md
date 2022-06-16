# 预定类

![image-20220524230136155](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524230136155.png) 

- Search
  - `Hotel.handleSearchRequest()`：search 的 use case
    1. 时间作为输入
    2. Go through room availability 【roomReservations()】
    3. 输出是 RoomType，数量int
  - `Hotel.roomReservations()`：记录那些房间已经被预定过了
    - 调用 Hotel.isRequestAvailable()
  - `Hotel.isRequestAvailable()`：辅助函数，request 是否可以被预约
- Make Reservation
  1. add *RoomType* and *number of rooms* in a request
  2. Send request to Hotel
     1. `Hotel.makeReservation()`： 接收 ReservationRequest， 生成具体的 Reservation
     2. Go through `Hotel.roomReservations` 再检查一遍当前预订情况
  3. if there is enough room left, confirm the reservation
     - 缓存：search / reserve 的时候都需要遍历 roomReservations
     - 
  4. if there’s not enough room left, throw exception

![image-20220524230141432](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524230141432.png)



```
Hotel:
- List<Room> / Map<RoomType, List<Room>>  rooms 
- List<Reservation>  reservations
- Map<Room, List<Date>>  roomReservations
- LRUCache<Request, Map<RoomType, Set<Room>>>  cache
- String  city

+ Map<RoomType, int>  handleSearchRequest(Request r)
- Boolean  isRequestAvailable(Request r, List<Date> dates)
+ Reservation  makeReservation(ReservationRequest r)
+ void  cancelReservation(Reservation r)
+ boolean  isSameCity(string city)
+ boolean  isValidRequest(SearchHotelRequest r)
```

- **Clarify**
  - *WHAT* （`Search criteria ---> Search() ---> List<Result> ---> Select() ---> Receipt ---> Cancel()`）
    - 预定一家酒店 / 酒店预定系统
      - 搜索条件区别（人数 + 时间 vs. 人数 + 时间 + 地址）
      - 返回结果区别（Rooms vs. Hotels）
      - 本题：酒店 + 酒店系统
    - 关键字：Hotel
      - `List<Result>` ---> `List<RoomType>`
  - *HOW* (规则)
    - Search Criteria
- **Core Object**
  - ![image-20220525223053563](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525223053563.png)
- **Use Case**
  - 思路 （`Search criteria ---> Search() ---> List<Result> ---> Select() ---> Receipt ---> Cancel()`）
    - Search：Search for available rooms
      1. Based on search criteria
      2. Go through rooms to check availability
      3. List available room types and available count
    - Select：Make Reservation
    - Cancel：Cancel Reservation

```java
// K.Z
// enums
public enum RoomType {
    SINGLE,
    DOUBLE,
}

// input
// Search Request
public class SearchRequest {
    private Date startDate;
    private Date endDate;

    public SearchRequest(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
        return "SearchRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

// Reservation Request
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

// Core Objects
// Hotel
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
                    // add to reservation list
                    reservations.add(reservation);
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

// Room
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

// Reservation
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
```













































