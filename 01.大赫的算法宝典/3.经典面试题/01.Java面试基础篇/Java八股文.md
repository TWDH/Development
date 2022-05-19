- [Java八股文-详细版](https://juejin.cn/post/6995726543745974279)

# JAVA EE

## **Access Modifiers** (访问修饰符)

1. private / protected / public / default

2. | **Modifier**                     | **Default** | **Private** | **Protected** | **Public** |
   | -------------------------------- | ----------- | ----------- | ------------- | ---------- |
   | *Same class*                     | YES         | YES         | YES           | YES        |
   | *Same Package subclass*          | YES         | NO          | YES           | YES        |
   | *Same Package non-subclass*      | YES         | NO          | YES           | YES        |
   | *Different package subclass*     | NO          | NO          | YES           | YES        |
   | *Different package non-subclass* | NO          | NO          | NO            | YES        |




## Final

1. final variable
   - 值不能被改变
2. final method
   - 不能被重写 （overridden）
3. final class
   - 不能被继承（extend）



## **OOP 要素**

1. **Inheritance (继承)**
2. **Encapsulation (封装)**
3. **Polymorphism (多态)**
   - one interface, many implementations
   - Compile Time (编译时多态)
     - overloading（重载 - 方法名相同，参数不同）
   - Run Time（运行时多态）
     - inheritance（继承 - 方法名相同，覆盖父类）
     - interface（接口）与  Abstraction（抽象）
4. **Abstraction (抽象)**
   - 隐藏实现细节，仅提供功能接口



## 抽象类和接口

1. 抽象（抽象类 和 接口）
   - hiding the implementation details
   - 抽象类：**对类本质的抽象**， `is a` 关系（BMW is a car）；包含且实现 子类通用特性，子类存在差异化的特性抽象，交给子类实现
   - 接口：**对行为的抽象**， `like a` 关系（Bird like a Aircraft 可以飞），本质上 `is a bird`；
     - 定义行为，实现类 *做* 什么；实现类主题是谁，如何实现，接口不关心
2. 抽象类和接口 - 区别
   - **接口** 是公开的，不能有私有的方法或变量，接口中的所有方法都 `没有方法体`，通过关键字 `interface` 实现。
   - **抽象类** 是可以有私有方法或私有变量的，通过把类或者类中的方法声明为`abstract`来表示一个类是抽象类，被声明为抽象的方法 `不能包含方法体`。
3. 抽象类和接口 - 相同点
   - 都不能被实例化
   - 接口的实现类或抽象类的子类都只有 *实现了接口或抽象类中的方法后才能实例化*。
4. 抽象类和接口 - 不同点
   - 接口只有定义，不能有方法的实现，java 1.8中可以定义 `default` 方法体，而抽象类可以有定义与实现，方法可在抽象类中实现。
   - 实现接口的关键字为 `implements`，继承抽象类的关键字为 `extends`。**一个类可以实现多个接口**，但**一个类只能继承一个抽象类**。所以，使用接口可以间接地实现多重继承。
   - 接口强调 **特定功能** 的实现，而抽象类强调 **所属关系**。
   - **接口**成员变量默认为 `public static final`，必须赋初值，不能被修改；其所有的成员方法都是 `public`、`abstract` 的。抽象类中成员变量默认 `default`，可在子类中被重新定义，也可被重新赋值；
   - **抽象**方法被 `abstract` 修饰，不能被`private`、`static`、`synchronized` 和 `native` 等修饰，必须以分号结尾，不带花括号。
   - 接口被用于常用的功能，便于日后维护和添加删除，而**抽象类更倾向于充当公共类的角色**，不适用于日后重新对里面的代码修改。**功能需要累积** 时用抽象类，不需要累积时用接口。



## Exception

- Checked Exception
  - Checked exceptions are **checked at compile-time**.
    - **编译器**要检查这类异常
    - 必须处理（try ...catch）的异常
  - Example: `IOException`, `SQLException` etc.
- Unchecked Exception （Runtime Exception）
  - The classes that **extend `RuntimeException`** are known as unchecked exceptions. 
  - Unchecked exceptions are **not checked at compile-time**.
  - Example: `ArithmeticException`, `NullPointerException` etc.



## Processes (进程) and Threads (线程) 

|                   | **Process**                                                  | **Thread**                                                   |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Definition**    | An executing **instance of a program** is called a process.  | A thread is a **subset of the process**.                     |
| **Communication** | Processes must use inter-process communication to communicate with sibling processes. | Threads can directly communicate with other threads of its process. |
| **Control**       | Processes can only exercise control over child processes.    | Threads can exercise considerable control over threads of the same process. |
| **Changes**       | Any change in the parent process does not affect child processes. | Any change in the main thread may affect the behavior of the other threads of the process. |
| **Memory**        | Run in separate memory spaces.                               | Run in **shared memory spaces**.                             |
| **Controlled by** | Process is controlled by the operating system.               | Threads are controlled by programmer in a program.           |
| **Dependence**    | Processes are independent.                                   | Threads are dependent.                                       |

### java多线程之启动线程的三种方式

- 继承Thread类

  - ```java
    // Thread1类(线程类):
    public class Thread1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + "执行" + i);
            }
        }
    }
    
    // Mian类，测试类：
    public class Main {
        public static void main(String[] args) {
            new Thread1().start();
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + "执行" + i);
            }
        }
    }
    ```

- 实现Runnable接口

  - ```java
    // Thread2类（线程类）：
    public class Thread2 implements Runnable{
    	public void run() {
    		for (int i = 0; i < 50; i++) {
    			System.out.println(Thread.currentThread().getName() + "执行" + i);
    		}
    	}
    }
    
    // Main类：
    public class Main {
    	public static void main(String[] args) {
    		new Thread(new Thread2()).start();
    		for (int i = 0; i < 50; i++) {
    			System.out.println(Thread.currentThread().getName() + "执行" + i);
    		}
    	}
    }
    ```

- 实现 callable 接口

# Spring



## Spring

- 什么是Spring?
  - Spring是一个轻量级的IoC和AOP容器框架
- 什么是MVC模式?
  - 设计模式，是spring在原有基础上，又提供了web应用的MVC模块，
  - ![image-20220430121024798](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220430121024798.png)
- SpringMVC常用的注解有哪些？
  - @RequestMapping：用于处理请求 url 映射的注解，可用于类或方法上。用于类上，则表示类中
    的所有响应请求的方法都是以该地址作为父路径。
  - @RequestBody：注解实现接收http请求的json数据，将json转换为java对象。
  - @ResponseBody：注解实现将conreoller方法返回对象转化为json对象响应给客户。



## IOC

- IOC就是控制反转，是指创建对象的控制权的转移
  - 以前创建对象的主动权和时机是由自己把控的，而现在这种权力转移到Spring容器中，并由容器根据配置文件去创建实例和管理各个实例之间的依赖关系。
  - 应用程序在运行时依赖IoC容器来 *动态注入对象需要的外部资源*
- IOC让对象的创建不用去new了，可以 *由spring自动生产*，使用java的反射机制，根据配置文件在运行时动态的去创建对象以及管理对象，并调用对象的方法的
- Spring的IOC有三种注入方式 ：构造器注入、setter方法注入、根据注解注入

> *IoC让相互协作的组件保持松散的耦合，而AOP编程允许你把遍布于应用各层的功能分离出来形成可重用的功能组件。*



## AOP

- Aspect-Oriented Programming，能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来
  - 减少系统的重复代码
  - 降低模块间的耦合度
  - 可扩展性和可维护性
- 基于动态代理



## Spring依赖注入有哪几种方式

- [Spring依赖注入有哪几种方式](https://www.zhihu.com/question/452470872)
- Constructor-based
  - [Spring Boot简明教程--依赖注入的三种方式](https://cloud.tencent.com/developer/article/1777778)
  - ![image-20220430124836919](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220430124836919.png)
- Field
  - 接口注入 依赖类必须要实现指定的接口，然后实现该接口中的一个函数，该函数就是用于依赖注入。该函数的参数就是要注入的对象。
  - 优点 接口注入中，接口的名字、函数的名字都不重要，只要保证函数的参数是要注入的对象类型即可。
  - 缺点： 侵入行太强，不建议使用。
- ![image-20220429225558031](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220429225558031.png)
- ![image-20220429230709603](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220429230709603.png)

# 数据库

## 事务的隔离级别

- [MySQL事务之不可重复读问题](https://blog.csdn.net/sun8112133/article/details/89739475)
- 未提交读(Read Uncommitted)：允许脏读，一个事务可以读取另一个未提交事务的数据。最低级别，它存在4个常见问题（[脏读](https://so.csdn.net/so/search?q=脏读&spm=1001.2101.3001.7020)、不可重复读、幻读、丢失更新）
- 提交读(Read Committed)：只能读取到已经提交的数据。一个事务要等另一个事务提交后才能读取数据。 它解决了脏读问题，存在3个常见问题（不可重复读、幻读、丢失更新）
- 可重复读(Repeated Read)：开始读取数据（事务开启）时，不再允许修改操作 。它解决了脏读和不可重复读，还存在2个常见问题（幻读、丢失更新）
- 可串行化(Serializable)：完全串行化的读，每次读都需要获得表级共享锁，读写相互都会阻塞。将每个事务按一定的顺序去执行，它将隔离问题全部解决，但是这种事务隔离级别效率低下，比较耗数据库性能，一般不使用
- ![image-20220430130914236](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220430130914236.png)
- **脏读 (dirty read)**
  - 一个事务，读取了另一个事务，没有提交的数据。 (读了别人的)
- **不可重复读 (unrepeatable read)**
  - 就是一个事务读到另一个事务修改后并提交的数据（update）。在同一个事务中，对于同一组数据读取到的结果不一致。比如，事务B 在 事务A **提交前**读到的结果，和在 事务A **提交后**读到的结果可能不同。不可重复读出现的原因就是由于事务并发修改记录而导致的。
- **幻读（虚读）(phantom read)**
  - 在一个事务内，读取到了别的事务插入的数据，导致前后读取不一致。（读自己的，别人插入我）





## 数据库的三范式

- 第一范式：列不可再分 
- 第二范式：行可以唯一区分，主键约束 
- 第三范式：表的非主属性不能依赖与其他表的非主属性 
  - ![image-20210710095437357](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210710095437357.png)
- 外键约束 且三大范式是一级一级依赖的，



## 事务的特性

- 数据库事务特性：原子性(Atomic)、一致性(Consistency)、隔离性(Isolation)、持久性
  (Durabiliy)。简称ACID
- 原子性：组成一个事务的多个数据库操作是一个不可分割的原子单元，只有所有操作都成功，
  整个事务才会提交。任何一个操作失败，已经执行的任何操作都必须撤销，让数据库返回初始
  状态。
- 一致性：事务操作成功后，数据库所处的状态和它的业务规则是一致的。即数据不会被破坏。
  如A转账100元给B，不管操作是否成功，A和B的账户总额是不变的。
- 隔离性：在并发数据操作时，不同的事务拥有各自的数据空间，它们的操作不会对彼此产生干
  扰
- 持久性：一旦事务提交成功，事务中的所有操作都必须持久化到数据库中。



# Redis

## 什么是 Redis？

- Redis 是一个开源（BSD 许可）、基于内存、支持多种数据结构的存储系统，可以作为数据库、缓存和消息中间件。它支持的数据结构有字符串（strings）、哈希（hashes）、列表（lists）、集合（sets）、有序集合（sorted sets）等，除此之外还支持 bitmaps、hyperloglogs 和地理空间（geospatial ）索引半径查询等功能
- 它内置了复制（Replication）、LUA 脚本（Lua scripting）、LRU 驱动事件（LRU eviction）、事务（Transactions）和不同级别的磁盘持久化（persistence）功能，并通过 Redis 哨兵（哨兵）和集群（Cluster）保证缓存的高可用性（High availability）。
- Redis 数据丢失后可以通过*aof* 恢复



## 使用 Redis 有哪些好处

- 读取速度快，因为数据存在内存中，所以数据获取快；
- 支持多种数据结构，包括**字符串**、**列表**、**集合**、**有序集合**、**哈希**等；
- 支持事务，且操作遵守原子性，即对数据的操作要么都执行，要么都不支持；
- 还拥有其他丰富的功能，队列、主从复制、集群、数据持久化等功能。



## Redis的持久化方式

- [Redis的持久化方式](https://blog.csdn.net/ayonglink/article/details/121756609)
- RDB：根据指定的规则“定时”将内存中的数据存储在硬盘上，生成的快照
- AOF：每次执行命令后将命令本身记录下来，每次执行命令都会将命令写入到aof文件中



## 缓存雪崩、击穿、穿透

今天我们讲讲用 redis 缓存时容易遇到的一些问题。首先要明确一点的是，缓存雪崩、击穿、穿透这些名词虽然是针对缓存的，但是实际的受害方往往是数据库（存储层）。因为我们之所以用缓存，就是为数据库服务，也就是缓存的抗压性要大于数据库，当我们缓存由于一些原因使用不当时，就会导致数据库压力剧增，由此产生上面的几种问题。

> 题外话：我觉得一些技术上的专有名词很扯，有些是某位大神直接自己翻译过来传开的，其实它的本意可能翻译的并不准确。所以终归到底，专有名词能记住最好，有装逼的资本；没记住也没必要强记，理解含义和场景是正解，同时面对面试官先下手为强，避免忘记名词时的尴尬。

### 雪崩 cache avalanche

缓存雪崩往往是指在一段时间内，灭霸打了个响指，缓存大批量的失效（同一时间过期），没有缓存怎么办，当然就去数据库找数据了，如果请求量很大，数据库马上就崩了，由此就叫做缓存导致的雪崩。

缓存雪崩的英文原意是 stampeding herd（奔逃的野牛），指的是缓存层宕掉后，流量会像奔逃的野牛一样，奔向后端存储。

这种场景的解决方案就是给失效时间设置一个随机数：

```
setRedis（Key，value，time + Math.random() * 10000）；
```

或者设置热点数据永远不过期，更新数据后更新缓存就好了。

### 击穿 Cache breakdown

击穿跟雪崩类似，缓存雪崩是因为大面积的缓存失效，使数据层压力剧增，而缓存击穿不同的是指一个Key非常热点，在不停的扛着大并发，大并发集中对这一个点进行访问，当这个Key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，就像在一个完好无损的桶上凿开了一个洞。

### 穿透 Cache penetration

如果你看了前面的2个，缓存穿透也很好理解，都说穿透了，其实也就是缓存没数据，数据库也没数据，关键请求量还很大。穿透其实很可能是来自于攻击，指的是专门大批量的去请求不存在的数据，例如id是-1的数据。

简单的解决方案就是判断id的值范围，判断id≤1直接return了。当然id只是一个例子，其实针对客户端过来的参数，永远保持不信任的状态，给予合理的判断减少潜在的数据库压力是个好习惯。













































































