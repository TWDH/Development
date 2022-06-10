# Date类

## 概述

* 表示特定的瞬间，精确到毫秒
  * 毫秒:千分之一秒 1000毫秒=1秒

## 方法

* 获取当前系统时间到1970 年 1 月 1 日 00:00:00经历了多少毫秒
  * `System.currentTimeMillis()`
* Date类 + 空参构造方法
  * Date\(\)获取当前系统的日期和时间
  * ```java
    Date date = new Date() //Sun Aug 08 12:23:03 CST 2018
    ```
* 日期 ---&gt; 毫秒值
  * `getTime()`
  * ```java
    private static void demo03() {
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);//3742777636267
    }
    ```

## DataFormat

* 概念：时间/日期格式化子类的抽象类
* 作用
  * 格式化（也就是日期 -&gt; 文本）、解析（文本-&gt; 日期）

### SimpleDateFormat

* 构造方法:
  * SimpleDateFormat\(String pattern\)
  * 用给定的模式和默认语言环境的日期格式符号构造 SimpleDateFormat。
* 参数:
  * y 年
  * M 月
  * d 日
  * H 时
  * m 分
  * s 秒
  * `"yyyy-MM-dd HH:mm:ss"`
* 注意:
  * 模式中的字母不能更改,连接模式的符号可以改变
  * `yyyy年MM月dd日 HH时mm分ss秒"`  
* 日期 ---&gt; 文本
  * ```java
    /*
    使用DateFormat类中的方法format,把日期格式化为文本
    使用步骤:
        1.创建SimpleDateFormat对象,构造方法中传递指定的模式
        2.调用SimpleDateFormat对象中的方法format,按照构造方法中指定的模式,把Date日期格式化为符合模式的字符串(文本)
    */
    private static void demo01() {
        //1.创建SimpleDateFormat对象,构造方法中传递指定的模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        //2.调用SimpleDateFormat对象中的方法format,按照构造方法中指定的模式,把Date日期格式化为符合模式的字符串(文本)
        //String format(Date date)  按照指定的模式,把Date日期,格式化为符合模式的字符串
        Date date = new Date();
        String d = sdf.format(date);
        System.out.println(date);//Sun Aug 08 15:51:54 CST 2088
        System.out.println(d);//2088年08月08日 15时51分54秒
    }
    ```
* 文本 ---&gt; 日期
  * ```java
    /*
    使用DateFormat类中的方法parse,把文本解析为日期
    使用步骤:
    1.创建SimpleDateFormat对象,构造方法中传递指定的模式
    2.调用SimpleDateFormat对象中的方法parse,把符合构造方法中模式的字符串,解析为Date日期
    注意:
        public Date parse(String source) throws ParseException
        parse方法声明了一个异常叫ParseException
        如果字符串和构造方法的模式不一样,那么程序就会抛出此异常
        调用一个抛出了异常的方法,就必须的处理这个异常,要么throws继续抛出这个异常,要么try catch自己处理
     */
    private static void demo02() throws ParseException {
        //1.创建SimpleDateFormat对象,构造方法中传递指定的模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        //2.调用SimpleDateFormat对象中的方法parse,把符合构造方法中模式的字符串,解析为Date日期
        //Date parse(String source)  把符合模式的字符串,解析为Date日期
        Date date = sdf.parse("2088年08月08日 15时51分54秒");
        System.out.println(date);
    }

    ```



