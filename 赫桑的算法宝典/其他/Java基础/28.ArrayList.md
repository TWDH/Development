# ArrayList&lt;E&gt;

## 类ArrayList&lt;E&gt;

* **特点：**
  * 数组长度不可变，但ArrayList集合长度可以变化
  * &lt;E&gt;代表泛型
    * 泛型：装在集合当中的所有元素，全都是统一的什么类型
    * 泛型只能是【引用类型】，不能是【基本类型】 
* **使用：**
  * 创建ArrayList集合，集合名称是list，里面装的是String字符串类型的数据
  * 备注：JDK1.7开始，右侧的尖括号内部可以不写内容，但是&lt;&gt;左边本身要写；左右必定一致。
  * `ArrayList<String> list = new ArrayList<>();`  
* **注意事项：**
  * 对于ArrayList集合，直接打印得到内容，而非地址值。

    * 如果内容为空，则打印`[]`
* **常用方法：**
  * 添加：public boolean add\(E e\)：参数类型与泛型一致
    * `boolean success = list.add("张三");`  
  * 获取：public E get\(int index\)：从集合获取元素，参数是索引编号，返回对应位置元素
    * `String name = list.get(2);`  
  * 删除：public E remove\(int index\)：从集合删除元素，参数是索引编号，返回值是被删除元素
    * `String removed = list.remove(3);` 
  * 长度：public int size\(\)：获取集合尺寸长度，返回值是集合中包含元素个数
    * `int size = list.size();` 

## 存储基本类型数据

* 泛型：可以存储引用，不能存储基本类型。（因为集合保存地址值，基本类型没有地址值）
* 解决方案
  * 包装类（引用类型，位于java.lang）
    * byte ---&gt; Byte
    * short ---&gt; Short
    * int ---&gt; Integer
    * long ---&gt; Long
    * float ---&gt; Float
    * double ---&gt; Double
    * char ---&gt; Character
    * boolean ---&gt; Boolean
  * `ArrayList<Integer> list = new ArrayList<>();`
* JDK1.5自动装箱，自动拆箱
  * 自动装箱：基本类型 ---&gt; 包装类型
  * 自动拆箱：包装类型---&gt;基本类型



