# 属性集（properties）

## 1.概述

* java.util.Properties集合 `extends Hashtable implements Map` 
* Properties 类表示了一个持久的属性集
* Properties 可保存在流中或从流中加载
* Properties集合是一个唯一和IO流相结合的集合
  * 可以使用Properties集合中的方法**store**，把集合中的**临时数据**，持久化写入到**硬盘**中存储
  * 可以使用Properties集合中的方法**load**,把硬盘中保存的**文件**\(键值对\),读取到**集合**中使用
* 属性列表中每个**键值**都是一个**字符串**。
  * Properties集合是一个双列集合,key和value默认都是字符串

## 2. set/get 方法

### 2.1 操作字符串的特有方法

* `Object setProperty(String key, String value)` 调用 Hashtable 的方法 put。
* `String getProperty(String key)` 通过key找到value值,此方法相当于Map集合中的get\(key\)方法
* `Set stringPropertyNames()` 返回此属性列表中的键集，其中该键及其对应值是字符串,此方法相当于Map集合中的keySet方法

### 代码实例

```java
private static void show01() {
    //创建Properties集合对象
    Properties prop = new Properties();
    //使用setProperty往集合中添加数据
    prop.setProperty("赵丽颖","168");
    prop.setProperty("迪丽热巴","165");
    prop.setProperty("古力娜扎","160");
    //prop.put(1,true);

    //使用stringPropertyNames把Properties集合中的键取出,存储到一个Set集合中
    Set<String> set = prop.stringPropertyNames();

    //遍历Set集合,取出Properties集合的每一个键
    for (String key : set) {
        //使用getProperty方法通过key获取value
        String value = prop.getProperty(key);
        System.out.println(key+"="+value);
    }
}
```

## 3. Store\(\)方法

* `void store(OutputStream out, String comments)` 
* `void store(Writer writer, String comments)`  
* 参数:
  * `OutputStream out`：**字节**输出流,**不**能写入中文
  * `Writer writer`：**字符**输出流,可以写中文
  * `String comments`：注释,用来解释说明保存的文件是做什么用的 
* 使用步骤
  1. 创建Properties集合对象,添加数据
  2. 创建字节输出流/字符输出流对象,构造方法中绑定要输出的目的地
  3. 使用Properties集合中的方法store,把集合中的临时数据,持久化写入到硬盘中存储
  4. 释放资源

### 代码实例

```java
private static void show02() throws IOException {
    //1.创建Properties集合对象,添加数据
    Properties prop = new Properties();
    prop.setProperty("赵丽颖","168");
    prop.setProperty("迪丽热巴","165");
    prop.setProperty("古力娜扎","160");

    //2.创建字节输出流/字符输出流对象,构造方法中绑定要输出的目的地
    FileWriter fw = new FileWriter("09_IOAndProperties\\prop.txt");

    //3.使用Properties集合中的方法store,把集合中的临时数据,持久化写入到硬盘中存储
    prop.store(fw,"save data");

    //4.释放资源
    fw.close();
    
    //or
    prop.store(new FileOutputStream("09_IOAndProperties\\prop2.txt"),"");
}
```

## 4. Load\(\)方法

* `void load(InputStream inStream)` 
* `void load(Reader reader)`  
* **参数：**
  * InputStream inStream:**字节**输入流,**不**能读取含有中文的键值对
  * Reader reader:**字符**输入流,能读取含有中文的键值对 
* **使用步骤:**
  1. 创建Properties集合对象
  2. 使用Properties集合对象中的方法load读取保存键值对的文件
  3. 遍历Properties集合 
* **注意:**
  1. 键与值默认的连**接符号**可以使用`=`,空格\(其他符号\)。用于读取prop.txt
  2. 可以使用\#进行注释,被注释的键值对不会再被读取
  3. 键与值默认都是字符串,不用再加引号

### 代码实例

```java
private static void show03() throws IOException {
    //1.创建Properties集合对象
    Properties prop = new Properties();
    //2.使用Properties集合对象中的方法load读取保存键值对的文件
    prop.load(new FileReader("09_IOAndProperties\\prop.txt"));
    //prop.load(new FileInputStream("09_IOAndProperties\\prop.txt"));
    //3.遍历Properties集合
    Set<String> set = prop.stringPropertyNames();
    for (String key : set) {
        String value = prop.getProperty(key);
        System.out.println(key+"="+value);
    }
}
```

