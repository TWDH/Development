# HashMap和HashSet的区别

## HashMap存储对象的过程

- 对HashMap的Key**调用hashCode()**方法，返回int值，即对应的hashCode；
- 把此hashCode作为哈希表的索引，查找哈希表的相应位置，若当前位置内容为**NULL**，则把HashMap的Key、Value包装成Entry数组，放入当前位置；
- 若当前位置内容**不为空**，则继续查找当前索引处存放的链表，利用equals方法，找到Key相同的Entry数组，则用当前Value去替换旧的Value；
- 若**未找到**与当前Key值相同的对象，则把当前位置的链表后移（Entry数组持有一个指向下一个元素的引用），把新的Entry数组放到链表表头；



## 小提示：

- HashSet实现了Set接口，其内部不允许出现重复的值，如果我们将一个**对象**存入HashSet，必须**重写equals()和hashCode()**方法，这样才能确保集合中不存在同一个元素。HashSet的 **内部是无序 **的，因此不能使用 hashset.get(index) 来获取元素。
- HashMap实现了Map接口，其内容是键值对的映射（key->value），不允许出现相同的键（key）。在查询的时候会根据给出的键来查询对应的值。
- 我们可以认为，HashSet和HashMap增查操作的 **时间复杂度都是常数级** 的。推荐：[250期面试题汇总](http://mp.weixin.qq.com/s?__biz=MzIyNDU2ODA4OQ==&mid=2247489003&idx=1&sn=69bf19d900079e204e36df58525654bf&chksm=e80da39ddf7a2a8bf0765f9b95f359a3944fc40c4a192bb3fe9adedfbcd0070cd27234bcf6b3&scene=21#wechat_redirect)



## 哈希冲突解决方法

冲突（Collision），是说两个不同的 key 经过哈希函数的计算后，得到了两个相同的值。解决冲突的方法，主要有两种：

- 开散列法（Open Hashing）。是指哈希表所基于的数组中，每个位置是一个 Linked List 的头结点。这样冲突的 <key,value> 二元组，就都**放在同一个链表中**。
- 闭散列法（Closed Hashing）是指在发生冲突的时候，后来的元素，**往下一个位置**去找空位。











































