# 数据类型转换

数据类型不一样时，将会发生数据类型转换

* 自动类型转换（隐式）
  1. 特点：代码不需要特殊处理，自动完成。
  2. 规则：数据范围从小到大
     * `long num1 = 100`   int---&gt;long（符合数据范围小到大）
     * `double num2 = 2.5F`  float ---&gt;double（符合数据范围小到大）
     * `float num3 = 30L` long---&gt;float（符合数据范围小到大） 
* 强制类型转换（显式）
  * 特点：代码需要处理，不能自动完成
  * 格式：范围小的类型 范围小的变量名 = （范围小的类型） 范围大的数据
    * `int num = (int) 100L;`
* 注意事项
  * 数据溢出
    * long强制转换为int类型
      * `int num2 = (int) 6000000000L; // 1705032704`
  * 精度损失
    * double ---&gt; int
      * `int num3 = (int) 3.99; // 3`  
  * byte/short/char
    * 可以发生数学运算，会首先提升为int类型；如加法“+”
    * `char zifu = 'A';`
    * `zifu + 1 / 66`
    * ```java
      // byte + byte ---> int + int ---> int
      byte num4 = 40;
      byte num5 = 50;
      int result1 = num4 + num5;
      ```
  * boolean
    * 不能发生数据类型转换



