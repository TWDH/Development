![image-20201129162559539](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201129162559539.png)

## 1. 自顶而下(Top-down)模板套路

1. 首先访问根节点root，对其进行一些操作或返回某些值。
2. 将返回的值传递到其左右子树中。
3. 是另一种形式的**前序遍历**

#### 模板套路

```java
//1.访问根节点
if(root == null){
    ...
    //返回某值
    return;
}
//2.如果root是一个叶子节点，更新answer

//3.递归左右子树
left_answer = top_down(root.left, left_params);
right_answer = top_down(root.right, right_params);

//4.如果需要，返回ans
return answer;
    
```

相关练习：

1. [二叉树的最大深度](05.二叉树的最大深度.md)

![image-20201201222327924](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20201201222327924.png)

## 2. 自下而上(Bottom-up)模板套路



1. 首先计算左右子树的结果，通过**返回的结果**以及**当前节点**进行一些计算操作来得到答案。
2. 可以看做是一种**后序遍历**：左右根

#### 套路模板

```java

public boolean bottom_up(){
    //递归出口条件
    if(root == null){
        return;
    }
    //计算左右子树
    left_answer = bottom_up(root.left);
    right_answer = bottom_up(root.right);

    //利用左右子树的值，返回本节点的结果
    return operation(left_answer, right_answer);
}

```

相关练习：

[5.二叉树的最大深度](算法真题实践/05.二叉树的最大深度.md)

## 3. 技术总结：

* 递归算法的关键要明确函数的定义，相信这个定义，而不要跳进递归细节。
* 写二叉树的算法题，都是基于**递归框架**的，我们先要**搞清楚root节点它自己要做什么**，然后根据题目要求选择使用前序，中序，后续的递归框架。
* 二叉树题目的难点在于如何通过题目的要求**思考出每一个节点需要做什么**
* **把题目的要求细化，搞清楚根节点应该做什么，然后剩下的事情抛给前/中/后序的遍历框架就行了**
* 我们千万不要跳进递归的细节里，你的脑袋才能压几个栈呀

## 4.灵魂三问：

1. **这个函数是干嘛的**？
2. **这个函数参数中的变量是什么的是什么**？
3. **得到函数的递归结果，你应该干什么**？

[10.二叉树的最近公共祖先](3.2二叉树的进阶算法/10.二叉树的最近公共祖先)









