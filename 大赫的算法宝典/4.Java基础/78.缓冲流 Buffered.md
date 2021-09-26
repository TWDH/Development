# 缓冲流 Buffered

## 1.字节缓冲输出流 BufferedOutputStream

* java.io.BufferedOutputStream extends OutputStream

### 1.1 共性成员方法

* `public void close()` ：关闭此输出流并释放与此流相关联的任何系统资源。
* `public void flush()` ：刷新此输出流并强制任何缓冲的输出字节被写出。
* `public void write(byte[] b)`：将 b.length字节从指定的字节数组写入此输出流。
* `public void write(byte[] b, int off, int len)` ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
* `public abstract void write(int b)` ：将指定的字节输出流。

### 1.2 构造方法

* `BufferedOutputStream(OutputStream out)` 创建一个新的缓冲输出流，以将数据写入指定的底层输出流。
* `BufferedOutputStream(OutputStream out, int size)` 创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流。
* 参数：
  * `OutputStream out` 
    * 字节输出流
    * 传递`FileOutputStream`缓冲流会给`FileOutputStream`增加一个缓冲区,提高`FileOutputStream`的写入效率
  * `int size`
    * 指定缓冲流内部缓冲区的大小,不指定默认

### 1.3使用步骤

1. 创建**FileOutputStream**对象,构造方法中绑定要输出的目的地
2. 创建**BufferedOutputStream**对象,构造方法中传递FileOutputStream对象,提高FileOutputStream对象效率
3. 使用BufferedOutputStream对象中的方法**write**,把数据写入到内部缓冲区中
4. 使用BufferedOutputStream对象中的方法**flush**,把内部缓冲区中的数据,刷新到文件中
5. 释放资源\(会先调用flush方法刷新数据,第4部可以省略\)

### 代码实例

```java
    public static void main(String[] args) throws IOException {
        //1.创建FileOutputStream对象,构造方法中绑定要输出的目的地
        FileOutputStream fos = new FileOutputStream("10_IO\\a.txt");
        //2.创建BufferedOutputStream对象,构造方法中传递FileOutputStream对象对象,提高FileOutputStream对象效率
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //3.使用BufferedOutputStream对象中的方法write,把数据写入到内部缓冲区中
        bos.write("我把数据写入到内部缓冲区中".getBytes());
        //4.使用BufferedOutputStream对象中的方法flush,把内部缓冲区中的数据,刷新到文件中
        bos.flush();
        //5.释放资源(会先调用flush方法刷新数据,第4部可以省略)
        bos.close();
    }
```

## 2.字节缓冲输入流 BufferedInputStream

java.io.BufferedInputStream extends InputStream

### 2.1 共性成员方法

* int read\(\)从输入流中读取数据的下一个字节。
* int read\(byte\[\] b\) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。
* void close\(\) 关闭此输入流并释放与该流关联的所有系统资源。

### 2.2 构造方法

* `BufferedInputStream(InputStream in)` 创建一个 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
* `BufferedInputStream(InputStream in, int size)` 创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。 
* **参数：**
  * InputStream in:字节输入流
    * 我们可以传递FileInputStream,缓冲流会给FileInputStream增加一个缓冲区,提高FileInputStream的读取效率
  * int size:指定缓冲流内部缓冲区的大小,不指定默认
* **使用步骤：**
  1. 创建FileInputStream对象,构造方法中绑定要读取的数据源
  2. 创建BufferedInputStream对象,构造方法中传递FileInputStream对象,提高FileInputStream对象的读取效率
  3. 使用BufferedInputStream对象中的方法read,读取文件
  4. 释放资源

### 代码实例

```java
public static void main(String[] args) throws IOException {
    //1.创建FileInputStream对象,构造方法中绑定要读取的数据源
    FileInputStream fis = new FileInputStream("10_IO\\a.txt");
    //2.创建BufferedInputStream对象,构造方法中传递FileInputStream对象,提高FileInputStream对象的读取效率
    BufferedInputStream bis = new BufferedInputStream(fis);
    //3.使用BufferedInputStream对象中的方法read,读取文件
    //int read()从输入流中读取数据的下一个字节。
    int len = 0;//记录每次读取到的字节
    while((len = bis.read())!=-1){
        System.out.println(len);
    }

    //int read(byte[] b) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。
    byte[] bytes =new byte[1024];//存储每次读取的数据
    int len = 0; //记录每次读取的有效字节个数
    while((len = bis.read(bytes))!=-1){
        System.out.println(new String(bytes,0,len));
    }

    //4.释放资源
    bis.close();
}
```

## 3.字符缓冲输出流 BufferedWriter

* java.io.BufferedWriter extends Writer

### 3.1 共性成员方法

* `void write(int c)` 写入单个字符。
* `void write(char[] cbuf)`写入字符数组。
* `abstract  void write(char[] cbuf, int off, int len)`写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
* `void write(String str)`写入字符串。
* `void write(String str, int off, int len)` 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
* `void flush()`刷新该流的缓冲。
* `void close()` 关闭此流，但要先刷新它。

### 3.2 构造方法

* BufferedWriter\(Writer out\) 创建一个使用默认大小输出缓冲区的缓冲字符输出流。
* BufferedWriter\(Writer out, int size\) 创建一个使用给定大小输出缓冲区的新缓冲字符输出流。
* 参数：
  * `Writer out`:字符输出流
    * 我们可以传递FileWriter,缓冲流会给FileWriter增加一个缓冲区,提高FileWriter的写入效率
  * `int size`:指定缓冲区的大小,不写默认大小

### 3.3 特有成员方法

* `void newLine()` 写入一个行分隔符。会根据不同的操作系统,获取不同的行分隔符 换行:换行符号

### 3.4 使用步骤

1. 创建字符缓冲输出流对象,构造方法中传递字符输出流
2. 调用字符缓冲输出流中的方法write,把数据写入到内存缓冲区中
3. 调用字符缓冲输出流中的方法flush,把内存缓冲区中的数据,刷新到文件中
4. 释放资源

### 代码实例

```java
public static void main(String[] args) throws IOException {
    //System.out.println();
    //1.创建字符缓冲输出流对象,构造方法中传递字符输出流
    BufferedWriter bw = new BufferedWriter(new FileWriter("10_IO\\c.txt"));
    //2.调用字符缓冲输出流中的方法write,把数据写入到内存缓冲区中
    for (int i = 0; i <10 ; i++) {
        bw.write("传智播客");
        //bw.write("\r\n");
        bw.newLine();
    }
    //3.调用字符缓冲输出流中的方法flush,把内存缓冲区中的数据,刷新到文件中
    bw.flush();
    //4.释放资源
    bw.close();
}
```

## 4. 字符缓冲输出流 BufferedReader

* java.io.BufferedReader extends Reader

### 4.1 共性成员方法

* `int read()` 读取单个字符并返回。
* `int read(char[] cbuf)`一次读取多个字符,将字符读入数组。
* `void close()` 关闭该流并释放与之关联的所有资源。

### 4.2 构造方法

* `BufferedReader(Reader in)`  
  * 创建一个使用默认大小输入缓冲区的缓冲字符输入流。
* `BufferedReader(Reader in, int sz)` 
  * 创建一个使用指定大小输入缓冲区的缓冲字符输入流。
* 参数：
  * Reader in:字符输入流
    * 我们可以传递FileReader,缓冲流会给FileReader增加一个缓冲区,提高FileReader的读取效率

### 4.3 特有成员方法

* `String readLine()` 读取一个文本行。读取一行数据
  * 行的终止符号:通过下列字符之一即可认为某行已终止：换行 \('\n'\)、回车 \('\r'\) 或回车后直接跟着换行\(\r\n\)。
* 返回值:
  * 包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null

### 4.4 使用步骤

1. 创建字符缓冲输入流对象,构造方法中传递字符输入流
2. 使用字符缓冲输入流对象中的方法read/readLine读取文本
3. 释放资源

### 代码实例

```java
public static void main(String[] args) throws IOException {
    //1.创建字符缓冲输入流对象,构造方法中传递字符输入流
    BufferedReader br = new BufferedReader(new FileReader("10_IO\\c.txt"));

    //2.使用字符缓冲输入流对象中的方法read/readLine读取文本
    /*
        发下以上读取是一个重复的过程,所以可以使用循环优化
        不知道文件中有多少行数据,所以使用while循环
        while的结束条件,读取到null结束
     */
    String line;
    while((line = br.readLine())!=null){
        System.out.println(line);
    }

    //3.释放资源
    br.close();
}
```

