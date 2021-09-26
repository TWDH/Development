# 二叉搜索树

## 1. 特性

1. 对于 BST 的每一个节点`node`，左子树节点的值都比`node`的值要小，右子树节点的值都比`node`的值大
2. 对于 BST 的每一个节点`node`，它的左侧子树和右侧子树都是 BST
3. 可以提供 **logN 级别**的增删查改效率

## 2. 中序遍历：升序

* BST 的**中序遍历**结果是**有序的**（**升序**）

```java
void traverse(TreeNode root) {
    if (root == null) return;
    traverse(root.left);
    // 中序遍历代码位置
    print(root.val);
    traverse(root.right);
}
```

#### 相关练习：中序遍历

[1.二叉搜索树中第K小的元素](3.3二叉搜索树/1.二叉搜索树中第K小的元素.md)

[2.把二叉搜索树转换为累加树](3.3二叉搜索树/2.把二叉搜索树转换为累加树.md)

## 3. 判断 BST 的合法性

![image-20201202170319429](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201202170319429.png)



* 对右子树设定最小值min，当前节点右子树的所有值**均不可小于**min。比如，`root.right.left`这里计算时的min并没有变化
* 同理，左子树设定最大值max，左子树所有节点不可大于max

```java
boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
}

/* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
    // base case
    if (root == null) return true;
    // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
    if (min != null && root.val <= min.val) return false;
    if (max != null && root.val >= max.val) return false;
    // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
    return isValidBST(root.left, min, root) 
        && isValidBST(root.right, root, max);
}
```

## 4. BST遍历框架 + 搜索一个数

#### BST 遍历框架

```java
void BST(TreeNode root, int target) {
    //找到目标, 目标 = 当前值
    if (root.val == target)
    //右子树，目标 > 当前值
    if (root.val < target) 
        BST(root.right, target);
    //左子树，目标 < 当前值
    if (root.val > target)
        BST(root.left, target);
}
```

#### 搜索一个数

* 穷举所有点(低效)

```java
boolean isInBST(TreeNode root, int target) {
    if (root == null) return false;
    if (root.val == target) return true;
    // 当前节点没找到就递归地去左右子树寻找
    return isInBST(root.left, target)
        || isInBST(root.right, target);
}
```

* 使用框架遍历

```java
boolean isInBST(TreeNode root, int target) {
    if (root == null) return false;
    //目标 = 当前值
    if (root.val == target)
        return true;
    //右子树，目标 > 当前值
    if (root.val < target) 
        return isInBST(root.right, target);
    //左子树，目标 < 当前值
    if (root.val > target)
        return isInBST(root.left, target);
}
```

## 5. 插入一个数

* 一旦涉及「改」，函数就要**返回TreeNode类型**
* 对**递归**调用的**返回值进行接收**

```java
TreeNode insertIntoBST(TreeNode root, int val) {
    // 找到空位置插入新节点
    if (root == null) return new TreeNode(val);
    // if (root.val == val)
    // BST 中一般不会插入已存在元素
    if (root.val < val) 
        root.right = insertIntoBST(root.right, val);
    if (root.val > val) 
        root.left = insertIntoBST(root.left, val);
    return root;
}
```

## 6. 删除一个数

* 算法框架

```java
TreeNode deleteNode(TreeNode root, int key) {
    if (root.val == key) {
        // 找到啦，进行删除
    } else if (root.val > key) {
        // 去左子树找
        root.left = deleteNode(root.left, key);
    } else if (root.val < key) {
        // 去右子树找
        root.right = deleteNode(root.right, key);
    }
    return root;
}
```

**情况 1**：`A`恰好是末端节点，两个子节点都为空，那么它可以当场去世了。

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdHDhO70O5T6qmmt2L4r8oGMxqOy6Djmkjice1mXhiaQ9tmtWHGTBg64ohcvHoGU3lTVlCJicnvufusmg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
if (root.left == null && root.right == null)
    return null;
```

**情况 2**：`A`只有一个非空子节点，那么它要让这个孩子接替自己的位置。

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdHDhO70O5T6qmmt2L4r8oGM1B3zc4tCQLU5jKDQSyEib5NpC9bIiaib4k7I3nCiaDhGKecibib7Sw03oT2w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
// 排除了情况 1 之后
if (root.left == null) return root.right;
if (root.right == null) return root.left;
```

**情况 3**：`A`有两个子节点，麻烦了，为了不破坏 BST 的性质，`A`必须找到**左子树中最大**的那个节点，或者**右子树中最小**的那个节点来接替自己。我们以第二种方式讲解。

![img](https://mmbiz.qpic.cn/sz_mmbiz_png/gibkIz0MVqdHDhO70O5T6qmmt2L4r8oGMeoAeGRrjVuIdyKnCkS5MRibFTSIEl554dfmg1eQVWR2EumpHkW9uI5Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```java
if (root.left != null && root.right != null) {
    // 找到右子树的最小节点
    TreeNode minNode = getMin(root.right);
    // 把 root 改成 minNode
    root.val = minNode.val;
    // 转而去删除 minNode
    root.right = deleteNode(root.right, minNode.val);
}
```

三种情况分析完毕，填入框架，简化一下代码：

```java
TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    if (root.val == key) {
        // 这两个 if 把情况 1 和 2 都正确处理了
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        // 处理情况 3
        TreeNode minNode = getMin(root.right);
        root.val = minNode.val;
        root.right = deleteNode(root.right, minNode.val);
    } else if (root.val > key) {
        root.left = deleteNode(root.left, key);
    } else if (root.val < key) {
        root.right = deleteNode(root.right, key);
    }
    return root;
}

TreeNode getMin(TreeNode node) {
    // BST 最左边的就是最小的
    while (node.left != null) node = node.left;
    return node;
} 
```

## 技术总结：

1.  如果当前节点会**对下面的子节点有整体影响**，可以通过**辅助函数增长参数列表**，借助参数传递信息。
2. 在二叉树递归框架之上，扩展出一套 BST 代码框架：

```java
void BST(TreeNode root, int target) {
    if (root.val == target)
        // 找到目标，做点什么
    if (root.val < target) 
        BST(root.right, target);
    if (root.val > target)
        BST(root.left, target);
}
```



