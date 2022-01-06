# 接口

## 概念：

* 接口是多个类的公共规范
* 接口是【引用数据类型】 

## 接口中可包含的内容

1. **常量** 
2. **抽象方法**
   * 注意事项
     1. 接口当中的抽象方法，修饰符必须是两个固定的关键字：public abstract
     2. 这两个关键字修饰符，可以选择性地省略。
     3. 方法的三要素，可以随意定义
   * ```java
     public interface MyInterfaceAbstract {
         // 这是一个抽象方法
         public abstract void methodAbs1();

         // 这也是抽象方法
         abstract void methodAbs2();

         // 这也是抽象方法
         public void methodAbs3();

         // 这也是抽象方法
         void methodAbs4();
     }
     ```
3. **默认方法**
   * 从Java 8开始，接口里允许定义默认方法。
   * ```java
     public default 返回值类型 方法名称(参数列表) {
         方法体
     }
     =================================================
     // 新添加的方法，改成默认方法
     public default void methodDefault() {
         System.out.println("这是新添加的默认方法");
     }
     =================================================
     public static void main(String[] args) {
         // 创建了实现类对象
         MyInterfaceDefaultA a = new MyInterfaceDefaultA();
         a.methodAbs(); // 调用抽象方法，实际运行的是右侧实现类。

         // 调用默认方法，如果实现类当中没有，会向上找接口
         a.methodDefault(); // 这是新添加的默认方法

         MyInterfaceDefaultB b = new MyInterfaceDefaultB();
         b.methodAbs();
         b.methodDefault(); // 实现类B覆盖重写了接口的默认方法
     }
     ```
   * 备注：接口当中的默认方法，可以解决接口升级的问题。
     * 在实现类不用变的前提下，接口添加新方法 
   * 特点：
     * 接口的默认方法，可以通过接口实现类对象，直接调用。
     * 接口的默认方法，也可以被接口实现类进行覆盖重写。 
4. **静态方法**
   * 从Java 8开始，接口当中允许定义静态方法。
   * ```java
     public static 返回值类型 方法名称(参数列表) {
         方法体
     }
     =============================================
     public static void methodStatic() {
             System.out.println("这是接口的静态方法！");
         }
     =============================================
     public static void main(String[] args) {
         // 创建了实现类对象
         MyInterfaceStaticImpl impl = new MyInterfaceStaticImpl();
    
         // 直接通过接口名称调用静态方法
         MyInterfaceStatic.methodStatic();
     }
     ```
   * 注意事项：

     * 不能通过接口实现类的对象来调用接口当中的静态方法。
     * 正确用法：通过接口名称，直接调用其中的静态方法。
5. **私有方法**
   * 问题描述： 
     * 我们需要抽取一个共有方法，用来解决两个默认方法之间重复代码的问题。 但是这个共有方法不应该让实现类使用，应该是私有化的。
   * 解决方案：
     * 从Java 9开始，接口当中允许定义私有方法。
       1. 普通私有方法：解决多个默认方法之间重复代码问题
       2. 静态私有方法：解决多个静态方法之间重复代码问题
   * ```java
     //普通私有方法
     private 返回值类型 方法名称(参数列表) {
         方法体
     }

     //静态私有方法
     private static 返回值类型 方法名称(参数列表) {
         方法体
     }
     ```
6. 接口常量
   * 接口当中也可以定义“成员变量”，但是必须使用public static final三个关键字进行修饰。
   * 格式：
     * `public static final 数据类型 常量名称 = 数据值;` 
   * 备注：
     * 一旦使用`final`关键字进行修饰，说明不可改变。
   * 注意事项
     1. 接口当中的常量，可以省略public static final，注意：不写也照样是这样。
     2. 必须进行赋值；不能不赋值。
     3. 使用完全大写的字母，用下划线进行分隔。
        * `public static final int NUM_OF_MY_CLASS = 12;` 

## 定义：

* ```java
  public interface 接口名称{
      //接口内容
  }
  ```

## 使用步骤:

1. 接口不能直接使用，必须有一个【实现类】来“实现”该接口。
   * ```java
     public class 实现类名称 implements 接口名称 {
         // ...
     }
     ```
2. 接口的实现类必须覆盖重写（实现）接口中所有的抽象方法
   * 实现：去掉abstract关键字，加上方法体大括号。
   * ```java
     public class MyInterfaceAbstractImpl implements MyInterfaceAbstract {
         @Override
         public void methodAbs1() {
             System.out.println("这是第一个方法！");
         }

         @Override
         public void methodAbs2() {
             System.out.println("这是第二个方法！");
         }

         @Override
         public void methodAbs3() {
             System.out.println("这是第三个方法！");
         }

         @Override
         public void methodAbs4() {
             System.out.println("这是第四个方法！");
         }
     }
     ```
3. 创建实现类的对象，进行使用
   * ```java
     public class Demo01Interface {
         public static void main(String[] args) {
             // 错误写法！不能直接new接口对象使用。
     //        MyInterfaceAbstract inter = new MyInterfaceAbstract();

             // 创建实现类的对象使用
             MyInterfaceAbstractImpl impl = new MyInterfaceAbstractImpl();
             impl.methodAbs1();
             impl.methodAbs2();
         }
     }
     ```

## 注意事项：

1. 接口是没有静态代码块或者构造方法的。
2. 一个类的直接父类是唯一的，但是一个类可以同时实现多个接口。
   * ```java
     public class MyInterfaceImpl implements MyInterfaceA, MyInterfaceB {
         // 覆盖重写所有抽象方法
     }
     ```
3. 如果实现类所实现的多个接口当中，存在重复的抽象方法，那么只需要覆盖重写一次即可。
4. 如果实现类并没有覆盖重写接口中所有的抽象方法，那么这个实现类自己就必须是抽象类。
5. 如果实现类锁实现的多个接口当中，存在重复的默认方法，那么实现类一定要对冲突的默认方法进行覆盖重写。
6. 一个类如果直接父类当中的方法，和接口当中的默认方法产生了冲突，优先用父类当中的方法。

