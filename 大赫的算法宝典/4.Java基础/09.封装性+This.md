# 封装性 + This

## Java中的封装

* 封装是将一些细节信息隐藏起来，对于外界不可见
  * 方法是一种封装
  * private是一种封装

## private

* 问题描述：定义Person的年龄时，无法阻止不合理的数值被设置进来。
* 解决方案：用private关键字将需要保护的成员变量进行修饰。
  * 一旦使用了private进行修饰，那么本类当中仍然可以随意访问。 但是，超出了本类范围之外就不能再直接访问了。
  * 间接访问private成员变量，就是_定义一对儿Getter/Setter方法_
  * ```java
    // Person类
    public class Person {
        String name; // 姓名
        private int age; // 年龄

        public void show() {
            System.out.println("我叫：" + name + "，年龄：" + age);
        }

        // 这个成员方法，专门用于向age设置数据
        public void setAge(int num) {
            if (num < 100 && num >= 9) { // 如果是合理情况
                age = num;
            } else {
                System.out.println("数据不合理！");
            }
        }
        // 这个成员方法，专门私语获取age的数据
        public int getAge() {
            return age;
        }
    }
    ```

## This关键字

* 方法的局部变量和成员变量重名，“就近原则”，优先使用局部变量
* this代表【对象】
* 访问成员变量：`this.成员变量`
  * ```java
    //person类
    public class Person {
        String name; // 我自己的名字
        // 参数name是对方的名字
        // 成员变量name是自己的名字
        public void sayHello(String name) {
            System.out.println(name + "，你好。我是" + this.name);
            System.out.println(this); // 地址值
        }
    }

    //调用person
    public class Demo01Person {
        public static void main(String[] args) {
            Person person = new Person();
            // 设置我自己的名字
            person.name = "王健林";
            person.sayHello("王思聪");

            System.out.println(person); // 地址值
        }
    }
    ```

  * 通过“谁”调用方法，谁就是this
    * person调用sayHello\(\)方法，person就是this

