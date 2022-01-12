# Map-Entry

## 1.概述

* 我们已经知道，`Map`中存放的是两种对象，一种称为**key**\(键\)，一种称为**value**\(值\)，它们在在`Map`中是一一对应关系，这一对对象又称做`Map`中的一个`Entry(项)`。`Entry`将键值对的对应关系封装成了对象。

## 2.方法

### 2.1 entrySet\(\)

* `public Set<Map.Entry<K,V>> entrySet()`: 获取到Map集合中所有的键值对对象的集合\(Set集合\)。

### 2.2 getKey\(\)

* `public K getKey()`：获取Entry对象中的键。
* `public V getValue()`：获取Entry对象中的值。

```java
public static void main(String[] args) {
    //创建Map集合对象
    Map<String,Integer> map = new HashMap<>();
    map.put("赵丽颖",168);
    map.put("杨颖",165);
    map.put("林志玲",178);

    //1.使用Map集合中的方法entrySet(),把Map集合中多个Entry对象取出来,存储到一个Set集合中
    Set<Map.Entry<String, Integer>> set = map.entrySet();

    //2.遍历Set集合,获取每一个Entry对象
    //使用迭代器遍历Set集合
    Iterator<Map.Entry<String, Integer>> it = set.iterator();
    while(it.hasNext()){
        Map.Entry<String, Integer> entry = it.next();
        //3.使用Entry对象中的方法getKey()和getValue()获取键与值
        String key = entry.getKey();
        Integer value = entry.getValue();
        System.out.println(key+"="+value);
    }
    System.out.println("-----------------------");
    for(Map.Entry<String,Integer> entry:set){
        //3.使用Entry对象中的方法getKey()和getValue()获取键与值
        String key = entry.getKey();
        Integer value = entry.getValue();
        System.out.println(key+"="+value);
    }
}
```

