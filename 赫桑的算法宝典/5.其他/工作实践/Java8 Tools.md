# LocalDate

- Problems

  - ```
    springboot 2.5.6
    mysql.connector.java 8.0.17
    druid 1.1.22
    mybatis.plus 3.4.3.4
    ```

  - 

1. **Localdate创建方法**

   - ```java
     //获取当前时间
     LocalDate localDate = LocalDate.now();
     //输入年月日设置时间
     LocalDate localDate = LocalDate.of(year,month,day);
     ```

2. **获取年月日星期几**

   - ```java
     int year = localDate.getYear();
     int year = localDate.get(ChronoField.YEAR);
     //注意getMonth方法返回的是Month类，可通过getValue方法获得一个long类型的值，然后可以强转为int类型进行使用
     
     Month month = localDate.getMonth();
     int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
     int day = localDate.getDayOfMonth();
     int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
     
     //注意getDayOfWeek方法返回的是DayOfWeek类，可通过getValue方法获得一个long类型的值，然后可以强转为int类型进行使用
     DayOfWeek dayOfWeek = localDate.getDayOfWeek();
     int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
     ```

3. **进行加减日期**

   - ```java
     LocalDate localDate = LocalDate.now();
     
     //增加一年
     localDate = localDate.plusYears(1);
     localDate = localDate.plus(1, ChronoUnit.YEARS);
     
     //减少一个月
     localDate = localDate.minusMonths(1);
     localDate = localDate.minus(1, ChronoUnit.MONTHS);
     
     //减少一日
     localDate = localDate.minusDays(1);
     localDate = localDate.minus(1, ChronoUnit.DAYS);
     
     //通过使用with方法进行修改
     //修改年为2020
     localDate = localDate.withYear(2020);
     
     //修改为2020
     localDate = localDate.with(ChronoField.YEAR, 2020);
     ```

4. **LocalTime**

   1. ```java
      LocalDateTime.of(locaDate,localTime),LocalDate.atTime(localTime),LocalTime.atDate(localDate)
      
      // 获取LocalDate
      LocalDate localDate = localDateTime.toLocalDate();
      
      // 获取LocalTime
      LocalTime localTime = localDateTime.toLocalTime();
      ```

5. **Instant**

   1. Instant类是1.8之后新增的，与1.8之前的Date类有点相似

6. **Instant的创建方法**

   1. ```java
      Instant instant = Instant.now();
      //l为1970年1月1日至所记录时间的的毫秒数
      Instant instant1 = Instant.ofEpochMilli(l);
      ```

7. **设置时区偏移量**

   1. ```java
      // 按时区设置偏移量
      OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));// 设置偏移量为8
      ```

8. **获取秒数**

   1. ```java
      long currentSecond = instant.getEpochSecond();
      ```

9. **获取毫秒数**

   1. 获取1970年1月1日至所记录时间的的毫秒数，类似于Date类中的getTime方法。

   2. ```java
      //获取毫秒数
      long l = instant.toEpochMilli();
      ```

10. 对于LocalDate,LocalTime,LocalDateTime,Instant类进行格式化需要使用DateTimeFormatter类

    1. ```java
       //String转换成日期类型
       //String类型转换成LocalDate类型
       LocalDate localDate = LocalDate.parse("2019-12-07");
       //String类型转换为LocalTime类型
       LocalTime localTime = LocalTime.parse("17:26:00");
       //String类型转换为LocalDateTime类型
       //按照12小时计算
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
       //按照24小时计算
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       LocalDate localDate = LocalDate.parse("2019-12-07 07:43:53",formatter);
       //日期类型转换成String类型
       //localDate转换成String 
       LocalDate localDate = LocalDate.now();
       //按照yyyyMMdd样式进行更改
       String format = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
       //按照yyyy-MM-dd样式进行更改
       String format = localDate.format(DateTimeFormatter.ISO_DATE);
       //自定义样式进行更改
       DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
       String format = localDate.format(pattern);
       //LocalTime类型转换为String类型
       //按照xx:xx:xx.xx样式进行转换
       LocalTime localTime = LocalTime.now();
       String format3 = localTime.format(DateTimeFormatter.ISO_TIME); 
       //按照自定义样式进行转换
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
       String format4 = localTime.format(formatter);        
       //LocalDateTime类型转换为String类型
       LocalDateTime localDateTime = LocalDateTime.now();
       DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
       String format5 = localDateTime.format(formatter2);
       ```







- ```java
  public class DateUtil {
      public static DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   
      public static DateTimeFormatter simpleDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
   
      public static DateTimeFormatter defaultFormatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   
      /**
       * '前一天的日期
       */
      public static LocalDate lastDay(String date, DateTimeFormatter dateTimeFormatter) {
          LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
          return localDate.minusDays(1);
      }
   
      /**
       * 本周一的日期
       */
      public static LocalDate thisWeekFirstDay(String date, DateTimeFormatter dateTimeFormatter) {
          LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
          return localDate.with(DayOfWeek.MONDAY);
      }
   
      /**
       * 上周一
       */
      public static LocalDate lastWeekFirstDay(String date, DateTimeFormatter dateTimeFormatter) {
          LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
          return localDate.minusDays(7).with(DayOfWeek.MONDAY);
      }
   
      /**
       * 本月第一天
       */
      public static LocalDate thisMonthFirstDay(String date, DateTimeFormatter dateTimeFormatter) {
          LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
          return localDate.with(firstDayOfMonth());
      }
   
      /**
       * 上月第一天
       */
      public static LocalDate lastMonthFirstDay(String date, DateTimeFormatter dateTimeFormatter) {
          LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
          return localDate.minusMonths(1).with(firstDayOfMonth());
      }
   
      /**
       * 两个时间之间间隔了多少年
       */
      public static BigDecimal year(long low, long high) {
          Instant instant1 = Instant.ofEpochMilli(low);
          Instant instant2 = Instant.ofEpochMilli(high);
   
          LocalDate localDate1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault()).toLocalDate();
          LocalDate localDate2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();
          Period period = Period.between(
                  localDate1
                  , localDate2
          );
          int years = period.getYears();
          int months = period.getMonths();
          BigDecimal monthToYear = new BigDecimal(months).divide(BigDecimal.valueOf(12), 2, RoundingMode.DOWN);
          return BigDecimal.valueOf(years).add(monthToYear);
      }
   
      /**
       * 是否是同一天
       */
      public static boolean isSameDateByUnixTime(long time1, long time2) {
          LocalDateTime dateTime1 = Instant.ofEpochMilli(time1).atZone(ZoneId.systemDefault()).toLocalDateTime();
          LocalDateTime dateTime2 = Instant.ofEpochMilli(time2).atZone(ZoneId.systemDefault()).toLocalDateTime();
          return dateTime1.toLocalDate().isEqual(dateTime2.toLocalDate());
      }
  }
  ```

- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/17748870-168cd41a4cda7400.png)

- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/17748870-9ff934557f791468.png)

- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/17748870-f1119e006d158b91.png)

- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/17748870-493d95e6e912e84c.png)