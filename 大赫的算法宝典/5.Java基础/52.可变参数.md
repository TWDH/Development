# 可变参数

## 1.概述

* 参数列表数据类型已经确定
* 参数的个数不确定
* 格式：
  * `修饰符 返回值类型 方法名(数据类型...变量名){}` 
* 原理：
  * 底层就是一个数组
  * 传递参数个数不同,会创建不同长度的数组
  * 可以是0个\(不传递\),1,2...多个

```java
//main
public static void main(String[] args) {
    //int i = add();
    //int i = add(10);
    int i = add(10,20);
    //int i = add(10,20,30,40,50,60,70,80,90,100);
    System.out.println(i);

    method("abc",5.5,10,1,2,3,4);
}

//可变参数方法
public static int add(int...arr){
    //定义一个初始化的变量,记录累加求和
    int sum = 0;
    //遍历数组,获取数组中的每一个元素
    for (int i : arr) {
        //累加求和
        sum += i;
    }
    //把求和结果返回
    return sum;
}

//可变参数的特殊(终极)写法
public static void method(Object...obj){
    ......
}
```

## 2.注意事项

1. 一个方法的参数列表,只能有一个可变参数
2. 如果方法的参数有多个,那么可变参数必须写在参数列表的末尾

