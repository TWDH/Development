# Iterator迭代器

## 1. Iterator接口

* 迭代
  1. 判断集合中有没有元素
  2. 如果有：取出 + 继续判断
  3. 一直取出全部元素 
* Iterator迭代器是接口，需要使用Iterator实现类对象
  * `Iterator<E> iterator()` 返回迭代器的实现类对象
    * `coll.iterator();` 集合.iterator\(\)

## 2. 常用方法

* `public E next()`:返回迭代的下一个元素。
* `public boolean hasNext()`:如果仍有元素可以迭代，则返回 true。

## 3.使用步骤

1. `iterator()`获取实现类对象，Iterator接口接收
2. `hasNext()`判断有没有下一个元素
3. `next()`取出下一个元素

```java
//创建一个集合对象
Collection<String> coll = new ArrayList<>();
//往集合中添加元素
coll.add("姚明");
coll.add("科比");
coll.add("麦迪");
coll.add("詹姆斯");
coll.add("艾弗森");
=================================================
/*
    1.使用集合中的方法iterator()获取迭代器的实现类对象,使用Iterator接口接收(多态)
    注意:
        Iterator<E>接口也是有泛型的,迭代器的泛型跟着集合走,集合是什么泛型,迭代器就是什么泛型
*/
//多态  接口            实现类对象
Iterator<String> it = coll.iterator();
=================================================

/*
    发现使用迭代器取出集合中元素的代码,是一个重复的过程
    所以我们可以使用循环优化
    不知道集合中有多少元素,使用while循环
    循环结束的条件,hasNext方法返回false
*/
// while循环模式
while(it.hasNext()){
    String e = it.next();
    System.out.println(e);
}
=================================================
// for循环模式
for(Iterator<String> it2 = coll.iterator(); it2.hasNext();){
    String e = it2.next();
    System.out.println(e);
}
```

