# 数组工具栏Arrays

## 概念：

* java.util.Arrays是一个与数组相关的工具类，里面提供了大量静态方法，用来实现数组常见的操作。

## 方法：

* 数组 ---&gt; 字符串:
  * `public static String toString`\(数组\)
  * ```java
    int[] intArray = {10, 20, 30};
    // 将int[]数组按照默认格式变成字符串
    String intStr = Arrays.toString(intArray);
    System.out.println(intStr); // [10, 20, 30]
    ```
* 排序：按照默认升序（从小到大）对数组的元素进行排序
  * `public static void sort`\(数组\)
  * ```java
    int[] array1 = {2, 1, 3, 10, 6};
    Arrays.sort(array1);
    System.out.println(Arrays.toString(array1)); // [1, 2, 3, 6, 10]
    ===================================================================
    String[] array2 = {"bbb", "aaa", "ccc"};
    Arrays.sort(array2);
    System.out.println(Arrays.toString(array2)); // [aaa, bbb, ccc]
    ```
* 备注：
  1. 如果是数值，sort默认按照升序从小到大
  2. 如果是字符串，sort默认按照字母升序
  3. 如果是自定义的类型，那么这个自定义的类需要有Comparable或者Comparator接口的支持。

