# IO流 - 字节流

## 1.概述

* 输入： 硬盘 ---&gt; 内存
* 输出： 内存 ---&gt; 硬盘 
* i：input输入（读取 - read）
* o：output输出（写入 - write）
* 流：数据（字符，字节）1个字符=2个字节，1个字节=8个二进制位

### 1.1 顶级父类们

|  | 输入流 | 输出流 |
| :---: | :---: | :---: |
| **字节流** | 字节输入流 **InputStream** | 字节输出流 **OutputStream** |
| **字符流** | 字符输入流 **Reader** | 字符输出流 **Writer** |

## 2 一切皆为字节

一切文件数据\(文本、图片、视频等\)在存储时，都是以二进制数字的形式保存。

## 3.常用方法（OutputStream）

### 3.1 共性方法（OutputStream）

* `public void close()` ：**关闭**此输出流并释放与此流相关联的任何系统资源。  
* `public void flush()` ：**刷新**此输出流并强制任何缓冲的输出字节被写出。  
* `public void write(byte[] b)`：将 b.length字节从指定的字节数组**写入**此输出流。  
* `public void write(byte[] b, int off, int len)` ：从指定的字节数组**写入** len字节，从偏移量 off开始输出到此输出流。  
* `public abstract void write(int b)` ：将指定的字节流输出

## 4. 字节输出流OutputStream - 构造方法

* `FileOutputStream(String name)` 
  * 创建一个向具有**指定名称**的文件中**写入数据**的输出文件流。
  * 参数：写入数据的**目的地**（String name） 
* `FileOutputStream(File file)` 
  * 创建一个向指定 **File 对象**表示的**文件**中**写入数据**的文件输出流。
  * 参数：写入数据的**目的地**（File file） 
* 参数：
  * String name:目的地是一个文件的路径
  * File file:目的地是一个文件 
* 构造方法的作用:
  1. 创建一个**FileOutputStream对象**
  2. 会根据构造方法中传递的文件/文件路径,**创建**一个空的**文件**
  3. 会把**FileOutputStream**对象**指向**创建好的**文件**

### 4.1 写入数据的原理\(内存--&gt;硬盘\)

java程序--&gt;JVM\(java虚拟机\)--&gt;OS\(操作系统\)--&gt;OS调用写数据的方法--&gt;把数据写入到文件中

### 4.2 使用步骤

1. 创建一个FileOutputStream对象,构造方法中传递 - 写入数据的**目的地**
2. 调用FileOutputStream对象中的方法write,把数据写入到文件中
3. 释放资源

```java
public static void main(String[] args) throws IOException {
    //1.创建一个FileOutputStream对象,构造方法中传递写入数据的目的地
    FileOutputStream fos = new FileOutputStream("09_IOAndProperties\\a.txt");
    //2.调用FileOutputStream对象中的方法write,把数据写入到文件中
    //public abstract void write(int b) ：将指定的字节输出流。
    fos.write(97);
    //3.释放资源(流使用会占用一定的内存,使用完毕要把内存清空,提供程序的效率)
    fos.close();
}
```

### 代码实例

* 一次写多个字节:
  * 如果写的第一个字节是正数\(0-127\),那么显示的时候会查询ASCII表
  * 如果写的第一个字节是负数,那第一个字节会和第二个字节,两个字节组成一个中文显示,查询系统默认码表\(GBK\) 
* 字符串写入
  *  `byte[] getBytes()` 把字符串转换为字节数组

```java
public static void main(String[] args) throws IOException {
    //创建FileOutputStream对象,构造方法中绑定要写入数据的目的地
    FileOutputStream fos = new FileOutputStream(new File("09_IOAndProperties\\b.txt"));
    //调用FileOutputStream对象中的方法write,把数据写入到文件中
    //在文件中显示100,写个字节
    fos.write(49);
    fos.write(48);
    fos.write(48);

    /*
        public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
    */    
    byte[] bytes = {65,66,67,68,69};//ABCDE
    //byte[] bytes = {-65,-66,-67,68,69};//烤紻E
    fos.write(bytes);
----------------------------------------------------------------------
    /*
        public void write(byte[] b, int off, int len) ：把字节数组的一部分写入到文件中
            int off:数组的开始索引
            int len:写几个字节
     */
    fos.write(bytes,1,2);//BC
-----------------------------------------------------------------------
    /*
        写入字符的方法:可以使用String类中的方法把字符串,转换为字节数组
            byte[] getBytes()  把字符串转换为字节数组
     */
    byte[] bytes2 = "你好".getBytes();
    System.out.println(Arrays.toString(bytes2));//[-28, -67, -96, -27, -91, -67]
    fos.write(bytes2);

    //释放资源
    fos.close();
}
```

### 4.3 续写与换行

#### 4.3.1构造方法

* `FileOutputStream(String name, boolean append)` 创建一个具有指定name的文件，写入文件的输出文件流 
* `FileOutputStream(File file, boolean append)` 创建一个指定的File对象表示的文件，写入数据的文件输出流 
* 参数
  * String name,File file:写入数据的目的地
  * boolean append:追加写开关
    * true: 不会覆盖源文件,继续在文件的末尾追加写数据
    * false:创建一个新文件,覆盖源文件

#### 4.3.2 换行

* 写换行符号
  * windows:\r\n 
  * linux:/n 
  * mac:/r

## 5. 字节输入流 InputStream

### 5.1 概述（硬盘---&gt;内存）

* `java.io.InputStream`抽象类是表示字节输入流的所有类的超类，可以读取字节信息到内存中。它定义了字节输入流的基本共性功能方法。

### 5.2 常用方法

* `public void close()` ：关闭此输入流并释放与此流相关联的任何系统资源。    
* `public abstract int read()`： 从输入流读取数据的下一个字节。 
* `public int read(byte[] b)`： 从输入流中读取一些字节数，并将它们存储到字节数组 b中 。

### 5.3 构造方法

* `FileInputStream(String name)`  
* `FileInputStream(File file)`  
* 参数：
  * String name: 文件的路径
  * File file：文件 
* 构造方法作用：
  * 创建一个FileInputStream对象
  * 把FileInputStream对象**指向**构造方法中要读取的文件

### 5.4 使用步骤：

1. 创建FileInputStream对象,构造方法中绑定要读取的数据源
2. 使用FileInputStream对象中的方法read,读取文件
3. 释放资源

### 代码实例（一次读取一个字节）

* 基础
* len 返回读取到的数据，如果没有则返回-1

```java
public static void main(String[] args) throws IOException {
    //1.创建FileInputStream对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("09_IOAndProperties\\c.txt");
    //2.使用FileInputStream对象中的方法read,读取文件
    //int read()读取文件中的一个字节并返回,读取到文件的末尾返回-1
    int len = fis.read();
    System.out.println(len);//97 a

    len = fis.read();
    System.out.println(len);// 98 b

    len = fis.read();
    System.out.println(len);//99 c

    len = fis.read();
    System.out.println(len);//-1

    len = fis.read();
    System.out.println(len);//-1*/

    //3.释放资源
    fis.close();
}
```

* 循环

```java
public static void main(String[] args) throws IOException {
    //1.创建FileInputStream对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("09_IOAndProperties\\c.txt");

    /*
        发现以上读取文件是一个重复的过程,所以可以使用循环优化
        不知道文件中有多少字节,使用while循环
        while循环结束条件,读取到-1的时候结束

        布尔表达式(len = fis.read())!=-1
            1.fis.read():读取一个字节
            2.len = fis.read():把读取到的字节赋值给变量len
            3.(len = fis.read())!=-1:判断变量len是否不等于-1
     */
    int len = 0; //记录读取到的字节
    while((len = fis.read())!=-1){
        System.out.print(len);//abc
    }

    //3.释放资源
    fis.close();
}
```

### 5.5 读取多个字节

* `int read(byte[] b)` 从输入流中读取一定数量的字节，并将其**存储**在缓冲区**数组 b** 中。 
* 注意事项：
  1. 方法的参数`byte[]`的作用?
     * 起到缓冲作用,存储每次读取到的多个字节
     * 数组的长度一把定义为1024\(1kb\)或者1024的整数倍
  2. 方法的返回值int是什么?
     * 每次读取的有效字节个数 
* 字节 ---&gt; String 构造方法：
  * `String(byte[] bytes)` :把字节数组转换为字符串
  * `String(byte[] bytes, int offset, int length)` 把字节数组的一部分转换为字符串 
    * offset:数组的开始索引 
    * length:转换的字节个数

### 代码实例

* len：每次读取到的有效字节个数, 如果使用`fis.read(bytes)` 
* bytes：存储读取到的字节
* `String(byte[] bytes, int offset, int length)` 把字节数组的一部分转换为字符串

```java
public static void main(String[] args) throws IOException {
    //创建FileInputStream对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("09_IOAndProperties\\b.txt");
    //使用FileInputStream对象中的方法read读取文件
    //int read(byte[] b) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。
    byte[] bytes = new byte[2];
    
    int len = fis.read(bytes);
    System.out.println(len);//2
    //System.out.println(Arrays.toString(bytes));//[65, 66]
    System.out.println(new String(bytes));//AB

    //释放资源
    fis.close();
}
```

* while循环

```java
public static void main(String[] args) throws IOException {
    //创建FileInputStream对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("09_IOAndProperties\\b.txt");

    /*
        发现以上读取时一个重复的过程,可以使用循环优化
        不知道文件中有多少字节,所以使用while循环
        while循环结束的条件,读取到-1结束
     */
    byte[] bytes = new byte[1024];//存储读取到的多个字节
    int len = 0; //记录每次读取的有效字节个数
    while((len = fis.read(bytes))!=-1){
        //String(byte[] bytes, int offset, int length) 把字节数组的一部分转换为字符串 offset:数组的开始索引 length:转换的字节个数
        System.out.println(new String(bytes,0,len));
    }

    //释放资源
    fis.close();
}
```

