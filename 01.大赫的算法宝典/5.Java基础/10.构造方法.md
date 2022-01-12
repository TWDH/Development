# 构造方法

## 构造方法

* 概念：
  * 专门创建对象的方法，通过new关键字创建对象时，其实在调用构造方法
* 格式：
  * ```java
    public 类名称（参数类型 参数名称）{
        方法体
    }
    ```
* 注意事项
  1. 构造方法的名称必须和所在的类名称完全一样，就连大小写也要一样
  2. 构造方法不要写返回值类型，连void都不写
  3. 构造方法不能return一个具体的返回值
  4. 如果没有编写任何构造方法，那么编译器将会默认赠送一个构造方法，没有参数、方法体什么事情都不做。
     * `public Student() {}`
  5. 一旦编写了至少一个构造方法，那么编译器将不再赠送。
  6. 构造方法也是可以进行重载的。
     * 重载：方法名称相同，参数列表不同。
  7. ```java
     //Student类
     public class Student {
         // 成员变量
         private String name;
         private int age;

         // 无参数的构造方法
         public Student() {
             System.out.println("无参构造方法执行啦！");
         }

         // 全参数的构造方法
         public Student(String name, int age) {
             System.out.println("全参构造方法执行啦！");
             this.name = name;
             this.age = age;
         }

         // Getter Setter
         public void setName(String name) {
             this.name = name;
         }

         public String getName() {
             return name;
         }

         public void setAge(int age) {
             this.age = age;
         }

         public int getAge() {
             return age;
         }
     }

     //调用student
     public class Demo02Student {
         public static void main(String[] args) {
             Student stu1 = new Student(); // 无参构造
             System.out.println("============");

             Student stu2 = new Student("赵丽颖", 20); // 全参构造
             System.out.println("姓名：" + stu2.getName() + "，年龄：" + stu2.getAge());
             // 如果需要改变对象当中的成员变量数据内容，仍然还需要使用setXxx方法
             stu2.setAge(21); // 改变年龄
             System.out.println("姓名：" + stu2.getName() + "，年龄：" + stu2.getAge());
         }
     }
     ```

