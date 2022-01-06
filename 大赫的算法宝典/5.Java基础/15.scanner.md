# Scanner

## Scanner

* 功能：实现键盘输入数据，到程序当中 
* 使用步骤
  1. 导包
     * java.lang包下内容不需要导包，其他包需要import
  2. 创建
     * 类名称 对象名 = new 类名称\(\);
       * `Scanner sc = new Scanner(System.in); //从键盘输入`
  3. 使用
     * 对象名.成员方法名\(\);
       * `获取int：int num = sc.nextInt();`
       * `获取字符串：String str = sc.next();`
     * ```java
       public static void main(String[] args) {
           // 2. 创建
           // 备注：System.in代表从键盘进行输入
           Scanner sc = new Scanner(System.in);
    
           // 3. 获取键盘输入的int数字
           int num = sc.nextInt();
           System.out.println("输入的int数字是：" + num);
    
           // 4. 获取键盘输入的字符串
           String str = sc.next();
           System.out.println("输入的字符串是：" + str);
       }
       ```



