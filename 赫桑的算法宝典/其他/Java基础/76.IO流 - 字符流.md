# IO流 - 字符流

## 1. 字符输入流（Reader）

* 字符输入流,是字符输入流的最顶层的父类,定义了一些共性的成员方法,是一个抽象类

### 1.1 共性成员方法

* `int read()` 读取单个字符并返回。
* `int read(char[] cbuf)`一次读取多个字符,将字符读入数组。
* `void close()` 关闭该流并释放与之关联的所有资源。

### 1.2 FileReader

* `java.io.FileReader extends InputStreamReader extends Reader` 
* 文件字符输入流 
* **作用**
  * 把**硬盘**文件中的数据以字符的方式读取到**内存**中 
* **构造方法**
  * FileReader\(String fileName\) 
  * FileReader\(File file\) 
  * 参数:
    * 读取文件的数据源
    * String fileName:文件的路径
    * File file:一个文件
  * 构造方法的作用
    1. 创建一个FileReader对象
    2. 会把FileReader对象指向要读取的文件 
* **使用步骤**
  1. 创建FileReader对象,构造方法中绑定要读取的数据源
  2. 使用FileReader对象中的方法read读取文件
  3. 释放资源

### 代码实例

* `char[] cs` 需要使用字符数组，而不是byte

```java
public static void main(String[] args) throws IOException {
    //1.创建FileReader对象,构造方法中绑定要读取的数据源
    FileReader fr = new FileReader("09_IOAndProperties\\c.txt");
    //2.使用FileReader对象中的方法read读取文件
    //int read() 读取单个字符并返回。
    /*int len = 0;
    while((len = fr.read())!=-1){
        System.out.print((char)len);
    }*/

    //int read(char[] cbuf)一次读取多个字符,将字符读入数组。
    char[] cs = new char[1024];//存储读取到的多个字符
    int len = 0;//记录的是每次读取的有效字符个数
    while((len = fr.read(cs))!=-1){
        /*
            String类的构造方法
            String(char[] value) 把字符数组转换为字符串
            String(char[] value, int offset, int count) 把字符数组的一部分转换为字符串 offset数组的开始索引 count转换的个数
         */
        System.out.println(new String(cs,0,len));
    }

    //3.释放资源
    fr.close();
}
```

## 2.字符输出流（Writer）

字符输出流,是所有字符输出流的最顶层的父类,是一个抽象类

### 2.1 共性的成员方法:

* `void write(int c)` 写入单个字符。
* `void write(char[] cbuf)`写入字符数组。
* `abstract  void write(char[] cbuf, int off, int len)`写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
* `void write(String str)`写入字符串。
* `void write(String str, int off, int len)` 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
* `void flush()`刷新该流的缓冲。
* `void close()` 关闭此流，但要先刷新它。

### 2.2 FileWriter

* `java.io.FileWriter extends OutputStreamWriter extends Writer` 
* 文件字符输出流 
* **作用:**
  * 把内存中字符数据写入到文件中 
* **构造方法:**
  * `FileWriter(File file)`根据给定的 File 对象构造一个 FileWriter 对象。
  * `FileWriter(String fileName)` 根据给定的文件名构造一个 FileWriter 对象。
  * 参数:
    * 写入数据的目的地
    * String fileName:文件的路径
    * File file:是一个文件
  * 构造方法的作用:
    1. 创建一个FileWriter对象
    2. 根据构造方法中传递的文件/文件的路径,创建文件
    3. 把FileWriter对象指向创建好的文件 
* **使用步骤：**
  1. 创建FileWriter对象,构造方法中绑定要写入数据的目的地
  2. 使用FileWriter中的方法write,把**数据**写入到**内存缓冲区**中\(字符转换为字节的过程\)
  3. 使用FileWriter中的方法flush,把**内存缓冲区**中的数据,刷新到**文件**中
  4. 释放资源\(会先把内存缓冲区中的数据刷新到文件中\) 
* `flush`方法和`close`方法的区别
  * flush ：刷新缓冲区，流对象可以继续使用。
  * close:  先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。

### 代码实例

```java
public static void main(String[] args) throws IOException {
    //1.创建FileWriter对象,构造方法中绑定要写入数据的目的地
    FileWriter fw = new FileWriter("09_IOAndProperties\\d.txt");
    //2.使用FileWriter中的方法write,把数据写入到内存缓冲区中(字符转换为字节的过程)
    //void write(int c) 写入单个字符。
    fw.write(97);
    //3.使用FileWriter中的方法flush,把内存缓冲区中的数据,刷新到文件中
    //fw.flush();
    //4.释放资源(会先把内存缓冲区中的数据刷新到文件中)
    fw.close();
}
```

### 2.3 字符输出写数据

* `void write(char[] cbuf)`写入**字符数组**。 
* `abstract void write(char[] cbuf, int off, int len)`写入**字符数组**的某**一部分**,off数组的开始索引,len写的字符个数。 
* `void write(String str)`写入**字符串**。 
* `void write(String str, int off, int len)` 写入**字符串**的某**一部分**,off字符串的开始索引,len写的字符个数。

### 代码实例

```java
public static void main(String[] args) throws IOException {
    FileWriter fw = new FileWriter("09_IOAndProperties\\f.txt");
    char[] cs = {'a','b','c','d','e'};
    //void write(char[] cbuf)写入字符数组。
    fw.write(cs);//abcde

    //void write(char[] cbuf, int off, int len)写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
    fw.write(cs,1,3);//bcd

    //void write(String str)写入字符串。
    fw.write("传智播客");//传智播客

    //void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
    fw.write("黑马程序员",2,3);//程序员

    fw.close();
}
```

### 2.4 续写和换行

**直接叠加，不会破坏之前文件中的东西**

* 构造方法
  * FileWriter\(String fileName, boolean append\) 
  * FileWriter\(File file, boolean append\) 
* 参数
  * String fileName, File file:写入数据的目的地
  * boolean append:续写开关 
    * true:不会创建新的文件覆盖源文件,可以续写; 
    * false:创建新的文件覆盖源文件
* 换行符号
  * windows:\r\n
  * linux:/n
  * mac:/r

## 3. try ... catch ... finally 在流中使用

* 提高变量fw的作用域,让finally可以使用
* 变量在定义的时候,可以没有值,但是使用的时候必须有值。赋值null

```java
/*
在jdk1.7之前使用try catch finally 处理流中的异常
格式:
    try{
        可能会产出异常的代码
    }catch(异常类变量 变量名){
        异常的处理逻辑
    }finally{
        一定会指定的代码
        资源释放
    }
*/
public static void main(String[] args) {
    //提高变量fw的作用域,让finally可以使用
    //变量在定义的时候,可以没有值,但是使用的时候必须有值
    //fw = new FileWriter("09_IOAndProperties\\g.txt",true); 执行失败,fw没有值,fw.close会报错
    FileWriter fw = null;
    try{
        //可能会产出异常的代码
        fw = new FileWriter("w:\\09_IOAndProperties\\g.txt",true);
        for (int i = 0; i <10 ; i++) {
            fw.write("HelloWorld"+i+"\r\n");
        }
    }catch(IOException e){
        //异常的处理逻辑
        System.out.println(e);
    }finally {
        //一定会指定的代码
        //创建对象失败了,fw的默认值就是null,null是不能调用方法的,会抛出NullPointerException,需要增加一个判断,不是null在把资源释放
        if(fw!=null){
            try {
                //fw.close方法声明抛出了IOException异常对象,所以我们就的处理这个异常对象,要么throws,要么try catch
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
```

### 3.1 JDK7 新特性

* 在`try`的后边可以增加一个\(\),在括号中可以定义流对象
* 这个流对象的作用域就在`try`中有效
* `try`中的代码执行完毕,会**自动**把流对象**释放**,不用写`finally` 

```java
/*
格式:
    try(定义流对象;定义流对象....){
        可能会产出异常的代码
    }catch(异常类变量 变量名){
        异常的处理逻辑
    }
 */
 public static void main(String[] args) {
    try(//1.创建一个字节输入流对象,构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("c:\\1.jpg");
        //2.创建一个字节输出流对象,构造方法中绑定要写入的目的地
        FileOutputStream fos = new FileOutputStream("d:\\1.jpg");){

        //可能会产出异常的代码
        //一次读取一个字节写入一个字节的方式
        //3.使用字节输入流对象中的方法read读取文件
        int len = 0;
        while((len = fis.read())!=-1){
            //4.使用字节输出流中的方法write,把读取到的字节写入到目的地的文件中
            fos.write(len);
        }
    }catch (IOException e){
        //异常的处理逻辑
        System.out.println(e);
    }
}
```

### 3.2 JDK9 新特性

* try的前边可以定义流对象
* 在try后边的\(\)中可以直接引入流对象的名称\(变量名\)
* 在try代码执行完毕之后,流对象也可以释放掉,不用写finally

```java
/*
    格式:
        A a = new A();
        B b = new B();
        try(a,b){
            可能会产出异常的代码
        }catch(异常类变量 变量名){
            异常的处理逻辑
        }
 */

public static void main(String[] args) throws IOException {
    //1.创建一个字节输入流对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("c:\\1.jpg");
    //2.创建一个字节输出流对象,构造方法中绑定要写入的目的地
    FileOutputStream fos = new FileOutputStream("d:\\1.jpg");

    try(fis;fos){
        //一次读取一个字节写入一个字节的方式
        //3.使用字节输入流对象中的方法read读取文件
        int len = 0;
        while((len = fis.read())!=-1){
            //4.使用字节输出流中的方法write,把读取到的字节写入到目的地的文件中
            fos.write(len);
        }
    }catch (IOException e){
        System.out.println(e);
    }

    //fos.write(1);//Stream Closed 流已经关闭无法使用

}
```

