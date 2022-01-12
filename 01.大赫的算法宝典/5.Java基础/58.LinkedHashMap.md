# LinkedHashMap

## 1.概述

* HashMap
  * 不允许重复
  * 无序
* LinkedHashMap
  * Map接口的哈希表和链表实现，具有可预知的迭代顺序
  * 不允许重复
  * 有序

```java
public static void main(String[] args) {
    HashMap<String,String> map = new HashMap<>();
    map.put("a","a");
    map.put("c","c");
    map.put("b","b");
    map.put("a","d");
    System.out.println(map);// key不允许重复,无序 {a=d, b=b, c=c}

    LinkedHashMap<String,String> linked = new LinkedHashMap<>();
    linked.put("a","a");
    linked.put("c","c");
    linked.put("b","b");
    linked.put("a","d");
    System.out.println(linked);// key不允许重复,有序 {a=d, c=c, b=b}
}
```

