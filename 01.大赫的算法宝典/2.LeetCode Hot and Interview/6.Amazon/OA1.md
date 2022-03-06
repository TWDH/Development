# Amazon

## 蠡口

- [blind 75](https://leetcode.com/discuss/general-discussion/460599/blind-75-leetcode-questions)
- [一亩三分地：面经集合](https://coda.io/d/Offer_dm5fzh3MwOp/OA1_suMAY#OA1_tua4n/r1)
- [[小土刀的面试刷题笔记](https://wdxtub.com/interview/index.html)](https://wdxtub.com/interview/14520609088903.html)
- 3
- 23
- 100
- 130
- 138
- 139
- 140
- 146
- 198
- 236
- 286
- 348
- 529
- 547
- 572
- 739
- 920
- 926
- 1004
- 1235
- 1846
- radix sort
- 让实现了一个infinite stack，可以O(1)实现push, pop, getmin等
- 设计德州扑克
- [Amazon | OA | Max Length of Valid Server Cluster](https://leetcode.com/discuss/interview-question/1636493/amazon&#8205;&#8204;&#8205;&#8204;&#8205;&#8204;&#8204;&#8204;&#8204;&#8205;&#8204;&#8205;&#8204;&#8204;&#8205;&#8205;&#8205;&#8204;&#8204;&#8204;-online-assessment-questions)
- [Amazon | Max Consecutive Ones](https://leetcode.com/discuss/interview-question/algorithms/125017/amazon-max-consecutive-ones/125014)
- [Amazon OA 2022](https://leetcode.com/discuss/interview-question/1735633/Amazon-OA-2022)
- [Amazon | OA | Max deviation among all substrings](https://leetcode.com/discuss/interview-question/1742621/Amazon-or-OA-or-Max-deviation-among-all-substrings)
- [Amazon OA | SDE Intern](https://leetcode.com/discuss/interview-question/1733741/Amazon-OA-or-SDE-Intern)

## 非蠡口

### 1. 灰度题

- ![image-20220305224118926](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220305224118926.png)

- ```python
  grid = [[0, 1, 0],
          [1, 0, 1], 
          [1, 0, 0]]
  
  def maxGrayScale(grid):
      # for each point, grayscale = num(0 in row) + num(0 in col) - (num(1 in row) + num(1 in col))
      # so, grayscale = (num(0 in row)-num(1 in row)) + (num(0 in col)-num(1 in col))
      # so we just need to find the max value for (num(0 in row)-num(1 in row)) and (num(0 in col)-num(1 in col)) respectively
      
      
      row, col = len(grid), len(grid[0])
      maxrow, maxcol = -col, -row
      
      # find maxrow
      for i in range(row):
          num_1 = sum(grid[i])
          num_0 = col - num_1
          diff = num_0 - num_1
          maxrow = max(maxrow, diff)
          
      # find maxcol
      for c in range(col):
          num_1 = 0
          for r in range(row):
              num_1 += grid[r][c]
          num_0 = row - num_1
          diff = num_0 - num_1
          maxcol = max(maxcol, diff)
      
      return maxrow+maxcol
      
  print maxGrayScale(grid)
  ```

###  2. shipment imbalance

- ```python
  # basically same with leetcode 2104
  from typing import List
  
  def sumSubarray(weights: List[int], min: bool) -> int:
      stack = []
      sum = 0
      curSum = 0
      for curValue in weights:
          curCount = 1
          while stack:
              if min and stack[-1][0] < curValue:
                  break
              elif not min and stack[-1][0] > curValue:
                  break
              value, count = stack.pop()
              curCount += count
              curSum -= value * count
  
          stack.append((curValue, curCount))
          curSum += curValue * curCount
          sum += curSum
      return sum
  
  
  def imbalance(weights: List[int]) -> int:
      return sumSubarray(weights, False) - sumSubarray(weights, True)
  
  if __name__ == '__main__':
      weights = [int(x) for x in input().split()]
      res = imbalance(weights)
      print(res)
  ```


### 3. 一滴血

- ```
  findMinHealth, 一个游戏，输入一个数列代表每关要掉的血🩸，还有一个值表示一个只能用一次的可以挡最高伤害的盾，要求过完全部关后还要留一滴血。求游戏开始前需要的最少血。
  
  ode question 1
  Amazon Prime Games is designing a game. The player needs to pass n rounds sequentially in this game. Rules of play are as follows;
  The player losses power [ i ] health to complete round i;
  The player's health must be greater than 0 at all times;
  The player can choose to use armor in any one round. The armor will prevent damage of min (armor, power [ i ] );
  Determine the minimum starting health for a player to win the game.
  Example
  power= [1, 2, 6, 7]
  armor= 5
  Give the player 12 units of health at the beginning of the game. One of the optimal strategies is to use the armor in the third round and only lose 1 unit instead of 6. The health of the player after each round is:
  Round, health
  0. 12
  1. 12 - power[0] = 12 - 1 = 11
  ‍‌‍‌‍‌‌‌‌‍‌‍‌‌‍‍‍‌‌‌
  2. 11 - power[1] = 11 - 2 = 9
  3. 9- power[2] + armor = 9 - 6 + 5 = 8
  4, 8 - power[3) = 8 - 7 = 1
  
  
  sum（power）- min（max（power），armor）+ 1
  ```

### 4. 括号

- [Amazon Online Assessment Question](https://leetcode.com/discuss/interview-question/1332412/amazon-online-assessment-question)

- ```
  There is a string with the charcater [,(,),],? find the number of possible ways to divide the string into two substring (Continuoes) such that number of open and closing bracket should be same in both substring with same type, you can use ? as a wild card to satisfy either opening or closing bracket of any type.
  
  eg:- ][?)?[
  
  there is two ways to splity this string
  1st way :-
  1st string :-][() replace que mark with round open bracket
  2nd string:-][ replace que mark with closing square bracket
  2nd way :-
  1st string :-][ replace que mark with round open bracket
  2nd string:-()][ replace que mark with round open bracket,replace que mark with closing square bracket
  ```

- ```java
  public class AmazonOAClosingBrackets {
  
      public static void main(String[] args) {
          String inputStr = "[()]??";
  
          AmazonOAClosingBrackets closingBrackets = new AmazonOAClosingBrackets();
          int count = closingBrackets.CountClosingBrackets(inputStr); 
  
          log("Balanced combinations : " + count);
      }
  
      public int CountClosingBrackets(String inputStr) {
         	Brackets finalBrackets= new Brackets();
         	finalBrackets.processChar(inputStr);
  
         	log("Final Brackets : " + finalBrackets); 
         	log("Substring Brackets : "); 
  
         	Brackets incrementalBracketsLeft = new Brackets();
         	int count = 0;
  
         	for (int i = 0; i < inputStr.length(); i++) {
         		incrementalBracketsLeft.processChar(inputStr.charAt(i)); 
         		log("i = " + i);
  			log("Sub left " + inputStr.substring(0, i + 1));
  			log(incrementalBracketsLeft);
  
  			Brackets diffBrackets = diff(finalBrackets, incrementalBracketsLeft);
  			log("Sub right " + inputStr.substring(i + 1));
  			log(diffBrackets);
  
  			if (incrementalBracketsLeft.isBalanced() && diffBrackets.isBalanced()) {
  				log("is balanced." ); 
  				count++;
  			} else {
  				log("Not balanced"); 
  			}
         	}
  
         	return count;
      }
  
  
      private class Brackets {
      	int sqClosing, sqOpening, rbClosing, rbOpening, wildCard; 
  
      	public Brackets() {
      		sqClosing = sqOpening = rbClosing = rbOpening = wildCard = 0;
      	}
  
      	public Brackets(int sqOpening, int sqClosing, int rbOpening, int rbClosing, int wildCard) {
      		this.sqOpening = sqOpening; 
      		this.sqClosing = sqClosing; 
      		this.rbOpening = rbOpening;
      		this.rbClosing = rbClosing; 
      		this.wildCard = wildCard;
      	}
  
      	public void processChar(String s) {
      		for (int i = 0; i < s.length(); i++) {
         			processChar(s.charAt(i));
         		}
      	}
  
      	public void processChar(char c) {
      		switch (c){ 
      			case '[' : 
      				sqOpening++;
      				break; 
  
      			case '(' : 
      				rbOpening++;
      				break; 
  
      			case ')' : 
      				rbClosing++;
      				break; 
  
      			case ']' : 
      				sqClosing++; 
      				break; 
  
      			case '?' :
      				wildCard++;
      				break; 
      			default : 
      				log("Invalid char encoutered " + c); 
  
      		}
      	}
  
      	public boolean isBalanced() {
      		int bracketDiff = Math.abs(sqOpening - sqClosing) + Math.abs(rbOpening - rbClosing); 
      		if (bracketDiff == 0 && wildCard%2 == 0) {
      			return true;
      		}
      		return bracketDiff - wildCard == 0;
  
      	}
  
      	@Override public String toString() {
      		StringBuilder sb = new StringBuilder(); 
      		sb.append("sq: " + sqOpening + ", " + sqClosing + "\n");
      		sb.append("rb: " + rbOpening + ", " + rbClosing + "\n");
      		sb.append("wildCard: " + wildCard);
      		return sb.toString(); 
  
      	}
  
      }
  
      public static Brackets diff(Brackets left, Brackets right) {
      		return new AmazonOAClosingBrackets().new Brackets(left.sqOpening - right.sqOpening, 
      			left.sqClosing - right.sqClosing, 
      			left.rbOpening - right.rbOpening, 
      			left.rbClosing - right.rbClosing, 
      			left.wildCard - right.wildCard); 
  
      }
  
      public static void log(Object s) {
      	System.out.println(s.toString());
      }
  }
  ```

### 5. Find K maximum Priority

- [Find K maximum Priority](https://leetcode.com/discuss/interview-question/1625460/amazon-oa-find-k-maximum-priority)

- ![image-20220305231405164](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220305231405164.png)

- ```
  A combo is defined as a subset of the given n terms. The total popularity is the sum of the individual items of the combo. design an algorithmn that can find the k combos with the highest popularity.
  two combos are considered different if they have different subset of items. return the array of k integers where the ith term denotes the popularity of ith best combo. Combos should be returned arranged best to worst.
  
  Example
  n = 3
  array = [3,5,-2]
  k = 3
  All possible populatrity of combos are 0,3,5,-2,8,3,1,6 .
  The best 3 are 8,6,5.
  hence , answer is [8,6,5].
  
  Constraints -
  1 <= n <= 10^5
  -10^9 <= array[i] <= 10^9
  1 <= k <= min(2000,2^n)
  
  Can anybody explain how to approach this for the given constraints?
  Guys - I was not able to do it during test , any help would be appritiated
  ```

- ```java
  public int[] topKSum(int[] nums, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>();
  
      subsetKSum(pq, k, 0, nums, 0);
  
      int[] result = new int[k];
      for (int i = 0; i < k; i++) {
          result[i] = pq.poll();
      }
      return result;
  }
  
  private void subsetKSum(PriorityQueue<Integer> result, int k, int sum, int[] nums, int current) {
  
  
      for (int i = current; i < nums.length; i++) {
          int nextSum = sum + nums[i];
          result.add(nextSum);
          if (result.size() > k) {
              result.poll();
          }
          subsetKSum(result, k, nextSum, nums, i +1);
  
      }
  }
  ```

### 6. Data centers

- [data centers](https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=856589&ctid=234484)

- ```
  AWS has several data centers which have multiple processors that perform computations. In one such data center, these processors are placed in a sequence with their IDs denoted by 1, 2, ...., n. Each Processor consumes a certain amount of power to boot up, denoted by booting Power[i].  After booting, a processor uses processing Power[i] to run the processes.
  For maximum utilization, the data center wishes to group these processors into clusters. Clusters can only be formed of processors located adjacent to each other. For example, processors 2, 3, 4, 5 can form a cluster, but 1, 3, 4 can not.
  The net power consumption of a cluster of K processors (i, j+1, ...., j+k-1) is defined as : max booting Power[i], booting Power[i+1], ...., booting power[i+k-1]
  return the max cluster per net power consumption.
  ```

### 7. SearchWord

- ```
  一个searchWord和一个resultWord，问最少给searchWord末尾加几个字母，可以让resultWord成为searchWord的subsequence。例子是：searchWord=“armaze”，resltWord=”amazon”，返回2。用双指针做就可以了
  ```

### 8. Shipment

- ![img](https://oss.1point3acres.cn/forum/202202/25/62156hedsxwfqkasmvxvd.jpg)

- ```java
  public class AmazonGetShipment {
      public static void main(String[] args) {
          int[] parcels = new int[]{2, 3, 6, 10, 11};
          int k = 9;
  
          int result = getShipment(parcels, k);
  
          System.out.println(result);
      }
  
      private static int getShipment(int[] parcels, int k) {
  
          Set<Integer> set = new HashSet<>();
  
          for (int parcel : parcels) {
              set.add(parcel);
          }
  
          int cost = 1;
          int sum = 0;
  
          while(set.size() < k){
              if(set.contains(cost)){
                  cost++;
                  continue;
              }
  
              sum += cost;
              set.add(cost);
              cost++;
          }
          return sum;
      }
  }
  ```

### 九宫格

- ![img](https://codaio.imgix.net/docs/m5fzh3MwOp/blobs/bl-EehqcEH0aS/3b924c8d5fc1bab2f25746869b896b533e8e47e9af8cdde979027ee256481ba05a665755c8ef7e651caca522c0acb749bb8b8a8bab1033a09fed5b7ed253dfd2deaa125fbba25c04662f4024a94db81e55573a91cd32daf6fb28b27d5e90e5b71c5d5168?auto=format%2Ccompress&fit=max)![img](https://codaio.imgix.net/docs/m5fzh3MwOp/blobs/bl-YPa9AxQ63X/949385c3717bf97e946f1834d7672c209ff15ce41f68cc85c3b77cd23e74ec8d5199ace38423e56a13eadcf48066a2872268cacce72f4c7a88fa0c636eab5f736921a95654aeed0cac42f0b3be21e8a560e31936245b67fad494c266c8cd9b0b1233e8ff?auto=format%2Ccompress&fit=max)![img](https://codaio.imgix.net/docs/m5fzh3MwOp/blobs/bl-bKmnZVVzqU/f53fb6eac14b67f418796c457468ca76dc04821f9a8e1d7f7b1d902fa37ec3c47fa6bb92c3bc76914d0370f06ce6e8ee6e6dd8a26daae587bbd825c1a08a5412c3b4c6c33effd052fb98147c2177d9c39cb6f24c92d43a5b38e1e7139f5997fb99344b16?auto=format%2Ccompress&fit=max)

- ```java
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.List;
  
  public class AmazonKeyPad {
      public static void main(String[] args) {
          String text = "abacadefghibj";
          int res = getMinClick(text);
          System.out.println(res);
      }
  
      /**
       * a: 3
       * b: 2
       * c: 1
       * d: 1
       * e: 1
       * f: 1
       * g: 1
       * h: 1
       * i: 1
       * j: 1
       */
      private static int getMinClick(String text) {
          int[] count = new int[26];
  
          // 每个字母的出现次数
          for(int i = 0; i < text.length(); i++){
              char ch = text.charAt(i);
              count[ch - 'a']++;
          }
  
          // 将其放入 list 并从大到小排列
          List<Integer> list = new ArrayList<>();
          for (int n : count) {
              list.add(n);
          }
          Collections.sort(list, (o1, o2) -> {
              return o2 - o1;
          });
  
          // 出现次数多的放在九宫格的第一个数字，以此类推；分别记录 click 1次、2次和3次的
          int minClick = 0;
  
          for(int i = 0; i < list.size(); i++){
              if(i < 9){
                  minClick += list.get(i);
              }
              else if(i < 18){
                  minClick += list.get(i) * 2;
              }
              else{
                  minClick += list.get(i) * 3;
              }
          }
  
          return minClick;
      }
  }
  
  ```

- 

## 图片

- ![img](https://oss.1point3acres.cn/forum/202202/25/62152fcz899mkwxv3sp0f.jpg)![img](https://oss.1point3acres.cn/forum/202202/25/62154af8f53o1cdwqjysn.jpg)