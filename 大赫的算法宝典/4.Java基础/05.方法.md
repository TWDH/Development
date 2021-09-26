# 方法

* return：没有返回值，只是结束方法的执行

## 重载（overload）

方法重载与下列因素相关： 

1. 参数个数不同 
2. 参数类型不同
3. 参数的多类型顺序不同

方法重载与下列因素无关：

1. 与参数的名称无关 
2. 与方法的返回值类型无关
   * 相同参数列表不能使用两个不同的返回值
   * 同一名字的方法，返回值必须相同

```java
public class Demo01MethodOverload {

    public static void main(String[] args) {
        /*System.out.println(sumTwo(10, 20)); // 30
        System.out.println(sumThree(10, 20, 30)); // 60
        System.out.println(sumFour(10, 20, 30, 40)); // 100*/

        System.out.println(sum(10, 20)); // 两个参数的方法
        System.out.println(sum(10, 20, 30)); // 三个参数的方法
        System.out.println(sum(10, 20, 30, 40)); // 四个参数的方法
//        System.out.println(sum(10, 20, 30, 40, 50)); // 找不到任何方法来匹配，所以错误！

        sum(10, 20);
    }

    public static int sum(int a, double b) {
        return (int) (a + b);
    }

    public static int sum(double a, int b) {
        return (int) (a + b);
    }

    public static int sum(int a, int b) {
        System.out.println("有2个参数的方法执行！");
        return a + b;
    }

    // 错误写法！与方法的返回值类型无关
//    public static double sum(int a, int b) {
//        return a + b + 0.0;
//    }

    // 错误写法！与参数的名称无关
//    public static int sum(int x, int y) {
//        return x + y;
//    }

    public static int sum(double a, double b) {
        return (int) (a + b);
    }

    public static int sum(int a, int b, int c) {
        System.out.println("有3个参数的方法执行！");
        return a + b + c;
    }

    public static int sum(int a, int b, int c, int d) {
        System.out.println("有4个参数的方法执行！");
        return a + b + c + d;
    }
}
```



