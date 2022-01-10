### 心法

1. 创建队列，入队`offer`首个节点
2. `while` 开启 `BFS`，得到当前层的节点数 `size`
3. `for`循环加入 `neighbor` (层遍历)
4. `queue.poll()`获取当前节点
5. 向队列加入当前节点的`neighbor`

### # 技巧

* `queue.offer()` 和 `visited = ture` 必须同时出现

* 层数 `level++` 在 `while` 和 `for(size)` 之间

### 九章算法模板：

* ```java
  ReturnType bfs(Node startNode) {
      // BFS 必须要⽤队列 queue，别⽤栈 stack！
      Queue<Node> queue = new ArrayDeque<>();
      // hashmap 有两个作⽤，⼀个是记录⼀个点是否被丢进过队列了，避免重复访问
      // 另外⼀个是记录 startNode 到其他所有节点的最短距离
      // 如果只求连通性的话，可以换成 HashSet 就⾏
      // node 做 key 的时候⽐较的是内存地址
      Map<Node, Integer> distance = new HashMap<>();
      // 把起点放进队列和哈希表⾥，如果有多个起点，都放进去
      queue.offer(startNode);
      distance.put(startNode, 0); // or 1 if necessary
  
      // while 队列不空，不停的从队列⾥拿出⼀个点，拓展邻居节点放到队列中
      while (!queue.isEmpty()) {
          Node node = queue.poll();
          // 如果有明确的终点可以在这⾥加终点的判断
          if (node 是终点) {
          	break or return something;
          }
          for (Node neighbor : node.getNeighbors()) {
              if (distance.containsKey(neighbor)) {
              	continue;
          	}
              queue.offer(neighbor);
              distance.put(neighbor, distance.get(node) + 1);
          }
      }
      // 如果需要返回所有点离起点的距离，就 return hashmap
      return distance;
      // 如果需要返回所有连通的节点, 就 return HashMap ⾥的所有点
      return distance.keySet();
      // 如果需要返回离终点的最短距离
      return distance.get(endNode);
  }
  ```


### 二叉树经典层序遍历

- ```java
  class Solution {
      public List<List<Integer>> levelOrder(TreeNode root) {
          // edge condition
          if(root == null){
              return new ArrayList<>();
          }
  
          // result
          List<List<Integer>> result = new ArrayList<>();
  
          // Queue
          Queue<TreeNode> queue = new LinkedList<>();
          // 1. 入队首个节点
          queue.offer(root);
  
          // BFS
          while(!queue.isEmpty()){
              // 2. 得到当前层的节点数
              int size = queue.size();
  
              // 当前层 list
              List<Integer> list = new ArrayList<>();
  
              // 3. 循环加入 neighbor
              for(int i = 0; i < size; i++){
                  // 4. 获取当前节点
                  TreeNode node = queue.poll();
  
                  // 5. 加入当前节点的neighbor
                  if(node.left != null){
                      queue.offer(node.left);
                  }
  
                  if(node.right != null){
                      queue.offer(node.right);
                  }
  
                  // list 加入当前层节点
                  list.add(node.val);
              }
  
              result.add(list);
          }
          return result;
      }
  }
  ```

