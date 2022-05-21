# HashMap

## 1.概述

* 无序
* 非同步

## 2.特点

1. HashMap集合底层是哈希表:查询的速度特别的快
   * JDK1.8之前:数组+单向链表
   * JDK1.8之后:数组+单向链表\|红黑树\(链表的长度超过8\):提高查询的速度 
2. hashMap集合是一个无序的集合,存储元素和取出元素的顺序有可能不一致

## 3.LinkedHashMap

### 3.1特点

1. LinkedHashMap集合底层是哈希表+链表\(保证迭代的顺序\)
2. LinkedHashMap集合是一个有序的集合,存储元素和取出元素的顺序是一致的

## 4.常用方法

### 4.1 put

* `public V put(K key, V value)`:  把指定的键与指定的值添加到Map集合中。
  * 返回值
    * key重复：返回被替换的value值
    * key不重复：返回null

### 4.2 remove

* `public V remove(Object key)`: 把指定的键 所对应的键值对元素 在Map集合中删除，返回被删除元素的值。
  * 返回值
    * key存在,v返回被删除的值
    * key不存在,v返回null

### 4.3 get

* `public V get(Object key)` 根据指定的键，在Map集合中获取对应的值。
  * 返回值
    * key存在,返回对应的value值
    * key不存在,返回null

### 4.4 containsKey

* `boolean containsKey(Object key)` 判断集合中是否包含指定的键。
  * 返回值
    * 包含返回true
    * 不包含返回false

### 4.5 keySet

* `public Set<K> keySet()`: 获取Map集合中所有的键，存储到Set集合中。

### 4.6 Set

* `public Set<Map.Entry<K,V>> entrySet()`: 获取到Map集合中所有的键值对对象的集合\(Set集合\)。

## 5.遍历

* **方法一**：通过键找值
  * 方法：Set keySet\(\) 返回此映射中包含的键的 Set 视图。
  * 实现步骤：
    1. 使用Map集合中的方法`keySet()`,把Map集合所有的key取出来,存储到一个Set集合中
    2. 遍历set集合,获取Map集合中的每一个key
    3. 通过Map集合中的方法get\(key\),通过key找到value

```java
public static void main(String[] args) {
    //创建Map集合对象
    Map<String,Integer> map = new HashMap<>();
    map.put("赵丽颖",168);
    map.put("杨颖",165);
    map.put("林志玲",178);

    //1.使用Map集合中的方法keySet(),把Map集合所有的key取出来,存储到一个Set集合中
    Set<String> set = map.keySet();

    //2.遍历set集合,获取Map集合中的每一个key
    //使用迭代器遍历Set集合
    Iterator<String> it = set.iterator();
    while (it.hasNext()){
        String key = it.next();
        //3.通过Map集合中的方法get(key),通过key找到value
        Integer value = map.get(key);
        System.out.println(key+"="+value);
    }
----------------------------------------------------------
    //使用增强for遍历Set集合
    for(String key : set){
        //3.通过Map集合中的方法get(key),通过key找到value
        Integer value = map.get(key);
        System.out.println(key+"="+value);
    }
-----------------------------------------------------------
    //使用增强for遍历Set集合
    for(String key : map.keySet()){
        //3.通过Map集合中的方法get(key),通过key找到value
        Integer value = map.get(key);
        System.out.println(key+"="+value);
    }
}
```

* 方法二：EntrySet\(见下一节\)

## 6.HashMap存储自定义类型键值

### 6.1注意

* Map集合保证key是唯一的: 
  * 作为key的元素,必须重写hashCode方法和equals方法,以保证key唯一

```java
public class Person {
    private String name;
    private  int age;

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
}
---------------------------------------------------------------
/*
    HashMap存储自定义类型键值
    key:Person类型
        Person类就必须重写hashCode方法和equals方法,以保证key唯一
    value:String类型
        可以重复
*/
private static void show02() {
    //创建HashMap集合
    HashMap<Person,String> map = new HashMap<>();
    //往集合中添加元素
    map.put(new Person("女王",18),"英国");
    map.put(new Person("秦始皇",18),"秦国");
    map.put(new Person("普京",30),"俄罗斯");
    map.put(new Person("女王",18),"毛里求斯");
    //使用entrySet和增强for遍历Map集合
    Set<Map.Entry<Person, String>> set = map.entrySet();
    for (Map.Entry<Person, String> entry : set) {
        Person key = entry.getKey();
        String value = entry.getValue();
        System.out.println(key+"-->"+value);
    }
}
```

