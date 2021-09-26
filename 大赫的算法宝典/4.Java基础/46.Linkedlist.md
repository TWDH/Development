# LinkedList

## 1.概述

* List接口的链表实现
* 非同步

## 2.方法

* `public void addFirst(E e)`:将指定元素插入此列表的开头。
* `public void addLast(E e)`:将指定元素添加到此列表的结尾。 
* `public E getFirst()`:返回此列表的第一个元素。
* `public E getLast()`:返回此列表的最后一个元素。 
* `public E removeFirst()`:移除并返回此列表的第一个元素。
* `public E removeLast()`:移除并返回此列表的最后一个元素。 
* `public E pop()`:从此列表所表示的堆栈处弹出一个元素+返回。                            =`removeFirst()`
* `public void push(E e)`:将元素推入此列表所表示的堆栈+返回。                                  = `addFirst()`

  

* `public boolean isEmpty()`：如果列表不包含元素，则返回true。

## 3.特点

1. 底层是一个链表：查询慢，增删快
2. 里边包含大量首位元素的方法

* 注意：
  * 使用LinkedList集合特有的方法，不能使用多态。（多态不能访问子类特有方法）

