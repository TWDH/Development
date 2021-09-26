# 内部类

## 内部类

* **概念**：
  * 如果一个事物的内部包含另一个事物，那么这就是一个类内部包含另一个类。
    * 身体和心脏的关系。又如：汽车和发动机的关系。
* **格式：**
  * ```java
    修饰符 class 外部类名称 {
        修饰符 class 内部类名称 {
            // ...
        }
        // ...
    }
    ```
* **注意：**
  * 内用外，随意访问；外用内，需要内部类对象。

## 成员内部类使用

1. 间接方式：在外部类的方法当中，使用内部类；然后main只是调用外部类的方法。
2. 直接方式，公式：
   * 外部类名称.内部类名称 对象名 = new 外部类名称\(\).new 内部类名称\(\);
   * `Body.Heart heart = new Body().new Heart();` 



```java
public class Body { // 外部类

    public class Heart { // 成员内部类

        // 内部类的方法
        public void beat() {
            System.out.println("心脏跳动：蹦蹦蹦！");
            System.out.println("我叫：" + name); // 正确写法！
        }
    }

    // 外部类的成员变量
    private String name;

    // 外部类的方法
    public void methodBody() {
        System.out.println("外部类的方法");
        new Heart().beat();//匿名对象
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## 内部类同名变量

* 如果出现了重名现象，那么格式是：外部类名称.this.外部类成员变量名

```java
public class Outer {

    int num = 10; // 外部类的成员变量

    public class Inner /*extends Object*/ {

        int num = 20; // 内部类的成员变量

        public void methodInner() {
            int num = 30; // 内部类方法的局部变量
            System.out.println(num); // 局部变量，就近原则
            System.out.println(this.num); // 内部类的成员变量
            System.out.println(Outer.this.num); // 外部类的成员变量
        }
    }
}

```

## 局部内部类

* 概念：
  * 如果一个类是定义在一个方法内部的，那么这就是一个局部内部类。
  * “局部”：只有当前所属的方法才能使用它，出了这个方法外面就不能用了。
* 格式：
  * ```java
    修饰符 class 外部类名称 {
        修饰符 返回值类型 外部类方法名称(参数列表) {
            class 局部内部类名称 {
                // ...
            }
        }
    }
    ```
* 总结：public &gt; protected &gt; \(default\) &gt; private
  1. 外部类：public / \(default\)
  2. 成员内部类：public / protected / \(default\) / private
  3. 局部内部类：什么都不能写\(只有当前方法自己可以用\)

```java
class Outer {
    public void methodOuter() {
        class Inner { // 局部内部类
            int num = 10;
            public void methodInner() {
                System.out.println(num); // 10
            }
        }

        Inner inner = new Inner();
        inner.methodInner();
    }
}
===================================
//main
public class DemoMain {
    public static void main(String[] args) {
        Outer obj = new Outer();
        obj.methodOuter();//10
    }
}
```

## 局部内部类的final问题

* 局部内部类，如果希望访问所在方法的局部变量，那么这个局部变量必须是【有效final的】

## 匿名内部类

如果接口的实现类（或者是父类的子类）只需要使用唯一的一次， 那么这种情况下就可以省略掉该类的定义，而改为使用【匿名内部类】。

* 格式
  * ```java
    接口名称 对象名 = new 接口名称() {
        // 覆盖重写所有抽象方法
    };
    ===========================================
    public static void main(String[] args) {
    //        MyInterface obj = new MyInterfaceImpl();
    //        obj.method();

    //        MyInterface some = new MyInterface(); // 错误写法！

        // 使用匿名内部类，但不是匿名对象，对象名称就叫objA
        MyInterface objA = new MyInterface() {
            @Override
            public void method1() {
                System.out.println("匿名内部类实现了方法！111-A");
            }

            @Override
            public void method2() {
                System.out.println("匿名内部类实现了方法！222-A");
            }
        };
        obj.method(); //指向上面的method方法
    
        =====================================================
        //匿名内部类+匿名对象
        // 使用了匿名内部类，而且省略了对象名称，也是匿名对象
        new MyInterface() {
            @Override
            public void method1() {
                System.out.println("匿名内部类实现了方法！111-B");
            }

            @Override
            public void method2() {
                System.out.println("匿名内部类实现了方法！222-B");
            }
        }.method1();
        // 因为匿名对象无法调用第二次方法，所以需要再创建一个匿名内部类的匿名对象
        new MyInterface() {
            @Override
            public void method1() {
                System.out.println("匿名内部类实现了方法！111-B");
            }

            @Override
            public void method2() {
                System.out.println("匿名内部类实现了方法！222-B");
            }
        }.method2();
    }
    ```
* 对格式“new 接口名称\(\) {...}”进行解析：
  1. new代表创建对象的动作
  2. 接口名称就是匿名内部类需要实现哪个接口
  3. {...}这才是匿名内部类的内容 
* 注意事项：
  1. 匿名内部类，在【创建对象】的时候，只能使用唯一一次。 如果希望多次创建对象，而且类的内容一样的话，那么就需要使用单独定义的实现类了。
  2. 匿名对象，在【调用方法】的时候，只能调用唯一一次。 如果希望同一个对象，调用多次方法，那么必须给对象起个名字。
  3. 匿名内部类是省略了【实现类/子类名称】，但是匿名对象是省略了【对象名称】 强调：匿名内部类和匿名对象不是一回事！！！

