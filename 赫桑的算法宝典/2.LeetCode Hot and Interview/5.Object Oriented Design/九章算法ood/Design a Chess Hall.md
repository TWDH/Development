## 设计Chess（棋牌大厅）

![image-20220525190558798](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525190558798.png)



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
  - Init: Join game，Set up game, 
  - **Make move**
  - **Change player**
  - Check for win, Increase steps, Calculate points