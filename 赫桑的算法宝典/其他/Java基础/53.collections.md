# Collections

## 1.概述

* 集合工具类，用来对集合进行操作
* **Set + List**

## 2.常用方法

### 2.1 addAll

* `public static <T> boolean addAll(Collection<T> c, T... elements)`:往集合中添加一些元素。
  * `Collections.addAll(list, 5, 222, 1，2);`

### 2.2 shuffle

* `public static void shuffle(List<?> list) 打乱顺序`:打乱集合顺序。
  * `Collections.shuffle(list);` 

### 2.3.1 sort

* `public static <T> void sort(List<T> list)`:将集合中元素按照默认规则排序。
  * `Collections.sort(list01);//默认是升序` 
  * 英文字母 --- 从小到大
  * 自定义类型 --- 无法排序（实现comparable）

### 2.3.2 Comparator

* `public static <T> void sort(List<T> list，Comparator<? super T> )`:将集合中元素按照指定规则排序。

