# 完全二叉树

## **1. 完全二叉树 (Complete Binary Tree)**

* 每一层都是**紧凑靠左**排列的

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdEP9mVB4tqBYfNT69s5yTbm0UlN0d2fmKXn6RCv25WiapjvnsxLZ7mkobu5v1NPQY3cVkt8iawyMYvw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## **2. 满二叉树 (Perfect Binary Tree)**

* 特殊的完全二叉树，每层都是是满的

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdEP9mVB4tqBYfNT69s5yTbmKVl4jaKTlfnzFt9ibcWmF6orEyNypVUsmqV58Jx92icb18rIrxgFp8gA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



#### Full Binary Tree

* 一棵二叉树的所有节点要么没有孩子节点，要么有两个孩子节点

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdEP9mVB4tqBYfNT69s5yTbmibx5TKD3fDUlt1dNVsAC6ox8VJTHFRznRNAwCBwUJt0nZhrBMp1VONA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## 3. 计算节点个数

#### 3.1 普通二叉树

```java
public int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

#### 3.2 满二叉树

* 节点总数就和树的高度呈指数关系，时间复杂度 O(logN)

```java
public int countNodes(TreeNode root) {
    int h = 0;
    // 计算树的高度
    while (root != null) {
        root = root.left;
        h++;
    }
    // 节点总数就是 2^h - 1
    return (int)Math.pow(2, h) - 1;
}
```

#### 3.3 完全二叉树

* 普通二叉树和完全二叉树的结合版
* 如果其中某棵**子树**是**满二叉树**，可以快速使用 Math.pow 返回其数量。
* 复杂度：**O(logN * logN)**
  * 算法的递归深度就是树的高度 O(logN)
    * 直觉递归所有节点需要 O(N)，但是**这两个递归只有一个会真的递归下去，另一个一定会触发`hl == hr`而立即返回，不会递归下去。由于完全二叉树的性质，其子树一定有一棵是满的。**
  * 每次递归所花费的时间就是 while 循环，需要 O(logN)

![image-20201203143841491](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201203143841491.png)

```java
public int countNodes(TreeNode root) {
    TreeNode l = root, r = root;
    // 记录左、右子树的高度
    int hl = 0, hr = 0;
    while (l != null) {
        l = l.left;
        hl++;
    }
    while (r != null) {
        r = r.right;
        hr++;
    }
    // 如果左右子树的高度相同，则是一棵满二叉树
    if (hl == hr) {
        return (int)Math.pow(2, hl) - 1;
    }
    // 如果左右高度不同，则按照普通二叉树的逻辑计算
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```











