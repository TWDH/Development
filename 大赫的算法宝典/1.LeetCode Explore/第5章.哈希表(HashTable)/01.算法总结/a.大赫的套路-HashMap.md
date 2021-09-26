# HashMap套路

## 提供更多信息

```java
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    Map<Type, InfoType> hashmap = new HashMap<>();
    //遍历集合
    for (Type key : keys) {
        //如果hashmap中存在key
        if (hashmap.containsKey(key)) {
            //如果key对应的value满足条件
            if (hashmap.get(key) satisfies the requirement) {
                //返回该条件
                return needed_information;
            }
        }
        //value可以放入任何信息(比如index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
```

## 按键聚合

```java
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    // Replace Type and InfoType with actual type of your key and value
    Map<Type, InfoType> hashmap = new HashMap<>();
    for (Type key : keys) {
        if (hashmap.containsKey(key)) {
            hashmap.put(key, updated_information);
        }
        // Value can be any information you needed (e.g. index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
```

# 设计键Key

这里有一些为你准备的关于如何设计键的建议。

1. 当字符串 / 数组中每个元素的顺序不重要时，可以使用排序后的字符串 / 数组作为键。
   1. ![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/09/06/screen-shot-2018-02-28-at-144518.png)
2. 如果只关心每个值的偏移量，通常是第一个值的偏移量，则可以使用偏移量作为键。
   1. ![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/09/06/screen-shot-2018-02-28-at-144738.png)
3. 在树中，你有时可能会希望直接使用 TreeNode 作为键。 但在大多数情况下，采用子树的序列化表述可能是一个更好的主意。
   1. ![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/09/06/screen-shot-2018-02-28-at-143858.png)
4. 在矩阵中，你可能希望使用行索引或列索引作为键。
5. 在数独中，可以将行索引和列索引组合来标识此元素属于哪个块。
   1. ![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/09/06/screen-shot-2018-02-28-at-145454.png)
6. 有时，在矩阵中，您可能希望将值聚合在同一对角线中。
   1. ![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/09/06/screen-shot-2018-02-28-at-140029.png)


