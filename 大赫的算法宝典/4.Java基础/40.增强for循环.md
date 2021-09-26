# 增强for循环

## 1.概述

* 专门用来遍历数组和集合的，它的内部原理其实是个Iterator迭代器
* 只能用于遍历【Collection】和【数组】

## 2.格式

```java
for(元素的数据类型  变量 : Collection集合or数组){ 
      //写操作代码
}
==========================================
private static void demo01() {
    int[] arr = {1,2,3,4,5};
    for(int i:arr){
        System.out.println(i);
    }
}
```

