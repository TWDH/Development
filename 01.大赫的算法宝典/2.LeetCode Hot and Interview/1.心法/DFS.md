# DFS

- **DFS 是否有返回值**：
  - 如果需要**遍历整颗树**，递归函数就**不能有返回值**。
  - 如果需要遍历**某一条固定路线**，递归函数就**一定要有返回值**！
- 数组记录 `visited` 速度更快（LeetCode 1306）
- 在 `backtracking` 时，进入下一层，是 `i + 1`，而**不是** `start + 1`
- [DFS LeetCode讲解](https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/)



# 回溯

- 步骤

  1. 路径：也就是已经做出的选择。
  2. 选择列表：也就是你当前可以做的选择。
  3. 结束条件：也就是到达决策树底层，无法再做选择的条件。

- ```java
  result = []
  def backtrack(路径, 选择列表):
      if 满足结束条件:
          result.add(路径)
          return
      
      for 选择 in 选择列表:
          做选择
          backtrack(路径, 选择列表)
          撤销选择
  ```

- 

​	

# 岛屿问题遍历框架

- **DFS/BFS 算法遍历二维数组**

- ```java
  // 二叉树遍历框架
  void traverse(TreeNode root) {
      traverse(root.left);
      traverse(root.right);
  }
  
  // 二维矩阵遍历框架
  void dfs(int[][] grid, int i, int j, boolean[] visited) {
      int m = grid.length, n = grid[0].length;
      if (i < 0 || j < 0 || i >= m || j >= n) {
          // 超出索引边界
          return;
      }
      if (visited[i][j]) {
          // 已遍历过 (i, j)
          return;
      }
      // 进入节点 (i, j)
      visited[i][j] = true;
      dfs(grid, i - 1, j); // 上
      dfs(grid, i + 1, j); // 下
      dfs(grid, i, j - 1); // 左
      dfs(grid, i, j + 1); // 右
  }
  ```

### 使用【方向数组】

- ```java
  // 方向数组，分别代表上、下、左、右
  int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
  
  void dfs(int[][] grid, int i, int j, boolean[] visited) {
      int m = grid.length, n = grid[0].length;
      if (i < 0 || j < 0 || i >= m || j >= n) {
          // 超出索引边界
          return;
      }
      if (visited[i][j]) {
          // 已遍历过 (i, j)
          return;
      }
  
      // 进入节点 (i, j)
      visited[i][j] = true;
      // 递归遍历上下左右的节点
      for (int[] d : dirs) {
          int next_i = i + d[0];
          int next_j = j + d[1];
          dfs(grid, next_i, next_j);
      }
      // 离开节点 (i, j)
  }
  ```


