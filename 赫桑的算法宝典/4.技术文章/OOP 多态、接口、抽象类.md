## 1. Object-Oriented Programming?

- **Inheritance (继承)**: 
  - Inheritance is a process where one class **acquires the properties** of another.
- **Polymorphism (多态)**: 
  - Polymorphism is the ability of a **variable**, **function** or **object** to take multiple forms.
  - `Compile time` polymorphism
    - method overloading
  - `Run time` polymorphism
    - done using **inheritance** and **interface**.
- **Encapsulation (封装)**: 
  - Encapsulation in Java is a mechanism of **wrapping up the data** and code together as a single unit.
  - **Data is hidden from the outer world** 将数据隐藏
  - Accessed only via current **class methods (getter, setter)** 只能用方法操作变量
  - **Abstraction (抽象)**: 
    - Abstraction is the methodology of **hiding the implementation details** from the user and only providing the functionality to the users. 

## 2. what is interface?

- A collection of **abstract method** and **static constant**
- method is **public** and **abstract**
- **No constructor**
- **No method** implementation
- Instance variable is **public static final**, has to initialize 
- Function don’t have to accumulate, because not good for maintenance

## Polymorphism

Polymorphism is briefly described as “**one interface, many implementations**”. Polymorphism is a characteristic of being able to assign a different meaning or usage to something in different contexts – specifically, to allow an entity such as a variable, a function, or an object to have **more than one form**. There are two types of polymorphism:

![img](https://d1jnx9ba8s6j9r.cloudfront.net/blog/wp-content/uploads/2017/04/Polymorphism-483x300.png)

1. `Compile time` polymorphism
   - method **overloading**
2. `Run time` polymorphism
   - done using **inheritance** and **interface**.

## 3. "final" mean in Java?

Non access modifier, used in different context

- **final variable**
  - **value cannot be changed**. only class constructor can assign to it 
  - **引用**不能改变，reference cannot be changed, 只能指向初始对象。（对象本身可以改变）
- **final method**
  - **can’t be overridden** by the **inheriting class**
  - subclass **can use** the method
- **final class**
  - **can’t be extended** by any **subclass**,  **can extend other class**. 
  - all method cannot be overridden
- final parameter
  - cannot be changed within the method

## 4. “static” mean in Java?

- **Static Instance Variable**
  - Global variable 全局变量，不会根据对象而修改
  - 属于 Class， 在内存中**只有一个内存地址**。只要类被加载，静态变量就被分配空间
  - `class.staticVariable` or `instance.staticVariable`
- **Static Method**
  - **Class Method**, 属于类方法，不是对象方法，不需要创建 Instance Variable 就可以使用
  - **Cannot** invoke **non-static method**，对象还没创建 or 不能确定是哪个对象的方法
  - **Singleton Pattern**, 创建对象的方法设为 static
    - 无法创建对象（构造器私有）
    - 必须用 `class.method` 创建对象
- **Static Code Block**
  - JVM load static code block when loading Class
  - 初始化静态变量
  - 执行一次
- **Static Inner Class**
  - not rely on outer instance initialization
  - no same name as outer class
  - 不能访问外部类的成员变量，但可以访问外部类的静态变量 or 方法

## 5. Heap and Stack

![img](https://snailclimb.gitee.io/javaguide/docs/java/jvm/pictures/java内存区域/Java运行时数据区域JDK1.8.png)

- 栈：**基本数据类型** + **对象引用变量**
- 堆：**引用类型的变量** (new 出来的对象)
- 线程间共享堆内存

| **Features** |                          **Stack**                           |                           **Heap**                           |
| :----------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   *Memory*   | Stack memory is used only by **one thread of execution**. <br />(当前线程可以使用) | Heap memory is used by **all the parts of the application**. (全部应用可以使用) |
|   *Access*   | Stack memory **can’t** be accessed by **other threads.** <br />(其他线程不可使用) |   Objects stored in the heap are **globally accessible.**    |
|  *Lifetime*  |   Exists until the **end of** execution of the **thread**.   | Heap memory lives from the start till the end of **application execution**. |
|   *Usage*    | Stack memory only contains **local primitive** and **reference variables** （指向一个object的地址）to objects in heap space. | Whenever an **object is created**, it’s always **stored** in the **Heap** space. |

## 6.overloading (重载) and overriding (重写)?

- **overloading** (重载)
  - same name 方法名一样
  - different # of parameters 变量数不同
  - different types 变量类型不同
  - different order 变量顺序不同
  - **compile-time polymorphism 编译时多态**
- **overriding** (重写)
  - subclass exactly same method 子类完全相同的方法
  - “Change” existing behavior 改变父类中的方法
  - **run-time polymorphism 运行时多态**  +  **继承 / Interface**

## Abstract class and interface

- 相同点
  - 不能被实例化
  - 只有实现接口，或实现抽象类中的方法，才可以被实例化
- 不同点
  - 接口：
    - 方法不能在接口中实现，只能定义方法 (Java8前)
    - 类可以实现多个接口
    - 强调特定功能，has a xxx
    - 功能不需要积累
    - 成员变量：public static final，静态 + 不可修改
    - 成员方法：public abstract
  - abstract类：
    - 抽象类可以定义方法实现
    - 类只能继承一个抽象类
    - 强调所属关系，is a xxx 
    - 功能需要积累

## 7. Error and Exception

- **Error**
  - An error is an **irrecoverable** condition occurring at runtime. 运行时问题，无法解决
  - OutOfMemory
- **Exception**
  - bad input 不正确认为失误，可以被解决
  - **Checked Exception** (Compile-time Exception)
    - Checked exceptions are **checked at compile-time**. 编译时检测
    - classes that **extend `Throwable` class**
    - 除了  `RuntimeException` and `Error` 都是checked
    - 必须处理（try ...catch）的异常
    - Example: `IOException`, `SQLException` etc.
  - **Unchecked Exception** (Runtime Exception)
    - classes that **extend `RuntimeException`** 
    - Example: `ArithmeticException`, `NullPointerException` etc.

## 8. Processes and Threads?

- **Process (进程)**
  - Instance of a Program 程序实例
  - 通讯：**进程间** 通信 ( inter-process communication) 与 同级进程通信
  - 其他进程：不会影响子进程，只能控制子进程
  - 控制：操作系统控制进程
- **Thread (线程)**
  - subset of the process 进程子集，程序执行最小单元
  - 通讯：线程间可直接互相通讯
  - 其他线程：main 线程会影响其他线程，进程可以很大的控制线程
  - 控制：程序员控制线程
  - 每个线程，**共享内存空间**
  - 状态：运行、就绪、挂起、结束

## 7. What are the properties of the transaction?

###  **ACID** properties. 

- ==Atomicity==: 
  - Ensures the completeness of all transactions performed. Checks whether every transaction is completed successfully or not. If not, then the transaction is aborted at the failure point and the **previous transaction is rolled back to its initial state** as changes are undone.
  - 事务是不可分割的整体，要么全执行，要么全不执行
- ==Consistency==: 
  - Ensures that all changes made through successful transactions are reflected properly on the database.
  - 一个事务执行前和执行后，数据库必须保持一致性状态
  - 最终一致性（银行案例）
- ==Isolation==: 
  - Ensures that all transactions are performed independently and changes made by one transaction are not reflected on others.
  - 2个事务不会互相影响
- ==Durability==: 
  - Ensures that the changes made in the database with committed transactions persist as it is even after a system failure.
  - 事物结束后，不会丢失
  - 事务没有提交，恢复到原状
  - 事务一旦提交(commit)，持久化到数据库

### 事务的隔离级别

- **脏读 (dirty read)**
  - 一个事务，读取了另一个事务，没有提交的数据。 (读了别人的)
- **不可重复读 (unrepeatable read)**
  - 一个事务，读取同一行数据，多次读取结果不同（事务未提交 -> 事务已经提交，不一定是错误）
- **幻读（虚读）(phantom read)**
  - 在一个事务内，读取到了别的事务插入的数据，导致前后读取不一致。（读自己的，别人插入我）