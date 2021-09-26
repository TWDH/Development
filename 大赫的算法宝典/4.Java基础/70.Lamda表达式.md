# Lamda表达式

## 1.Lambda标准格式

* 由三部分组成
  * 参数
  * 箭头
  * 代码
* 格式
  * （参数列表）-&gt; {方法体} 
* 本质：**重写方法！！！**
  * **函数式接口时**

## 2. Lambda可推到，可省略

*  凡是根据上下文**推导**出来的内容,都可以**省略**书写
* 可省略内容
  1. \(参数列表\):括号中参数列表的**数据类型**,可以省略不写
  2. \(参数列表\):括号中的参数如果只有一个,那么**类型**和**（）**都可以省略
  3. {一些代码}:如果{}中的代码只有一行,无论是否有返回值,都可以省略
     1. {}
     2. return
     3. 分号 
     4. 注意:要省略{},return,分号必须一起省略

## 3.Lambda的使用前提

* Lambda必须使用接口，且接口**有且仅有一个**
* Lambda必须具有**上下文判断**
  * 方法的**参数**/**局部变量**类型必须为Lambda对应的接口类型，才能使Lambda作为该接口实例
* 有且仅有一个抽象方法的接口，称为“**函数式接口**”

### Lamda-练习

* Cook接口

```java
public interface Cook {
    //定义无参数无返回值的方法makeFood
    public abstract void makeFood();
}
```

* main-Lambda

```java
public class Demo01Cook {
    public static void main(String[] args) {
        //调用invokeCook方法,参数是Cook接口,传递Cook接口的匿名内部类对象
        invokeCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("吃饭了");
            }
        });

        //使用Lambda表达式,简化匿名内部类的书写
        invokeCook(()->{
            System.out.println("吃饭了");
        });

        //优化省略Lambda
        invokeCook(()-> System.out.println("吃饭了"));
    }

    //定义一个方法,参数传递Cook接口,方法内部调用Cook接口中的方法makeFood
    public static void invokeCook(Cook cook){
        cook.makeFood();
    }
}
```

### Lambda-练习-有参数返回值\(排序\)

```java
/*
    Lambda表达式有参数有返回值的练习
    需求:
        使用数组存储多个Person对象
        对数组中的Person对象使用Arrays的sort方法通过年龄进行升序排序
 */
public class Demo01Arrays {
    public static void main(String[] args) {
        //使用数组存储多个Person对象
        Person[] arr = {
                new Person("柳岩",38),
                new Person("迪丽热巴",18),
                new Person("古力娜扎",19)
        };

        //对数组中的Person对象使用Arrays的sort方法通过年龄进行升序(前边-后边)排序
        /*Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });*/

        //使用Lambda表达式,简化匿名内部类
        Arrays.sort(arr,(Person o1, Person o2)->{
            return o1.getAge()-o2.getAge();
        });

        //优化省略Lambda
        Arrays.sort(arr,(o1, o2)->o1.getAge()-o2.getAge());

        //遍历数组
        for (Person p : arr) {
            System.out.println(p);
        }
    }
}
```

### Lambda-练习-有参数返回值\(sum\)

```java
public interface Calculator {
    //定义一个计算两个int整数和的方法并返回结果
    public abstract int calc(int a,int b);
}
----------------------------------------------
/*
    Lambda表达式有参数有返回值的练习
    需求:
        给定一个计算器Calculator接口，内含抽象方法calc可以将两个int数字相加得到和值
        使用Lambda的标准格式调用invokeCalc方法，完成120和130的相加计算
 */
public class Demo01Calculator {
    public static void main(String[] args) {
        //调用invokeCalc方法,方法的参数是一个接口,可以使用匿名内部类
        invokeCalc(10, 20, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a+b;
            }
        });

        //使用Lambda表达式简化匿名内部类的书写
        invokeCalc(120,130,(int a,int b)->{
            return a + b;
        });

        //优化省略Lambda
        invokeCalc(120,130,(a,b)-> a + b);
    }

    /*
        定义一个方法
        参数传递两个int类型的整数
        参数传递Calculator接口
        方法内部调用Calculator中的方法calc计算两个整数的和
     */
    public static void invokeCalc(int a,int b,Calculator c){
        int sum = c.calc(a,b);
        System.out.println(sum);
    }
}
```

