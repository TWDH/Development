# HashSet

## 1.概述

* 实现set接口，由哈希表支持
* 无序
* 非同步

## 2.特点（java.util.HashSet implements Set）

1. 不允许存储重复元素
2. 没有索引，不可用for循环（使用迭代器）
3. 无序集合，存储取出元素顺序可能不一致
4. 底层是哈希表

```java
public class HashSetDemo {
    public static void main(String[] args) {
        //创建 Set集合
        HashSet<String> set = new HashSet<>();

        //添加元素
        set.add(new String("cba"));
        set.add("abc");
        set.add("bac"); 
        set.add("cba");  
        //遍历
        for (String name : set) {
            System.out.println(name);
        }
    }
}
```

## 3. 哈希值

* 哈希值:是一个十进制的整数,由系统随机给出\(就是对象的地址值,是一个逻辑地址,是模拟出来得到地址,不是数据实际存储的物理地址\) 
* 方法
  * `int hashCode()` 返回该对象的哈希码值。
  * 源码：
    * `public native int hashCode();`
    * native:代表该方法调用的是本地操作系统的方法

```java
public static void main(String[] args) {
    //Person类继承了Object类,所以可以使用Object类的hashCode方法
    Person p1 = new Person();
    int h1 = p1.hashCode();
    System.out.println(h1);//1967205423  | 1
    
    Person p2 = new Person();
    int h2 = p2.hashCode();
    System.out.println(h2);//42121758   |  1
}
```

* String类的哈希值
  * String类重写Obejct类的hashCode方法
  * ```java
    String s1 = new String("abc");
    String s2 = new String("abc");
    System.out.println(s1.hashCode());//96354
    System.out.println(s2.hashCode());//96354

    System.out.println("重地".hashCode());//1179395
    System.out.println("通话".hashCode());//1179395
    ```

## 4.HashSet存储自定义类型元素

* 需要重写对象中的hashCode和equals方法

```java
//Person类
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    //对象一旦向上转型为父类，那么就无法调用子类原本特有的内容
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
=================================================
//Demo03HashSetSavePerson
public class Demo03HashSetSavePerson {
    public static void main(String[] args) {
    
        //创建HashSet集合存储Person
        HashSet<Person> set = new HashSet<>();
        Person p1 = new Person("小美女",18);
        Person p2 = new Person("小美女",18);
        Person p3 = new Person("小美女",19);

        System.out.println(p1==p2);//false
        System.out.println(p1.equals(p2));//false
        //存入Person类
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println(set);
    }
}
```



