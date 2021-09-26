# 接口多继承

## 接口多继承：

1. 类与类之间是单继承的。直接父类只有一个。
2. 类与接口之间是多实现的。一个类可以实现多个接口。
3. 接口与接口之间是多继承的。

```java
public interface MyInterface extends MyInterfaceA, MyInterfaceB {
    public abstract void method();

    @Override
    public default void methodDefault() {

    }
}
```

## 注意事项：

1. 多个父接口当中的抽象方法如果重复，没关系。
2. 多个父接口当中的默认方法如果重复，那么子接口必须进行默认方法的覆盖重写，【而且带着default关键字】。

## 接口作为方法的参数或者返回值

java.util.List正是ArrayList所实现的接口

* 左边是接口名称，右边是实现类名称，这就是多态写法
  * `List list = new ArrayList<>();`

```java
public class DemoInterface {

    public static void main(String[] args) {
        // 左边是接口名称，右边是实现类名称，这就是多态写法
        List<String> list = new ArrayList<>();

        List<String> result = addNames(list);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public static List<String> addNames(List<String> list) {
        list.add("迪丽热巴");
        list.add("古力娜扎");
        list.add("玛尔扎哈");
        list.add("沙扬娜拉");
        return list;
    }
}
```

```java
//ArrayList 初始化及赋值的两种方式
public class Demo {
    public static void main(String[] args) {
 
        //Method 1
        ArrayList<String> lists1 = new ArrayList<String>();
        lists1.add("test1");
        lists1.add("test2");
 
        //Method 2 , (double brace initialization)
        ArrayList<String> lists2 = new ArrayList<String>(){{
            add("test1");
            add("test2");
        }};
    }
}
```

