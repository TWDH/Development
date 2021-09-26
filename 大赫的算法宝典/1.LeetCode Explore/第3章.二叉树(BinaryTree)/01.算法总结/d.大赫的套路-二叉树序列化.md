# 二叉树的序列化与反序列化

* **所谓的序列化不过就是把结构化的数据「打平」，其实就是在考察二叉树的遍历方式**

## 1. 前序遍历的Serialize

* 将二叉树打平到List中

```java
LinkedList<Integer> res;
void traverse(TreeNode root) {
    if (root == null) {
        // 暂且用数字 -1 代表空指针 null
        res.addLast(-1);
        return;
    }

    /****** 前序遍历位置 ******/
    res.addLast(root.val);
    /***********************/

    traverse(root.left);
    traverse(root.right);
}
```

![image-20201203114314652](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201203114314652.png)

* 将二叉树打平到String中

```java
String SEP = ",";
String NULL = "#";

/* 主函数，将二叉树序列化为字符串 */
String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
}

/* 辅助函数，将二叉树存入 StringBuilder */
void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
        sb.append(NULL).append(SEP);
        return;
    }

    /****** 前序遍历位置 ******/
    sb.append(root.val).append(SEP);
    /***********************/

    serialize(root.left, sb);
    serialize(root.right, sb);
}
```

## 2. 前序遍历的Deserialize

* 首先我们可以把字符串转化成列表

```java
String data = "1,2,#,4,#,#,3,#,#,";
String[] nodes = data.split(",");
```

问题转化为：如何通过二叉树的前序遍历结果还原一棵二叉树？

* 因为node列表中**包含null**指针，所以**可以还原**二叉树

```java
/* 主函数，将字符串反序列化为二叉树结构 */
TreeNode deserialize(String data) {
    // 将字符串转化成列表
    LinkedList<String> nodes = new LinkedList<>();
    for (String s : data.split(SEP)) {
        nodes.addLast(s);
    }
    return deserialize(nodes);
}

/* 辅助函数，通过 nodes 列表构造二叉树 */
TreeNode deserialize(LinkedList<String> nodes) {
    if (nodes.isEmpty()) return null;

    /****** 前序遍历位置 ******/
    // 列表最左侧就是根节点
    String first = nodes.removeFirst();
    if (first.equals(NULL)) return null;
    TreeNode root = new TreeNode(Integer.parseInt(first));
    /***********************/
	
    //先解析左子树，再解析右子树（左子树会全部解析完 - "#"全部被remove了表示左子树结束）
    root.left = deserialize(nodes);
    root.right = deserialize(nodes);

    return root;
}
```

* `Integer.parseInt(String s)`：将括号中的参数转化成int

## 3. 后序遍历的Serialize

```java
String SEP = ",";
String NULL = "#";

/* 主函数，将二叉树序列化为字符串 */
String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
}

/* 辅助函数，将二叉树存入 StringBuilder */
void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
        sb.append(NULL).append(SEP);
        return;
    }

    serialize(root.left, sb);
    serialize(root.right, sb);

    /****** 后序遍历位置 ******/
    sb.append(root.val).append(SEP);
    /***********************/
}
```

![image-20201203114329093](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201203114329093.png)



## 4. 后序遍历的Deserialize

* **deserialize** 方法首先寻找 **root** 节点的值，然后**递归计算**左右子节点
* **root** 的值是列表的**最后一个**元素
* **从后往前在 nodes 列表中取元素，一定要先构造 root.right 子树，后构造 root.left 子树**

```java
/* 主函数，将字符串反序列化为二叉树结构 */
TreeNode deserialize(String data) {
    LinkedList<String> nodes = new LinkedList<>();
    for (String s : data.split(SEP)) {
        nodes.addLast(s);
    }
    return deserialize(nodes);
}

/* 辅助函数，通过 nodes 列表构造二叉树 */
TreeNode deserialize(LinkedList<String> nodes) {
    if (nodes.isEmpty()) return null;
    // 从后往前取出元素
    String last = nodes.removeLast();
    if (last.equals(NULL)) return null;
    TreeNode root = new TreeNode(Integer.parseInt(last));
    // 必须先构造右子树，后构造左子树
    root.right = deserialize(nodes);
    root.left = deserialize(nodes);

    return root;
}
```

## 5. 中序遍历的Serialize / Deserialize

* 行不通
* 反序列化无法找到root

## 6. 层序遍历的Serialize

* 代码框架

```java
void traverse(TreeNode root) {
    if (root == null) return;
    // 初始化队列，将 root 加入队列
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        TreeNode cur = q.poll();

        /* 层级遍历代码位置 */
        System.out.println(root.val);
        /*****************/

        if (cur.left != null) {
            q.offer(cur.left);
        }

        if (cur.right != null) {
            q.offer(cur.right);
        }
    }
}
```

* 因为Deserialize**需要记录null值**，所以稍微修改模板代码

```java
void traverse(TreeNode root) {
    if (root == null) return;
    // 初始化队列，将 root 加入队列
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        TreeNode cur = q.poll();

        /* 层级遍历代码位置 */
        if (cur == null) continue;
        System.out.println(root.val);
        /*****************/

        q.offer(cur.left);
        q.offer(cur.right);
    }
}
```

完全仿照这个框架即可写出序列化方法：

```java
String SEP = ",";
String NULL = "#";

/* 将二叉树序列化为字符串 */
String serialize(TreeNode root) {
    if (root == null) return "";
    StringBuilder sb = new StringBuilder();
    // 初始化队列，将 root 加入队列
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        TreeNode cur = q.poll();

        /* 层级遍历代码位置 */
        if (cur == null) {
            sb.append(NULL).append(SEP);
            continue;
        }
        sb.append(cur.val).append(SEP);
        /*****************/

        q.offer(cur.left);
        q.offer(cur.right);
    }

    return sb.toString();
}
```

层级遍历序列化得出的结果如下图：

![image-20201203114345654](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201203114345654.png)

## 7.层级遍历的Deserialize

* 每一个非空节点都会对应两个子节点，**那么反序列化的思路也是用队列进行层级遍历，同时用索引 `i` 记录对应子节点的位置**

```java
/* 将字符串反序列化为二叉树结构 */
TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;
    String[] nodes = data.split(SEP);
    // 第一个元素就是 root 的值
    TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

    // 队列 q 记录父节点，将 root 加入队列
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    for (int i = 1; i < nodes.length; ) {
        // 队列中存的都是父节点
        TreeNode parent = q.poll();
        // 父节点对应的左侧子节点的值
        String left = nodes[i++];
        if (!left.equals(NULL)) {
            parent.left = new TreeNode(Integer.parseInt(left));
            //左子树加入队列
            q.offer(parent.left);
        } else {
            parent.left = null;
        }
        // 父节点对应的右侧子节点的值
        String right = nodes[i++];
        if (!right.equals(NULL)) {
            parent.right = new TreeNode(Integer.parseInt(right));
            //右子树加入队列
            q.offer(parent.right);
        } else {
            parent.right = null;
        }
    }
    return root;
}
```









