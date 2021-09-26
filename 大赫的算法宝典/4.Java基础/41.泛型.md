# 泛型

## 1.概述

* 未知数据类型
* 变量 --- 用来接收数据类型
  * 数据类型（如：String）作为参数，把（`String`）赋值给泛型`E` 

```java
public class GenericDemo2 {
	public static void main(String[] args) {
        Collection<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("itcast");
        // list.add(5);//当集合明确类型后，存放类型不一致就会编译报错
        // 集合->元素类型，迭代器->元素类型 (String)
        Iterator<String> it = list.iterator();
        
        while(it.hasNext()){
            String str = it.next();
            //当使用Iterator<String>控制元素类型后，就不需要强转了。获取到的元素直接就是String类型
            System.out.println(str.length());
        }
	}
}
```

## 2.泛型的定义与使用

* 定义
  * ```java
    class ArrayList<E>{ 
        public boolean add(E e){ }

        public E get(int index){ }
       	....
    }
    ```
* 使用
  * ```java
    //GenericClass类
    public class GenericClass<E> {
        private E name;

        public E getName() {
            return name;
        }

        public void setName(E name) {
            this.name = name;
        }
    }
    ===========================================
    //
    public static void main(String[] args) {
        //不写泛型默认为Object类型
        GenericClass gc = new GenericClass();
        gc.setName("只能是字符串");
        Object obj = gc.getName();

        //创建GenericClass对象,泛型使用Integer类型
        GenericClass<Integer> gc2 = new GenericClass<>();
        gc2.setName(1);

        Integer name = gc2.getName();
        System.out.println(name);

        //创建GenericClass对象,泛型使用String类型
        GenericClass<String> gc3 = new GenericClass<>();
        gc3.setName("小明");
        String name1 = gc3.getName();
        System.out.println(name1);
    }
    ```

## 3.含有泛型的方法

* 定义格式
  * `修饰符 <代表泛型的变量> 返回值类型 方法名(参数){  }` 
* 使用格式：
  * **调用方法时，确定泛型的类型**

```java
//带有泛型的方法类
public class GenericMethod {
    //定义一个含有泛型的方法
    public <M> void method01(M m){
        System.out.println(m);
    }

    //定义一个含有泛型的静态方法
    public static <S> void method02(S s){
        System.out.println(s);
    }
}
=============================================
//main
public static void main(String[] args) {
    //创建GenericMethod对象
    GenericMethod gm = new GenericMethod();

/*
    调用含有泛型的方法method01
    传递什么类型,泛型就是什么类型
 */
     
    //成员方法 
    gm.method01(10);
    gm.method01("abc");
    gm.method01(8.8);
    gm.method01(true);
    
    //静态方法
    gm.method02("静态方法,不建议创建对象使用");

    //静态方法,通过类名.方法名(参数)可以直接使用
    GenericMethod.method02("静态方法");
    GenericMethod.method02(1);
}

```

## 4.含有泛型的接口

* 使用方式：一
  * 定义接口的实现类（impl）
  * 实现接口
  * 指定接口的泛型

```java
//GenericInterface
public interface GenericInterface<I> {
    public abstract void method(I i);
}
=========================================================
//GenericInterfaceImpl1 
public class GenericInterfaceImpl1 implements GenericInterface<String>{
    @Override
    public void method(String s) {
        System.out.println(s);
    }
}
=========================================================
//main
public static void main(String[] args) {
    //创建GenericInterfaceImpl1对象
    GenericInterfaceImpl1 gi1 = new GenericInterfaceImpl1();
    gi1.method("字符串");
}
```

* 使用方式：二
  * 接口使用什么泛型,实现类就使用什么泛型,类跟着接口走
  * 相当于定义了一个含有泛型的类,创建对象的时候确定泛型的类型

```java
//GenericInterfaceImpl2
public class GenericInterfaceImpl2<I> implements GenericInterface<I> {
    @Override
    public void method(I i) {
        System.out.println(i);
    }
}
==============================================
public static void main(String[] args) {
    //创建GenericInterfaceImpl2对象
    GenericInterfaceImpl2<Integer> gi2 = new GenericInterfaceImpl2<>();
    gi2.method(10);

    GenericInterfaceImpl2<Double> gi3 = new GenericInterfaceImpl2<>();
    gi3.method(8.8);
}
```

## 5.泛型通配符

* 概述
  * 不知道使用什么类型来接收的时候，使用`?`（表示未知通配符）
  * 只能接收数据，不能往集合中存储数据
* 使用方法：
  * 不能创建对象时（定义）使用
  * 只能作为方法的参数使用

```java
public static void main(String[] args) {
    ArrayList<Integer> list01 = new ArrayList<>();
    list01.add(1);
    list01.add(2);

    ArrayList<String> list02 = new ArrayList<>();
    list02.add("a");
    list02.add("b");

    printArray(list01);
    printArray(list02);

    //ArrayList<?> list03 = new ArrayList<?>();
}

/*
    定义一个方法,能遍历所有类型的ArrayList集合
    这时候我们不知道ArrayList集合使用什么数据类型,可以泛型的通配符?来接收数据类型
    注意:
        泛型没有继承概念的
*/
public static void printArray(ArrayList<?> list){
    //使用迭代器遍历集合
    Iterator<?> it = list.iterator();
    while(it.hasNext()){
        //it.next()方法,取出的元素是Object,可以接收任意的数据类型
        Object o = it.next();
        System.out.println(o);
    }
}
```

* 泛型的上限和下限

```java
public class Demo06Generic {
    public static void main(String[] args) {
        Collection<Integer> list1 = new ArrayList<Integer>();
        Collection<String> list2 = new ArrayList<String>();
        Collection<Number> list3 = new ArrayList<Number>();
        Collection<Object> list4 = new ArrayList<Object>();

        getElement1(list1);
        //getElement1(list2);//报错
        getElement1(list3);
        //getElement1(list4);//报错

        //getElement2(list1);//报错
        //getElement2(list2);//报错
        getElement2(list3);
        getElement2(list4);

        /*
            类与类之间的继承关系
            Integer extends Number extends Object
            String extends Object
         */

    }
    // 泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类
    public static void getElement1(Collection<? extends Number> coll){}
    // 泛型的下限：此时的泛型?，必须是Number类型或者Number类型的父类
    public static void getElement2(Collection<? super Number> coll){}
}
```

