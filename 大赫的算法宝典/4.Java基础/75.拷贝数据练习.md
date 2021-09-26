# 拷贝数据练习

* `len = fis.read(bytes)` 注意一次读取多个bytes字节

```java
public class CopyFile {
    /*
    文件复制练习:一读一写

    明确:
        数据源: c:\\1.jpg
        数据的目的地: d:\\1.jpg

    文件复制的步骤:
        1.创建一个字节输入流对象,构造方法中绑定要读取的数据源
        2.创建一个字节输出流对象,构造方法中绑定要写入的目的地
        3.使用字节输入流对象中的方法read读取文件
        4.使用字节输出流中的方法write,把读取到的字节写入到目的地的文件中
        5.释放资源
 */
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("D:\\Java\\0.资料\\基础\\IOStream\\A\\1.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\Java\\0.资料\\基础\\IOStream\\B\\1.jpg");

//        int len = 0;
//        while ((len = fis.read()) != -1) {
//            fos.write(len);
//        }

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }


        fos.close();
        fis.close();

        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end-start) + "毫秒");
    }
}
```

