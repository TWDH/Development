## 设计Chess（棋牌大厅）

- Clarify : 玩家，规则，胜负，积分
- Core object: Hand, Board, Deck/Table, Suit, …
- Use cases: Initialization / Play / Checkout

![image-20220525190558798](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525190558798.png)

- joinGame()：分配当前玩家，红黑
- initializeBoard()：Piece（颜色 --- enum，角色 --- enum）
- makeMove()：piece（那个棋子，移动到哪一行那一列），那个玩家下棋 --- currentPlayer，valid？
  - boolean：判断是否合法（棋子 + 位置 = 规则走法）
- changePlayer()：改变currentPlayer
- checkCurrentPlayerWin()：是否胜利
- rewardCurrentPlayer()：添加 or 扣除 Player 的积分

棋牌大厅 = 一局游戏 + plus



- **Clarify:** 
  - *WHAT*
    - Chess hall?   Check game draw?
  - *HOW*
    - 棋盘大小变化
    - 想起走法规则？
    - 时间规则？
    - 胜负？（平局？）
      - 下的步数超过一定数量，判定平局（√）
      - 重复判定平局
      - 双方选手要求平局
- **Use cases:** 
  - Init: 
    - Join game
    - Set up game, 
  - Play：
    - **Make move**
    - **Change player**
  - Win/Lose check + Tie/Draw (平局)：
    - Check for win
    - Increase steps
    - Calculate points