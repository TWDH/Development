# 继承性

## 概念：

* 继承是多态的前提，没有继承就没有多态
* 解决问题：共性抽取

## 特点：

* 子类可以拥有父类的“内容”
* 子类还可以拥有自己专有的内容 
* **成员变量【重名】**，创建子类对象时，访问有两种方式：
  1. 直接通过子类对象访问成员变量： 等号左边是谁，就优先用谁，没有则向上找
     * ```java
       Zi zi = new Zi();
       zi.num; // 优先子类，200
       ```
  2. 间接通过成员方法访问成员变量： 该方法属于谁，就优先用谁，没有则向上找。 
* **重名的三种变量**（父类，子类，局部）
  * ```java
    局部变量：         直接写成员变量名
    本类的成员变量：    this.成员变量名
    父类的成员变量：    super.成员变量名
    ```
* **父类子类中方法重名**
  * 创建的对象是谁，就优先用谁，如果没有则向上找。 
* **Overide**
  1. 必须保证父子类之间方法的名称相同，参数列表也相同。
     * @Override：写在方法前面，用来检测是不是有效的正确覆盖重写。
     * 这个注解就算不写，只要满足要求，也是正确的方法覆盖重写。
  2. 子类方法的返回值必须【小于等于】父类方法的返回值范围。
     * 小扩展提示：java.lang.Object类是所有类的公共最高父类（祖宗类），java.lang.String就是Object的子类。
  3. 子类方法的权限必须【大于等于】父类方法的权限修饰符。
     * 小扩展提示：public &gt; protected &gt; \(default\) &gt; private
     * 备注：\(default\)不是关键字default，而是什么都不写，留空。 
* **继承的覆盖重写**
  * `super.父类方法();`
    * ```java
      @Override
      public void show() {
          super.show(); // 把父类的show方法拿过来重复利用
          // 自己子类再来添加更多内容
          System.out.println("显示姓名");
          System.out.println("显示头像");
          }
      }
      ```
* **父子类的构造方法**
  1. 子类构造方法当中有一个默认隐含的“super\(\)”调用，所以一定是先调用的父类构造，后执行的子类构造。
  2. 子类构造可以通过super关键字来调用父类重载构造。
     * `super(); //调用父类构造方法`
  3. super的父类构造调用，必须是子类构造方法的第一个语句。不能一个子类构造调用多次super构造。
  4. 总结： 子类必须调用父类构造方法，不写则赠送super\(\)；写了则用写的指定的super调用，super只能有一个，还必须是第一个。 
* **super关键字**

  1. 在子类的成员方法中，访问父类的成员变量
     * `super.num; // 父类中的num` 
  2. 在子类的成员方法中，访问父类的成员方法。
     * `super.method(); // 访问父类中的method` 
  3. 在子类的构造方法中，访问父类的构造方法。
     * `super();` 

* **this关键字**
  1. 在本类的成员方法中，访问本类的成员变量。
     * ```java
       public void showNum() {
           int num = 10;
           System.out.println(num); // 局部变量
           System.out.println(this.num); // 本类中的成员变量
           System.out.println(super.num); // 父类中的成员变量
       }
       ```
  2. 在本类的成员方法中，访问本类的另一个成员方法。
     * ```java
       public void methodA() {
           System.out.println("AAA");
       }

       public void methodB() {
           this.methodA();
           System.out.println("BBB");
       }
       ```
  3. 在本类的构造方法中，访问本类的另一个构造方法。
     * ```java
       public Zi() {
           this(123); // 本类的无参构造，调用本类的有参构造
       }

       public Zi(int n) {
           this(1, 2);
       }
       ```

### 重要特点：

1. Java语言是【单继承】：一个类只有一个直接父类
2. Java语言可以【多级继承】
3. 
## 定义：

* 父类
  * ```java
    public class 父类名称 {
        // ...
    }
    ```
* 子类
  * ```java
    public class 子类名称 extends 父类名称 {
        // ...
    }
    ```



