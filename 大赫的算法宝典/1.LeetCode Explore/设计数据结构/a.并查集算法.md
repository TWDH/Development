# 01.并查集算法 Union Find

1. 动态连通性可以抽象成给一幅图连线，总共有 10 个节点，他们互不相连，分别用 0~9 标记。

   ![image-20210106092149424](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210106092149424.png)

2. 现在我们的 Union-Find 算法主要需要实现这两个 API：

   ```java
   class UF {
       /* 将 p 和 q 连接 */
       public void union(int p, int q);
       /* 判断 p 和 q 是否连通 */
       public boolean connected(int p, int q);
       /* 返回图中有多少个连通分量 */
       public int count();
   }
   ```

   1. 我们使用森林（若干棵树）来表示图的动态连通性，用数组来具体实现这个森林

      1. 比如说刚才那幅 10 个节点的图，一开始的时候没有相互连通

         ```java
         class UF {
             // 记录连通分量
             private int count;
             // 节点 x 的节点是 parent[x]
             private int[] parent;
         
             /* 构造函数，n 为图的节点总数 */
             public UF(int n) {
                 // 一开始互不连通
                 this.count = n;
                 // 父节点指针初始指向自己
                 parent = new int[n];
                 for (int i = 0; i < n; i++)
                     parent[i] = i;
             }
         
             /* 其他函数 */
         }
         ```

         ![image-20210106092505066](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210106092505066.png)

3. 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上：

   ```java
   public void union(int p, int q) {
       int rootP = find(p);
       int rootQ = find(q);
       if (rootP == rootQ)
           return;
       // 将两棵树合并为一棵
       parent[rootP] = rootQ;
       // parent[rootQ] = rootP 也一样
       count--; // 两个分量合二为一
   }
   
   /* 返回某个节点 x 的根节点 */
   private int find(int x) {
       // 根节点的 parent[x] == x
       while (parent[x] != x)
           x = parent[x];
       return x;
   }
   
   /* 返回当前的连通分量个数 */
   public int count() { 
       return count;
   }
   ```

   ![image-20210106092929991](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210106092929991.png)

   1. 这样，如果节点`p`和`q`连通的话，它们一定拥有相同的根节点：

      ```java
      public boolean connected(int p, int q) {
          int rootP = find(p);
          int rootQ = find(q);
          return rootP == rootQ;
      }
      ```

      ![image-20210106093045878](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210106093045878.png)

4. 平衡性优化

   1. 简单粗暴的把`p`所在的树接到`q`所在的树的根节点下面，那么这里就可能出现「头重脚轻」的不平衡状况

      ![image-20210106093217494](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210106093217494.png)

   2. **我们其实是希望，小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些**。解决方法是额外使用一个`size`数组，记录每棵树包含的节点数

      ```java
      class UF {
          private int count;
          private int[] parent;
          // 新增一个数组记录树的“重量”
          private int[] size;
      
          public UF(int n) {
              this.count = n;
              parent = new int[n];
              // 最初每棵树只有一个节点
              // 重量应该初始化 1
              size = new int[n];
              for (int i = 0; i < n; i++) {
                  parent[i] = i;
                  size[i] = 1;
              }
          }
          /* 其他函数 */
      }
      ```

   3. 比如说`size[3] = 5`表示，以节点`3`为根的那棵树，总共有`5`个节点。这样我们可以修改一下`union`方法

      ```java
      public void union(int p, int q) {
          int rootP = find(p);
          int rootQ = find(q);
          if (rootP == rootQ)
              return;
      
          // 小树接到大树下面，较平衡
          if (size[rootP] > size[rootQ]) {
              parent[rootQ] = rootP;
              size[rootP] += size[rootQ];
          } else {
              parent[rootP] = rootQ;
              size[rootQ] += size[rootP];
          }
          count--;
      }
      ```

   4. 这样，通过比较树的重量，就可以保证树的生长相对平衡，树的高度大致在`logN`这个数量级

5. 完整代码

```java
class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
```

































