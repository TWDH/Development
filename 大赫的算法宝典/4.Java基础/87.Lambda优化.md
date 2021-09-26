# Lambda优化

## Runnable

```java
/*
    例如java.lang.Runnable接口就是一个函数式接口，
    假设有一个startThread方法使用该接口作为参数，那么就可以使用Lambda进行传参。
    这种情况其实和Thread类的构造方法参数为Runnable没有本质区别。
 */
public class Demo01Runnable {
    //定义一个方法startThread,方法的参数使用函数式接口Runnable
    public static void startThread(Runnable run){
        //开启多线程
        new Thread(run).start();
    }

    public static void main(String[] args) {
        //调用startThread方法,方法的参数是一个接口,那么我们可以传递这个接口的匿名内部类
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"-->"+"线程启动了");
            }
        });

        //调用startThread方法,方法的参数是一个函数式接口,所以可以传递Lambda表达式
        startThread(()->{
            System.out.println(Thread.currentThread().getName()+"-->"+"线程启动了");
        });

        //优化Lambda表达式
        startThread(()->System.out.println(Thread.currentThread().getName()+"-->"+"线程启动了"));
    }
}
```

## Comparator

```java
/*
    如果一个方法的返回值类型是一个函数式接口，那么就可以直接返回一个Lambda表达式。
    当需要通过一个方法来获取一个java.util.Comparator接口类型的对象作为排序器时,就可以调该方法获取。
 */
public class Demo02Comparator {
    //定义一个方法,方法的返回值类型使用函数式接口Comparator
    public static Comparator<String> getComparator(){
        //方法的返回值类型是一个接口,那么我们可以返回这个接口的匿名内部类
        /*return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //按照字符串的降序排序
                return o2.length()-o1.length();
            }
        };*/

        //方法的返回值类型是一个函数式接口,所有我们可以返回一个Lambda表达式
        /*return (String o1, String o2)->{
            //按照字符串的降序排序
            return o2.length()-o1.length();
        };*/

        //继续优化Lambda表达式
        return (o1, o2)->o2.length()-o1.length();
    }

    public static void main(String[] args) {
        //创建一个字符串数组
        String[] arr = {"aaa","b","cccccc","dddddddddddd"};
        //输出排序前的数组
        System.out.println(Arrays.toString(arr));//[aaa, b, cccccc, dddddddddddd]
        //调用Arrays中的sort方法,对字符串数组进行排序
        Arrays.sort(arr,getComparator());
        //输出排序后的数组
        System.out.println(Arrays.toString(arr));//[dddddddddddd, cccccc, aaa, b]
    }

}
```



