# Final

## Final关键字

* 四种常见用法：
  1. 修饰一个类
  2. 修饰一个方法
  3. 修饰一个局部变量
  4. 修饰一个成员变量
* final = 最后一个（类/方法）

## Final + 类

* 含义：当前这个类不能有任何的子类。（太监类）
* 注意：一个类如果是final的，那么其中所有的成员方法都无法进行覆盖重写（因为没儿子。）

```java
public final class MyClass /*extends Object*/ {
    public void method() {
        System.out.println("方法执行！");
    }
}
=====================================================
// 不能使用一个final类来作为父类
public class MySubClass /*extends MyClass*/ {
}
```

## Final + 方法

* 当final关键字用来修饰一个方法的时候，这个方法就是最终方法，也就是不能被覆盖重写。
* 注意事项： 对于类、方法来说，abstract关键字和final关键字不能同时使用，因为矛盾。
  * abstract必须有子类覆盖重写；final矛盾

```java
public abstract class Fu {
    public final void method() {
        System.out.println("父类方法执行！");
    }
    public abstract /*final*/ void methodAbs() ;
}
```

## Final + 局部变量

* 一旦使用final用来修饰局部变量，那么这个变量就不能进行更改。
* “一次赋值，终生不变”

```java
//基本类型
final int num2 = 200;
num2 = 250; // 错误写法！不能改变！
========================================
//引用类型
final Student stu2 = new Student("高圆圆");
stu2 = new Student("赵又廷");// 错误写法！final的引用类型变量，其中的地址不可改变
```

* 对于【基本类型】来说，不可变说的是变量当中的\[【数据】不可改变
* 对于【引用类型】来说，不可变说的是变量当中的【地址值】不可改变;但可以用set函数改变内容

## Final + 成员变量

* 对于成员变量来说，如果使用final关键字修饰，那么这个变量也照样是不可变。
* 注意事项：
  1. 由于成员变量具有默认值，所以用了final之后必须手动赋值，不会再给默认值了。不然以后一直是NULL
  2. 对于final的成员变量，要么使用直接赋值，要么通过构造方法赋值。二者选其一。
  3. 必须保证类当中所有重载的构造方法，都最终会对final的成员变量进行赋值。

```java
public class Person {

    private final String name/* = "鹿晗"*/;
    
    public Person() {
        name = "关晓彤";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
}
```



