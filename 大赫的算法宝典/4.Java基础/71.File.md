# File

## 1.概述

* 概念：java.io.File类 **文件**和**目录路径名**的抽象表示形式。
  * java把电脑中的文件和文件夹\(目录\)封装为了一个File类,我们可以使用File类对文件和文件夹进行操作 
* File类方法
  * 创建 一个文件/文件夹
  * 删除 文件/文件夹
  * 获取 文件/文件夹
  * 判断文件/文件夹是否存在
  * 对文件夹进行遍历
  * 获取文件的大小 
* File类是一个与系统无关的类,任何的操作系统都可以使用这个类中的方法 
* 关键词
  * file:文件
  * directory:文件夹/目录
  * path:路径

## 2.特殊标识

* 路径分隔符 `File.pathSeparator`
  * windows: `;`
  * linux: `:`  
* 文件名分隔符 `File.separator`
  * windows:`\`
  * linux:`/` 

### 2.1特殊标识-练习

```java
public class Demo01File {
    public static void main(String[] args) {
        /*
            static String pathSeparator 与系统有关的路径分隔符，为了方便，它被表示为一个字符串。
            static char pathSeparatorChar 与系统有关的路径分隔符。

            static String separator 与系统有关的默认名称分隔符，为了方便，它被表示为一个字符串。
            static char separatorChar 与系统有关的默认名称分隔符。

            操作路径:路径不能写死了
            C:\develop\a\a.txt  windows
            C:/develop/a/a.txt  linux
            "C:"+File.separator+"develop"+File.separator+"a"+File.separator+"a.txt"
         */
        String pathSeparator = File.pathSeparator;
        System.out.println(pathSeparator);//路径分隔符 windows:分号;  linux:冒号:

        String separator = File.separator;
        System.out.println(separator);// 文件名称分隔符 windows:反斜杠\  linux:正斜杠/
    }

}
```

## 3.绝对路径与相对路径

* 绝对路径：是一个完整的路径
  * 以盘符\(c:,D:\)开始的路径
  * C:\Users\itcast\IdeaProjects\shungyuan\123.txt
  * D:\demo\b.txt 
* 相对路径：是一个简化的路径
  * 指的是相对于**当前项目**的根目录\(C:\Users\itcast\IdeaProjects\shungyuan\)
  * C:\Users\itcast\IdeaProjects\shungyuan\123.txt--&gt;简化为: 123.txt\(可以省略项目的根目录\) 
* 注意事项：
  * 路径是不区分大小写
  * 路径中的文件名称分隔符windows使用反斜杠,反斜杠是转义字符,两个反斜杠代表一个普通的反斜杠File\(String pathname\)

## 4.构造方法

* `File(String pathname)`
  * 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例
  * 参数:
    * String pathname:字符串的路径名称
  * ```java
    private static void show01() {
        File f1 = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\a.txt");
        System.out.println(f1);//重写了Object类的toString方法 C:\Users\itcast\IdeaProjects\shungyuan\a.txt

        File f2 = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan");
        System.out.println(f2);//C:\Users\itcast\IdeaProjects\shungyuan

        File f3 = new File("b.txt");
        System.out.println(f3);//b.txt
    }
    ```
* `File(String parent, String child)`
  * 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例
  * 参数：把路径分成了两部分
    * String parent:父路径
    * String child:子路径
  * 好处:
    * 父路径和子路径,可以单独书写,使用起来非常灵活;父路径和子路径都可以变化
  * ```java
    private static void show02(String parent, String child) {
        File file = new File(parent,child);
        System.out.println(file);//c:\a.txt
    }
    ```
* `File(File parent, String child)`
  * 根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例
  * 参数：把路径分成了两部分
    * File parent:父路径
    * String child:子路径
  * 好处:
    * 父路径和子路径,可以单独书写,使用起来非常灵活;父路径和子路径都可以变化
    * 父路径是File类型,可以使用**File的方法**对路径进行一些操作,再使用路径创建对象

## 5.常用方法

### 5.1 获取功能的方法

* `public String getAbsolutePath()` ：返回此File的绝对路径名字符串。 
* `public String getPath()` ：将此File转换为**路径名**字符串。（绝对or相对都可以）获取的构造方法中传递的路径（new File\(...\)）,toString方法调用的就是getPath方法 
* `public String getName()` ：返回由此File表示的**文件**或**目录**的名称\(字符串\)。获取的就是构造方法传递路径的结尾部分\(文件/文件夹\) 
* `public long length()` ：返回由此File表示的文件的长度。获取的是构造方法指定的文件的大小,以字节为单位

```java
public class FileGet {
  public static void main(String[] args) {
      //文件
      File f = new File("d:/aaa/bbb.java");     
      System.out.println("文件绝对路径:"+f.getAbsolutePath());
      System.out.println("文件构造路径:"+f.getPath());
      System.out.println("文件名称:"+f.getName());
      System.out.println("文件长度:"+f.length()+"字节");

      //目录
      File f2 = new File("d:/aaa");     
      System.out.println("目录绝对路径:"+f2.getAbsolutePath());
      System.out.println("目录构造路径:"+f2.getPath());
      System.out.println("目录名称:"+f2.getName());
      System.out.println("目录长度:"+f2.length());
  }
}
  输出结果：
  文件绝对路径:d:\aaa\bbb.java
  文件构造路径:d:\aaa\bbb.java
  文件名称:bbb.java
  文件长度:636字节

  目录绝对路径:d:\aaa
  目录构造路径:d:\aaa
  目录名称:aaa
  目录长度:4096
```

### 5.2 判断功能的方法

* `public boolean exists()` ：此File表示的**文件或目录**是否实际存在。
* `public boolean isDirectory()` ：此File表示的是否为**目录**。
* `public boolean isFile()` ：此File表示的是否为**文件**。 
* **注意:** 电脑的硬盘中只有文件/文件夹,两个方法是**互斥** 
  * 这两个方法使用前提,路径**必须是存在**的,否则都返回false

```java
public class FileIs {
    public static void main(String[] args) {
        File f = new File("d:\\aaa\\bbb.java");
        File f2 = new File("d:\\aaa");
          // 判断是否存在
        System.out.println("d:\\aaa\\bbb.java 是否存在:"+f.exists());
        System.out.println("d:\\aaa 是否存在:"+f2.exists());
          // 判断是文件还是目录
        System.out.println("d:\\aaa 文件?:"+f2.isFile());
        System.out.println("d:\\aaa 目录?:"+f2.isDirectory());
    }
}
输出结果：
d:\aaa\bbb.java 是否存在:true
d:\aaa 是否存在:true
d:\aaa 文件?:false
d:\aaa 目录?:true
```



### 5.3 创建删除功能的方法

* **新建：**`public boolean createNewFile()` ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。 
  * 创建文件的路径和名称在构造方法中给出\(构造方法的参数\)
  * 返回值:布尔值
    * true:文件不存在,创建文件,返回true
    * false:文件存在,不会创建,返回false
  * 注意:
    * 此方法只能创建文件,不能创建文件夹
    * 创建文件的路径必须存在,否则会抛出异常
  * ```java
    private static void show01() throws IOException {
        File f1 = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\08_FileAndRecursion\\1.txt");
        boolean b1 = f1.createNewFile();
        System.out.println("b1:"+b1);

        File f2 = new File("08_FileAndRecursion\\2.txt");
        System.out.println(f2.createNewFile());

        File f3 = new File("08_FileAndRecursion\\新建文件夹");
        System.out.println(f3.createNewFile());//不要被名称迷糊,要看类型

        File f4 = new File("08_FileAndRecursi\\3.txt");
        System.out.println(f4.createNewFile());//路径不存在,抛出IOException
    }
    ```
* **删除：**`public boolean delete()` ：删除由此File表示的文件或目录。 
  * 此方法,可以删除构造方法路径中给出的文件/文件夹
  * 返回值:布尔值
    * true:文件/文件夹删除成功,返回true
    * false:文件夹中有内容,不会删除返回false;构造方法中路径不存在false
  * 注意:
    * delete方法是直接在硬盘删除文件/文件夹,不走回收站,删除要谨慎
  * ```java
    private static void show03() {
        File f1 = new File("08_FileAndRecursion\\新建文件夹");
        boolean b1 = f1.delete();
        System.out.println("b1:"+b1);

        File f2 = new File("08_FileAndRecursion\\abc.txt");
        System.out.println(f2.delete());
    }
    ```
* **文件：**`public boolean mkdir()` ：创建由此File表示的目录。
  * 创建单级空文件夹 
* **多级文件：**`public boolean mkdirs()` ：创建由此File表示的目录，包括任何必需但不存在的父目录。
  * 既可以创建单级空文件夹,也可以创建多级文件夹
  * 创建文件夹的路径和名称在构造方法中给出\(构造方法的参数\)
  * 返回值:布尔值
    * true:文件夹不存在,创建文件夹,返回true
    * false:文件夹存在,不会创建,返回false;构造方法中给出的路径不存在返回false
  * 注意:
    * 此方法只能创建文件夹,不能创建文件
  * ```java
    private static void show02() {
        File f1 = new File("08_FileAndRecursion\\aaa");
        boolean b1 = f1.mkdir();
        System.out.println("b1:"+b1);

        File f2 = new File("08_FileAndRecursion\\111\\222\\333\\444");
        boolean b2 = f2.mkdirs();
        System.out.println("b2:"+b2);

        File f3 = new File("08_FileAndRecursion\\abc.txt");
        boolean b3 = f3.mkdirs();//看类型,是一个文件
        System.out.println("b3:"+b3);

        File f4 = new File("08_F\\ccc");
        boolean b4 = f4.mkdirs();//不会抛出异常,路径不存在,不会创建
        System.out.println("b4:"+b4);
    }
    ```

### 5.4 遍历目录

* `public String[] list()` ：返回一个String数组，表示该File目录中的所有子文件或目录。
  * 遍历构造方法中给出的目录,会获取目录中所有文件/文件夹的名称,把获取到的多个名称存储到一个String类型的数组中
  * ```java
    private static void show01() {
        //File file = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\08_FileAndRecursion\\1.txt");//NullPointerException
        //File file = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\08_Fi");//NullPointerException
        File file = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\08_FileAndRecursion");
        String[] arr = file.list();
        for (String fileName : arr) {
            System.out.println(fileName);
        }
    }
    ```
* `public File[] listFiles()` ：返回一个File数组，表示该File目录中的所有的子文件或目录。 
  * 遍历构造方法中给出的目录,会获取目录中所有的文件/文件夹,把文件/文件夹封装为File对象,多个File对象存储到File数组中
  * ```java
    private static void show02() {
        File file = new File("C:\\Users\\itcast\\IdeaProjects\\shungyuan\\08_FileAndRecursion");
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
    }
    ```

