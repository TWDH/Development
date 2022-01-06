# Object类

## ToString

* 看一个类是否重写了toString,直接打印这个类的对象即可,如果没有重写toString方法那么打印的是对象的地址值

```java
@Override
public String toString() {
   //return "abc";
   return "Person{name=" + name + " ,age=" + age + "}";
}
 ========================================
public class Demo01ToString{
  public static void main(String[] args) {
     /*
         Person类默认继承了Object类,所以可以使用Object类中的toString方法
         String toString() 返回该对象的字符串表示。
      */
     Person p = new Person("张三",18);
     String s = p.toString();
}
```

## equals

* `boolean equals(Object obj)` 指示其他某个对象是否与此对象“相等”
* 源码：
  * ```java
    public boolean equals(Object obj) {
        return (this == obj);
    }
    ```
  * **基本**数据类型:比较的是值
  * **引用**数据类型:比价的是两个对象的地址值

```java
//Person类
@Override
public boolean equals(Object obj) {
    //增加一个判断,传递的参数obj如果是this本身,直接返回true,提高程序的效率
    if(obj==this){
        return true;
    }

    //增加一个判断,传递的参数obj如果是null,直接返回false,提高程序的效率
    if(obj==null){
        return false;
    }

    //增加一个判断,防止类型转换一次ClassCastException
    if(obj instanceof Person){
        //使用向下转型,把obj转换为Person类型
        Person p = (Person)obj;
        //比较两个对象的属性,一个对象是this(p1),一个对象是p(obj->p2)
        boolean b = this.name.equals(p.name) && this.age==p.age;
        return b;
    }
    //不是Person类型直接返回false
    return false;
}
```

## Objects类 + equals

* null-save（空指针安全的）或null-tolerant（容忍空指针的）

```java
public static boolean equals(Object a, Object b) {  
    return (a == b) || (a != null && a.equals(b));  
}
=========================================
public static void main(String[] args) {
    String s1 = "abc";
    //String s1 = null; //false
    String s2 = "abc";

    boolean b2 = Objects.equals(s1, s2);
    System.out.println(b2);
}
```

