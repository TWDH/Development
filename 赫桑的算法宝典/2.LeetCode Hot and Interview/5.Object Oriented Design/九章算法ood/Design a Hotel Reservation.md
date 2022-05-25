# 预定类

![image-20220524230136155](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524230136155.png)

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
- **Use Case**
  - 思路 （`Search criteria ---> Search() ---> List<Result> ---> Select() ---> Receipt ---> Cancel()`）
    - Search
    - Select
    - Cancel













































