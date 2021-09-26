# 文件过滤器

* 在File类中有两个和ListFiles重载的方法,方法的参数传递的就是过滤器
  * `File[] listFiles(FileFilter filter)` 
  * `listFiles()` ---&gt; `FileFilter()` ---&gt; `FileFilterImpl()` ---&gt; 重写`accept()`  

### FileFilter接口

* **FileFilter接口**
  * 用于抽象路径名\(File对象\)的过滤器
  * 作用:
    * 用来过滤文件\(File对象\)
  * 抽象方法:
    * 用来过滤文件的方法
    * `boolean accept(File pathname)` 
      * 测试指定抽象路径名是否应该包含在某个路径名列表中。
      * 参数：
        * File pathname:使用ListFiles方法遍历目录,得到的每一个文件对象 
* **内核步骤：**（listFiles一共做了3件事情）
  1. listFiles方法对构造方法中传递的目录遍历，获取文件/文件夹 ---&gt; 封装为File对象
  2. listFiles方法调用参数\(File pathname\)传递的过滤器\(FileFilter filter\)中的accept方法
  3. listFiles方法把遍历得到的每一个File对象，传递给accept方法的参数pathname
     * accept返回一个boolean值
     * true：把传递过去的File对象保存到File数组中
     * false：不 把传递过去的File对象保存到File数组中 
* FileFilterImpl.java
  * ```java
    public class FileFilterImpl implements FileFilter{
        @Override
        public boolean accept(File pathname) {
            /*
                过滤的规则:
                在accept方法中,判断File对象是否是以.java结尾
                是就返回true
                不是就返回false
             */
            //如果pathname是一个文件夹,返回true,继续遍历这个文件夹
            if(pathname.isDirectory()){
                return true;
            }

            return pathname.getName().toLowerCase().endsWith(".java");
        }
    }
    ```

### 实现代码

* FilterMain.java
  * ```java
    public static void main(String[] args) {
        File file = new File("c:\\abc");
        getAllFile(file);
    }

    public static void getAllFile(File dir){
        File[] files = dir.listFiles(new FileFilterImpl());//传递过滤器对象
        for (File f : files) {
            //对遍历得到的File对象f进行判断,判断是否是文件夹
            if(f.isDirectory()){
                //f是一个文件夹,则继续遍历这个文件夹
                //我们发现getAllFile方法就是传递文件夹,遍历文件夹的方法
                //所以直接调用getAllFile方法即可:递归(自己调用自己)
                getAllFile(f);
            }else{
                //f是一个文件,直接打印即可
                System.out.println(f);
            }
        }
    }
    ```

### FilenameFilter接口

* **FilenameFilter接口**
  * 实现此接口的类实例可用于过滤器文件名。
  * 作用:
    * 用于过滤文件名称
  * 抽象方法:
    * 用来过滤文件的方法
    * `boolean accept(File dir, String name)`  
      * 测试指定文件是否应该包含在某一文件列表中。
      * 参数:
        * File dir：构造方法中传递的被遍历的目录
        * String name：使用ListFiles方法遍历目录,获取的每一个文件/文件夹的名称
    * 注意:
      * 两个过滤器接口是没有实现类的,需要我们自己写实现类,重写过滤的方法accept,在方法中自己定义过滤的规则\| 

### 实现代码

* FilenameFilter+匿名内部类.java
  * File\(dir, name\)将文件目录和名字组合起来封装为File对象
  * files中只返回 `文件夹` or `文件.java` , 如果再遇到文件夹则递归，否则输出
  * ```java
    public static void main(String[] args) {
        File file = new File("c:\\abc");
        getAllFile(file);
    }

    public static void getAllFile(File dir){
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //过滤规则,pathname是文件夹或者是.java结尾的文件返回true
                return new File(dir,name).isDirectory() || name.toLowerCase().endsWith(".java");
            }
        
        for (File f : files) {
            //对遍历得到的File对象f进行判断,判断是否是文件夹
            if(f.isDirectory()){
                //f是一个文件夹,则继续遍历这个文件夹
                //我们发现getAllFile方法就是传递文件夹,遍历文件夹的方法
                //所以直接调用getAllFile方法即可:递归(自己调用自己)
                getAllFile(f);
            }else{
                //f是一个文件,直接打印即可
                System.out.println(f);
            }
        }
    }
    ```
* Lambda表达式优化
  * ```java
    //使用Lambda表达式优化匿名内部类(接口中只有一个抽象方法)
    File[] files = dir.listFiles((File d, String name)->{
        //过滤规则,pathname是文件夹或者是.java结尾的文件返回true
        return new File(d,name).isDirectory() || name.toLowerCase().endsWith(".java");
    })
    ```
* 再优化
  * ```java
    File[] files = dir.listFiles((d,name)->new File(d,name).isDirectory() || name.toLowerCase().endsWith(".java"));
    ```



