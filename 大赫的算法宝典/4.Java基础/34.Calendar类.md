# Calendar类

## 概述

* java.util.Calendar类:日历类 
* Calendar类是一个抽象类,里边提供了很多操作日历字段的方法\(YEAR、MONTH、DAY\_OF\_MONTH、HOUR \) 
* Calendar类无法直接创建对象使用,里边有一个静态方法叫`getInstance()`,该方法返回了Calendar类的子类对象
  * ```java
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();//多态
        System.out.println(c);
    }
    ```

## 常用方法

* `public int get(int field)`：返回给定日历字段的值。
  * ```java
    /*
    public int get(int field)：返回给定日历字段的值。
    参数:传递指定的日历字段(YEAR,MONTH...)
    返回值:日历字段代表的具体的值
    */
    private static void demo01() {
        //使用getInstance方法获取Calendar对象
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        System.out.println(year);

        int month = c.get(Calendar.MONTH);
        System.out.println(month);//西方的月份0-11 东方:1-12

        //int date = c.get(Calendar.DAY_OF_MONTH);
        int date = c.get(Calendar.DATE);
        System.out.println(date);
    }
    ```
* `public void set(int field, int value)`：将给定的日历字段设置为给定值。
  * ```java
    /*
    public void set(int field, int value)：将给定的日历字段设置为给定值。
    参数:
        int field:传递指定的日历字段(YEAR,MONTH...)
        int value:给指定字段设置的值
     */
    private static void demo02() {
        //使用getInstance方法获取Calendar对象
        Calendar c = Calendar.getInstance();

        //设置年为9999
        c.set(Calendar.YEAR,9999);
        //设置月为9月
        c.set(Calendar.MONTH,9);
        //设置日9日
        c.set(Calendar.DATE,9);

        //同时设置年月日,可以使用set的重载方法
        c.set(8888,8,8);

        int year = c.get(Calendar.YEAR);
        System.out.println(year);

        int month = c.get(Calendar.MONTH);
        System.out.println(month);//西方的月份0-11 东方:1-12

        int date = c.get(Calendar.DATE);
        System.out.println(date);
    }
    ```
* `public abstract void add(int field, int amount)`：根据日历的规则，为给定的日历字段添加或减去指定的时间量。
  * ```java
    /*
        public abstract void add(int field, int amount)：根据日历的规则，为给定的日历字段添加或减去指定的时间量。
        把指定的字段增加/减少指定的值
        参数:
            int field:传递指定的日历字段(YEAR,MONTH...)
            int amount:增加/减少指定的值
                正数:增加
                负数:减少
     */
    private static void demo03() {
        //使用getInstance方法获取Calendar对象
        Calendar c = Calendar.getInstance();

        //把年增加2年
        c.add(Calendar.YEAR,2);
        //把月份减少3个月
        c.add(Calendar.MONTH,-3);


        int year = c.get(Calendar.YEAR);
        System.out.println(year);

        int month = c.get(Calendar.MONTH);
        System.out.println(month);//西方的月份0-11 东方:1-12

        //int date = c.get(Calendar.DAY_OF_MONTH);
        int date = c.get(Calendar.DATE);
        System.out.println(date);
    }
    ```
* `public Date getTime()`：返回一个表示此Calendar时间值（从历元到现在的毫秒偏移量）的Date对象。
  * ```java
    /*
        public Date getTime()：返回一个表示此Calendar时间值（从历元到现在的毫秒偏移量）的Date对象。
        把日历对象,转换为日期对象
     */
    private static void demo04() {
        //使用getInstance方法获取Calendar对象
        Calendar c = Calendar.getInstance();

        Date date = c.getTime();
        System.out.println(date);
    }
    ```

#### Calendar类中提供很多成员常量，代表给定的日历字段：

| 字段值 | 含义 |
| :--- | :--- |
| YEAR | 年 |
| MONTH | 月（从0开始，可以+1使用） |
| DAY\_OF\_MONTH | 月中的天（几号） |
| HOUR | 时（12小时制） |
| HOUR\_OF\_DAY | 时（24小时制） |
| MINUTE | 分 |
| SECOND | 秒 |
| DAY\_OF\_WEEK | 周中的天（周几，周日为1，可以-1使用） |

