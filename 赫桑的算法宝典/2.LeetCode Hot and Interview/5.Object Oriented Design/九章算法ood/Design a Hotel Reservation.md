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













































