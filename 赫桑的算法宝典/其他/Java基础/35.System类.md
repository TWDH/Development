# System类

## 概述

* 可以获取与系统相关的信息或系统级操作

## 方法\(currentTimeMillis + arraycopy\)

* `public static long currentTimeMillis()`：返回以毫秒为单位的当前时间。
  * ```java
    public class SystemDemo {
        public static void main(String[] args) {
           	//获取当前时间毫秒值
            System.out.println(System.currentTimeMillis()); // 1516090531144
        }
    }
    ```
* `public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)`：将数组中指定的数据拷贝到另一个数组中。

#### 数组的拷贝动作是系统级的，性能很高。System.arraycopy方法具有5个参数，含义分别为：

| 参数序号 | 参数名称 | 参数类型 | 参数含义 |
| :--- | :--- | :--- | :--- |
| 1 | src | Object | 源数组 |
| 2 | srcPos | int | 源数组索引起始位置 |
| 3 | dest | Object | 目标数组 |
| 4 | destPos | int | 目标数组索引起始位置 |
| 5 | length | int | 复制元素个数 |

```java
/*将src数组中前3个元素，复制到dest数组的前3个位置上复制元素前：
src数组元素[1,2,3,4,5]，dest数组元素[6,7,8,9,10]复制元素后：
src数组元素[1,2,3,4,5]，dest数组元素[1,2,3,9,10]
*/
public class Demo11SystemArrayCopy {
    public static void main(String[] args) {
        int[] src = new int[]{1,2,3,4,5};
        int[] dest = new int[]{6,7,8,9,10};
        System.arraycopy( src, 0, dest, 0, 3);
        /*代码运行后：两个数组中的元素发生了变化
         src数组元素[1,2,3,4,5]
         dest数组元素[1,2,3,9,10]
        */
    }
}
```

