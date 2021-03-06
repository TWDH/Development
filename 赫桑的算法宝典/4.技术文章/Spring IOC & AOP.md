# [Spring IOC & AOP](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)

## 1. IOC (Inverse of Control) 控制反转

* **将原本在程序中手动创建对象的控制权，交由Spring框架来管理, 完全不用考虑对象是如何被创建出来的**
* 把**实例化的对象** 注入 到开发人员**自定义的类**中
* 容器概念、控制反转、依赖注入 



### 1. IOC容器

* 实际上就是个 `map (key, value)`，里面存的是各种对象
  * 在 xml 里配置的 `bean` 节点、`@repository`,  `@service`, `@controller`,  `@component`
* 在项目启动的时候会读取配置文件里面的 `bean` 节点，**根据全限定类名使用反射创建对象放到map里**、扫描到打上上述注解的类还是通过反射创建对象放到 `map` 里。 
* 这个时候 `map` 里就有各种对象了，接下来我们在代码里需要用到里面的对象时再通过。注入（`autowired`. `resource`等注解，`xml` 里 `bean` 节点内的 `ref` 属性
* 项目启动的时候会读取 `xml` 节点 `ref` 属性**根据 id 主入**，也会扫描这 Jth.=1主解，根据**类型或id主入**；id就是对象名。 
* ![Spring IoC的初始化过程](https://images.xiaozhuanlan.com/photo/2019/57da0deca924d0e73dbb56501d2ec4be.png)



### 2. 控制反转（Inverse of Control）

*  没有引入 `IOC` 容器之前，对象 `A` 依赖于对象 `B`，那么对象 `A` 在初始化或者运行到某一点的时候，自己必须主动去创建 对象 `B` 或者使用已经创建的对象 `B`。无论是创建还是使用对象 `B`，控制权都在自己手上。
*  引入 `IOC` 容器之后，对象 `A` 与对象 `B` 之间失去了直接联系，当对象 `A` 运行到需要对象 `B` 的时候，**IOC容器会主动创建 一个对象B注入到对象A需要的地方**。 
* 通过前后的对比，不难看出来：对象 `A` 获得依赖对象 `B` 的过程，由**主动行为变为了被动行为 （B主动注入到A中）**，控制权颠倒过来了，这 就是“控制反转，这个名称的由来。 
* 全部对象的 **控制权全部上缴给“第三方"IOC容器**，所以，`IOC` 容器成了整个系统的关键核心，它起到了一种类似 “粘合剂” 的作用，把系统中的所有对象粘合在一起发挥作用，如果没有这个 “粘合剂”，对象与对象之间会彼此失去联系，这就是有人把`IOC`容器比喻成“粘合剂”的由来。 



### 3. 依赖注入 （Dependency Injection）

*  “获得依赖对象的过程被反转了”。控制被反转之后，**获得依赖对象的过程由自身管里变为了由IOC容器主动注入**。 
*  **依赖注入是实现IOC的方法**，就是由IOC容器在运行期间，动态地将某种依赖关系注入到对象之中。 
*  可以通过setter方法注入、构造注入、注解注入。
   *  依赖注入是 Spring 的思想,在使用 Spring 进行开发时，可以将对象交给 spring 进行管理，在初始化时spring 创建一批对象，当你需要用的时候只要从 spring 的容器中获取对象，而不用自己去new，当然在对象创建的时候可以注入另一个对象。比如 A, B 两个对象都由 spring 管理，A 中持有对 B 的引用，那么spring 在生成 A 对象的时候就已经吧B对象的一个实例给到 A 了，当你在A中用到B的时候直接使用就可以了。

*  https://www.vogella.com/tutorials/DependencyInjection/article.html

- ```java
  // Constructor-based Dependency Injection
  public class SimpleMovieLister {
  
      // the SimpleMovieLister has a dependency on a MovieFinder
      private final MovieFinder movieFinder;
  
      // a constructor so that the Spring container can inject a MovieFinder
      public SimpleMovieLister(MovieFinder movieFinder) {
          this.movieFinder = movieFinder;
      }
  
      // business logic that actually uses the injected MovieFinder is omitted...
  }
  ```




### 4. Spring依赖注入有哪几种方式

- [Spring依赖注入有哪几种方式](https://www.zhihu.com/question/452470872)
- Constructor-based
  - [Spring Boot简明教程--依赖注入的三种方式](https://cloud.tencent.com/developer/article/1777778)
  - ![image-20220430124836919](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220430124836919.png)
- Field
  - **接口注入** 依赖类必须要 **实现指定的接口**，然后实现该接口中的一个函数，该函数就是用于依赖注入。该函数的参数就是要注入的对象。
  - 优点 接口注入中，接口的名字、函数的名字都不重要，只要保证函数的参数是要注入的对象类型即可。
  - 缺点： 侵入行太强，不建议使用。
- ![image-20220429225558031](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220429225558031.png)
- ![image-20220429230709603](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220429230709603.png)

# 



### 4. Autowired byType 与 byName 策略

- https://blog.csdn.net/yangjiachang1203/article/details/52128830

- @Autowired是spring的注解，默认使用的是byType的方式向Bean里面注入相应的Bean。

  - ```java
    @Autowired
    private UserService userService;
    ```

  - 这段代码会在初始化的时候，在spring容器中寻找一个类型为`UserService`的bean实体注入，关联到userService的引入上。
  - 但是如果UserService这个接口存在多个实现类的时候，就会在spring注入的时候报错

- 多实现类场景

  - ```java
    @Autowired
    private UserService userService1;
    
    @Autowired
    private UserService userService2;
    
    @Autowired
    @Qualifier(value = "userService2")
    private UserService userService3;
    ```

  - 变量名用`userService1`,`userService2`，而不是`userService`。

    - 通常情况下`@Autowired`是通过`byType`的方法注入的，可是在多个实现类的时候，`byType`的方式不再是唯一，而需要通过`byName`的方式来注入，而这个`name`默认就是根据变量名来的。

  - 通过`@Qualifier`注解来指明使用哪一个实现类，实际上也是通过`byName`的方式实现。

## 5. Spring Test

- ![image-20220610115604857](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220610115604857.png)



## 2. AOP (Aspect-Oriented Programming)

* 将那些与业务无关，**却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来**，便于**减少系统的重复代码**，**降低模块间的耦合度**，并**有利于未来的可拓展性和可维护性**

  * 在不改变原来模型的基础上，动态的修改模型，来满足新需求
    * `traceBeforeCall()`
    * `traceEndCall()`: 主方法调用后使用该方法，类中实现 日志管理 等

* **Spring AOP就是基于动态代理的**，如果要代理的对象，

  * 实现了某个接口，那么`Spring AOP`会使用`JDK Proxy`，去创建代理对象
  * 没有实现接口的对象，就无法使用 `JDK Proxy` 去进行代理了，这时候`Spring AOP`会使用`Cglib` ，这时候`Spring AOP`会使用 `Cglib` 生成一个被代理对象的子类来作为代理，如下图所示：
  * ![SpringAOPProcess](https://images.xiaozhuanlan.com/photo/2019/926dfc549b06d280a37397f9fd49bf9d.jpg)

* 将程序中的交叉业务逻辑（比如安全，日志，事务等），封装成一个切面，然后注入到目标对象（具体业务逻辑）中去。

  * AOP可以对 某个对象 或 某些对象 的功能进行增强。比如对象中的方法进行增强，可以在执行某个方法之前额外的做一些事情，在某个方法执行之后额外的做一些事情 

  

### 1. Spring AOP 和 AspectJ AOP 有什么区别？

- **Spring AOP 属于运行时增强，而 AspectJ 是编译时增强。** Spring AOP 基于代理(Proxying)，而 AspectJ 基于字节码操作(Bytecode Manipulation)。
- Spring AOP 已经集成了 AspectJ ，AspectJ 应该算的上是 Java 生态系统中最完整的 AOP 框架了。AspectJ 相比于 Spring AOP 功能更加强大，但是 Spring AOP 相对来说更简单，
- 如果我们的切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择 AspectJ ，它比Spring AOP 快很多



## 3. Spring Bean

### 1. Spring 中的 bean 的作用域有哪些?

- `singleton` : 唯一 `bean` 实例，`Spring` 中的 `bean` 默认都是单例的。
- `prototype` : 每次请求都会创建一个新的 `bean` 实例。
- `request` : 每一次`HTTP`请求都会产生一个新的`bean`，该`bean`仅在当前`HTTP request`内有效。
- `session` : 每一次`HTTP`请求都会产生一个新的 `bean`，该`bean`仅在当前 `HTTP session` 内有效。
- `global-session`： 全局`session`作用域，仅仅在基于`portlet`的`web`应用中才有意义，`Spring5`已经没有了。
  - `Portlet`是能够生成语义代码(例如：`HTML`)片段的小型`Java Web`插件。它们基于`portlet`容器，可以像`servlet`一样处理`HTTP`请求。但是，与 `servlet` 不同，每个 `portlet` 都有不同的会话



### 2. Spring 中的单例 bean 的线程安全问题了解吗？

- 单例 bean 存在线程问题，主要是因为当**多个线程操作同一个对象**的时候，对这个对象的**非静态成员变量**的**写操作**会存在线程安全问题。
- 解决办法：
  - 在类中定义一个 `ThreadLocal` 成员变量，将需要的可变成员变量保存在 `ThreadLocal` 中（推荐的一种方式）



### 3. @Component 和 @Bean 的区别是什么？

1. 作用对象不同: `@Component` 注解作用于 **类**，而`@Bean`注解作用于 **方法**。
2. `@Component  `通常是通过 **类路径扫描** 来自动侦测以及自动装配到 `Spring` 
   - 容器中，我们可以使用 `@ComponentScan` 注解定义要扫描的路径从中找出标识了需要装配的类自动装配到 `Spring` 的 bean 容器中。
   - `@Bean` 注解通常是我们在 **标有该注解的方法** 中定义产生这个 `bean`, `@Bean` 告诉了 `Spring` 这是某个类的示例，当我需要用它的时候还给我。
3. `@Bean` 注解比 `Component` 注解的自定义性更强，而且很多地方我们只能通过 `@Bean` 注解来注册 `bean`。比如当我们引用第三方库中的类需要装配到 `Spring` 容器时，则只能通过 `@Bean`来实现。



### 4. 将一个类声明为Spring的 bean 的注解有哪些?

我们一般使用 `@Autowired` 注解**自动装配 bean**，要想把类标识成可用于 `@Autowired` 注解自动装配的 `bean` 的类,采用以下注解可实现：

- `@Component` ：通用的注解，可标注任意类为 `Spring` 组件。如果一个 `Bean ` 不知道属于哪个层，可以使用`@Component` 注解标注。
- `@Repository` : 对应持久层即 **Dao** 层，主要用于数据库相关操作。
- `@Service` : 对应**服务层**，主要涉及一些复杂的逻辑，需要用到 Dao 层。
- `@Controller` : 对应 **Spring MVC** 控制层，主要用于 **接受用户请求** 并 **调用 Service 层** 返回数据给前端页面。



### 5. Spring 中的 bean 生命周期?

> 1. 解析类得到Bean Definition
> 2. 如果有多个构造方法，则要推断构造方法
> 3. 确定好构造方法后，进行实例化得到一个对象
> 4. 对对象中的加了＠Autowired注解的属性进行属性填充
> 5. 回调Aware方法，比如BeanNameAware, BeanFactoryAware
> 6. 调用BeanPostProcessor的初始化前的方法
> 7. 调用初始化方法
> 8. 调用BeanPostProcessor的初始化后的方法，在这里会进行AOP
> 9. 如果当前创建的bean是单例的则会把bean放入单例池
> 10. 使用bean
> 11. Spring容器关闭时调用DisposableBean中destory(）方法 

> 1. Bean 容器找到配置文件中 Spring Bean 的定义。
> 2. Bean 容器利用 Java Reflection API 创建一个Bean的实例。
> 3. 如果涉及到一些属性值 利用 `set()`方法设置一些属性值。
> 4. 如果 Bean 实现了 `BeanNameAware` 接口，调用 `setBeanName()`方法，传入Bean的名字。
> 5. 如果 Bean 实现了 `BeanClassLoaderAware` 接口，调用 `setBeanClassLoader()`方法，传入 `ClassLoader`对象的实例。
> 6. 如果Bean实现了 `BeanFactoryAware` 接口，调用 `setBeanClassLoader()`方法，传入 `ClassLoade` r对象的实例。
> 7. 与上面的类似，如果实现了其他 `*.Aware`接口，就调用相应的方法。
> 8. 如果有和加载这个 Bean 的 Spring 容器相关的 `BeanPostProcessor` 对象，执行`postProcessBeforeInitialization()` 方法
> 9. 如果Bean实现了`InitializingBean`接口，执行`afterPropertiesSet()`方法。
> 10. 如果 Bean 在配置文件中的定义包含 init-method 属性，执行指定的方法。
> 11. 如果有和加载这个 Bean的 Spring 容器相关的 `BeanPostProcessor` 对象，执行`postProcessAfterInitialization()` 方法
> 12. 当要销毁 Bean 的时候，如果 Bean 实现了 `DisposableBean` 接口，执行 `destroy()` 方法。
> 13. 当要销毁 Bean 的时候，如果 Bean 在配置文件中的定义包含 destroy-method 属性，执行指定的方法。

![Spring Bean 生命周期](https://images.xiaozhuanlan.com/photo/2019/24bc2bad3ce28144d60d9e0a2edf6c7f.jpg)

![Spring Bean 生命周期](https://images.xiaozhuanlan.com/photo/2019/b5d264565657a5395c2781081a7483e1.jpg)



## 4. Spring MVC

### 1. 说说自己对于 Spring MVC 了解?

- Spring MVC 下我们一般把后端项目分为 
  - `Service` 层（处理业务）
  - `Dao` 层（数据库操作）
  - `Entity` 层（实体类）
  - `Controller` 层(控制层，返回数据给前台页面)

![img](https://images.xiaozhuanlan.com/photo/2019/2c3c2d5862db4b6dab3809183f64ab07.jpg)



### 2. SpringMVC 工作原理了解吗?

**原理如下图所示：**

![SpringMVC运行原理](https://images.xiaozhuanlan.com/photo/2019/093258b80bf44a737cdc3304ceea85d7.jpg)

**流程说明（重要）：**

1. 客户端（浏览器）发送请求，直接请求到 `DispatcherServlet`。
2. `DispatcherServlet` 根据请求信息调用 `HandlerMapping`，解析请求对应的 `Handler`。
3. 解析到对应的 `Handler`（也就是我们平常说的 `Controller` 控制器）后，开始由 `HandlerAdapter` 适配器处理。
4. `HandlerAdapter` 会根据 `Handler`来调用真正的处理器开处理请求，并处理相应的业务逻辑。
5. 处理器处理完业务后，会返回一个 `ModelAndView` 对象，`Model` 是返回的**数据对象**，`View` 是个逻辑上的 `View`。
6. `ViewResolver` 会根据逻辑 `View` 查找实际的 `View`。
7. `DispaterServlet` 把返回的 `Model` 传给 `View`（视图渲染）。
8. 把 `View` 返回给请求者（浏览器）



###  3. Spring 框架中用到了哪些设计模式？

- **工厂设计模式** : Spring使用工厂模式通过 `BeanFactory`、`ApplicationContext` 创建 bean 对象。
- **代理设计模式** : Spring AOP 功能的实现。
- **单例设计模式** : Spring 中的 Bean 默认都是单例的。
- **模板方法模式** : Spring 中 `jdbcTemplate`、`hibernateTemplate` 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
- **包装器设计模式** : 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
- **观察者模式:** Spring 事件驱动模型就是观察者模式很经典的一个应用。
- **适配器模式** :Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配`Controller`。

## 5. Spring 事务

### 1. Spring 管理事务的方式有几种？

1. 编程式事务，在代码中硬编码。(不推荐使用)
2. 声明式事务，在配置文件中配置（推荐使用）

**声明式事务又分为两种：**

1. 基于 `XML` 的声明式事务
2. 基于注解的声明式事务



### 2. Spring 事务中的隔离级别有哪几种?

**TransactionDefinition 接口中定义了五个表示隔离级别的常量：**

- `TransactionDefinition.ISOLATION_DEFAULT`: 
  - 使用后端 **数据库默认** 的隔离级别，Mysql 默认采用的 `REPEATABLE_READ`隔离级别 Oracle 默认采用的 `READ_COMMITTED`隔离级别.
- `TransactionDefinition.ISOLATION_READ_UNCOMMITTED`: 
  - **最低**的隔离级别，允许读取尚未提交的数据变更，**可能会导致脏读、幻读或不可重复读**
- `TransactionDefinition.ISOLATION_READ_COMMITTED`: 
  - 允许读取并发事务已经提交的数据，**可以阻止脏读，但是幻读或不可重复读仍有可能发生**
- `TransactionDefinition.ISOLATION_REPEATABLE_READ`: 
  - 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，**可以阻止脏读和不可重复读，但幻读仍有可能发生。**
- `TransactionDefinition.ISOLATION_SERIALIZABLE`: 
  - 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，**该级别可以防止脏读、不可重复读以及幻读**。但是这将严重影响程序的性能。通常情况下也不会用到该级别。



### 3. Spring 事务中哪几种事务传播行为?

**支持当前事务的情况：**

- **TransactionDefinition.PROPAGATION_REQUIRED：** 
  - 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
- **TransactionDefinition.PROPAGATION_SUPPORTS：**
  -  如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
- **TransactionDefinition.PROPAGATION_MANDATORY：** 
  - 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。（mandatory：强制性）

**不支持当前事务的情况：**

- **TransactionDefinition.PROPAGATION_REQUIRES_NEW：** 
  - 创建一个新的事务，如果当前存在事务，则把当前事务挂起。
- **TransactionDefinition.PROPAGATION_NOT_SUPPORTED：** 
  - 以非事务方式运行，如果当前存在事务，则把当前事务挂起。
- **TransactionDefinition.PROPAGATION_NEVER：** 
  - 以非事务方式运行，如果当前存在事务，则抛出异常。

**其他情况：**

- **TransactionDefinition.PROPAGATION_NESTED：** 
  - 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于`TransactionDefinition.PROPAGATION_REQUIRED`。



### 4. @Transactional(rollbackFor = Exception.class)注解了解吗？

- 当`@Transactional`注解作用于类上时，该类的所有 public 方法将都具有该类型的事务属性，同时，我们也可以在方法级别使用该标注来覆盖类级别的定义。如果类或者方法加了这个注解，那么这个类里面的方法抛出异常，就会回滚，数据库里面的数据也会回滚。
- 在 `@Transactional` 注解中如果不配置 `rollbackFor` 属性,那么事物只会在遇到 `RuntimeException` 的时候才会回滚,加上 `rollbackFor=Exception.class` ,可以让事物在遇到非运行时异常时也回滚。

## 6. JPA

### 1. 如何使用JPA在数据库中非持久化一个字段？

假如我们有有下面一个类：

```java
Entity(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    private String secrect;

}
```

如果我们想让`secrect` 这个字段不被持久化，也就是不被数据库存储怎么办？我们可以采用下面几种方法：

```java
static String transient1; // not persistent because of static
final String transient2 = “Satish”; // not persistent because of final
transient String transient3; // not persistent because of transient
@Transient
String transient4; // not persistent because of @Transient
```

一般使用后面两种方式比较多，我个人使用注解的方式比较多。

## 7. Spring Security

### 1. 认证 (Authentication) 和授权 (Authorization)

- **Authentication（认证）** 是验证您的身份的凭据（例如用户名/用户ID和密码），通过这个凭据，系统得以知道你就是你，也就是说系统存在你这个用户。所以，Authentication 被称为身份/用户验证。
- **Authorization（授权）** 发生在 **Authentication（认证）**之后。授权嘛，光看意思大家应该就明白，它主要掌管我们**访问系统的权限**。比如有些特定资源只能具有特定权限的人才能访问比如 `admin`，有些对系统资源操作比如删除、添加、更新只能特定人才具有。



### 2. Cookie的作用是什么?和Session有什么区别？

- **Cookie 一般用来保存用户信息** 比如
  - ①我们在 Cookie 中保存已经登录过得用户信息，下次访问网站的时候页面可以自动帮你登录的一些基本信息给填了；
  - ②一般的网站都会有保持登录也就是说下次你再访问网站的时候就不需要重新登录了，这是因为用户登录的时候我们可以存放了一个 `Token` 在 `Cookie` 中，下次登录的时候只需要根据 `Token` 值来查找用户即可(为了安全考虑，重新登录一般要将 Token 重写)；
  - ③登录一次网站后访问网站其他页面不需要重新登录。

- **Session 的主要作用就是通过服务端记录用户的状态。** 典型的场景是购物车，当你要添加商品到购物车的时候，系统不知道是哪个用户操作的，因为 HTTP 协议是无状态的。服务端给特定的用户创建特定的 Session 之后就可以标识这个用户并且跟踪这个用户了。



















## BeanFactory 和 ApplicationContext 区别？

- AppilcatlonContext 是 BeanFactory 的子接口 
- ApplicationContext 提供了更完整的功能 (扩展)： 
  - ①继承Messagesource，因此支持国际化。 
  - ②统一的资源文件访司方式。
  - ③提供在监听器中注册bean的事件。 
  - ④同时加载多个配置文件。
  - ⑤载入多个（有继承关系）上下文，使得每一个上下文都专注于一个特定的层次，比如应用的web层。 
    .
  - `BeanFactroy` 采用的是 **延迟加载** 形式来注入Bean的，即只有在使用到某个Bean时（调用getBean()))，才对该 Bean进行加载实例化。这样，我们就不能发现一些存在的Spring的配置问题。如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。
  - `ApplicationContext`，它是在容器启动时，**一次性创建所有的Bean**。这样，在容器启动时，我们就可以发现Spring中存在9勺配置错误，这样有利于检查所依赖属性是否注入。ApplicationContext启动后预载入所有的 单实例Bean，通过预载入单实例bean确保当你需要的时候，你就不用等待，因为它们已经创建好了。
  - 相对于基本的 BeanFactory, Appticationcontext 唯一的**不足是占用内存空间**。当应用程序配置Bean较多时，程序启动较慢。
  - BeanFactory通常以编程的方式被创建，Applicationcontext 还能以声明的方式创建，如使用 ContextLoader
  - BeanFactory 和 ApplicationContext 都支持 `BeanPostProcessor`、`BeanFactoryPostProcessor` 的使用但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是**自动注册**。 



## 



## Spring的Bean实例化过程应该是怎样的？

1. ### 配置元信息

   - ![image-20210506214930488](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210506214930488.png)

2. ### BeanDefination

   - Spring选择在内存中表示这些配置元信息的方式
     

3. ### BeanDefinationReader

   - 读取xml配置元信息
   - `xml：XmlBeanDefinationReader`
   - `properties：PropertiesBeanDefinitionReader`

4. ### BeanDefinationRegistry

   - 解决：去哪里找到对应的 BeanDefination
   - 通过Bean定义的id找到对象的BeanDefination的对应关系，保存在 BeanDefinationRegistry
   - Spring通过 BeanDefinationReader 将配置元信息加载到内存生成相应的 BeanDefination 之后，就将其**注册到BeanDefinationRegistry** 中，BeanDefinationRegistry就是一个存放 BeanDefination 的大篮子，它也是一种键值对的形式，通过特定的**Bean定义的id**，**映射**到相应的**BeanDefination**

5. ### BeanFactoryPostProcessor

   - 负责对注册到BeanDefinationRegistry中的一个个的BeanDefination进行一定程度上的修改与替换
   - 例如：替换`$`占位符为配置文件中的真实的数据
     - ![image-20210506215716861](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210506215716861.png)

—————————————————–容器启动完成—————————————————–

- 容器的启动阶段的最终产物就是注册到BeanDefinationRegistry中的一个个BeanDefination
- ![image-20210506215904635](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20210506215904635.png)

—————————————————–容器实例化开始—————————————————–

1. ### 对象创建策略

   - 对象的创建采用了策略模式
   - 使用反射的方式创建对象，借助前面 `BeanDefinationRegistry` 中的 `BeanDefination` 

2. ### BeanWrapper——对象的外衣

   - 统一对不同类型对象的访问，Spring给所有创建的`Bean`实例穿上了外套 — BeanWrapper
   - BeanWrapper 是对反射相关API的简单封装，使得上层使用反射完成相关的业务逻辑大大的简化
   - 获取某个对象的属性，调用某个对象的方法，现在不需要在写繁杂的反射API
   - 直接通过 `BeanWrapper` 就可以完成相关操作

3. ### 设置对象属性

   - 需要为`BeanWrapper`设置属性以及依赖对象
   - 对于基本类型的属性，如果配置元信息中有配置，那么将直接使用配置元信息中的设置值赋值即可，即使基本类型的属性没有设置值，那么得益于JVM对象实例化过程，属性依然可以被赋予默认的初始化零值。
   - 对于引用类型的属性，Spring会将所有已经创建好的对象放入一个Map结构中，此时Spring会检查所依赖的对象是否已经被纳入容器的管理范围之内，也就是Map中是否已经有对应对象的实例了。如果有，那么直接注入，如果没有,那么Spring会暂时放下该对象的实例化过程，转而先去实例化依赖对象，再回过头来完成该对象的实例化过程



**这里有一个Spring中的经典问题，那就是Spring是如何解决循环依赖的？**

* Spring是通过三级缓存解决循环依赖，并且只能解决Setter注入的循环依赖



4. ### 检查Aware相关接口

   - 想要依赖Spring中的相关对象，使用Spring的相关API,那么可以实现相应的Aware接口，Spring IOC容器就会为我们自动注入相关依赖对象实例
   - 达对于`BeanFactory`来说，这一步的实现是先检查相关的`Aware`接口，然后去Spring的对象池(也就是容器，也就是那个Map结构)中去查找相关的实例(例如对于`ApplicationContextAware`接口，就去找`ApplicationContext`实例)，也就是说我们必须要在配置文件中或者使用注解的方式，将相关实例注册容器中，`BeanFactory`才可以为我们自动注入。
   - 而对于ApplicationContext，由于其本身继承了一系列的相关接口，所以当检测到Aware相关接口，需要相关依赖对象的时候，ApplicationContext完全可以将自身注入到其中，ApplicationContext实现这一步是通过下面要讲到的东东——BeanPostProcessor
   - ![图片](https://mmbiz.qpic.cn/mmbiz_png/8KKrHK5ic6XBzOtg5OQ6icxfKTvViaePJOcawOTKyqrhVWJxl6WgtvSa9eyrYRUVTEcy9l4K4czlhoJV98T1UKs5Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)
     - 例如ApplicationContext继承自ResourceLoader和MessageSource，那么当我们实现ResourceLoaderAware和MessageSourceAware相关接口时，就将其自身注入到业务对象中即可

5. ### BeanPostProcessor前置处理

   - `BeanFactoryPostProcessor `关注对象被创建之前 那些配置的修修改改
   - `BeanPostProcessor ` 阶段关注对象已经被创建之后 的功能增强，替换等操作
     - `BeanPostProcessor`前置处理就是在要生产的Bean实例放到容器之前，允许我们程序员对Bean实例进行一定程度的修改，替换等操作。
   - ApplicationContext对于Aware接口的检查与自动注入就是通过BeanPostProcessor实现的，在这一步Spring将检查Bean中是否实现了相关的Aware接口，如果是的话，那么就将其自身注入Bean中即可
   - Spring中AOP就是在这一步实现的偷梁换柱，产生对于原生对象的代理对象，然后将对源对象上的方法调用，转而使用代理对象的相同方法调用实现的。

6. ### 自定义初始化逻辑

   1. `InitializingBean`
   2. 配置init-method参数

   - 一般通过配置init-method方法比较灵活。

7. ### BeanPostProcess后置处理

   - Spring又留给我们的最后一个扩展点
   - AOP

8. ### 自定义销毁逻辑

   - 这一步对应自定义初始化逻辑
     - 这里一个比较典型的应用就是配置dataSource的时候destory-method为数据库连接的close()方法。

   1. 实现DisposableBean接口
   2. 配置destory-method参数。

9. ### 使用bean

10. ### 调用回调销毁接口

    - Spring的Bean在为我们服务完之后，马上就要消亡了。
    - Spring将以回调的方式调用我们自定义的销毁逻辑
    - ![图片](https://mmbiz.qpic.cn/mmbiz_png/8KKrHK5ic6XBzOtg5OQ6icxfKTvViaePJOcjmSyS6MDFLFNnAeF6Z8yI44MowvPD9ECllJCzjr4Y9uEaib0aia1O9Uw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## Bean的作用域？

- **singleton**：默认，每个容器中只有一个 bean 的实例，单例的模式由 BeanFactory 自身来维护。该对象的生命周期是与Spring IOC 容器一致的（但在第一次被汪入时才芸创建）。
- **prototype**：为每一个 bean 请求提供一个实例。在每次注入时都会创建一个新的对象
- **request**：bean 被定义为在每个日，IT户请求中创建一个单例对象，也就是说在单个请求中都会复用这一个单例对象。 .
- **session**：与 request 范围类似，确保每个 session 中有一个bean的实例，在 session 过期后，bean会随之失效。 
- **application**：bean 被定义为在 ServletContext 的生命周期中复用一个单例对象。 
- **websocket**：bean 被定义为在 websocket 的生命周期中复用一个单例对象。 
- **global-session**：全局作用域，global-session 和 Portlet 应用相关。当你的应用部署在Portlet容器中工作时， 它包含很多portlet。如果你想要声明诩听有的portlet共用全局的存储变量的话，那么这全局变量需要存储在 global-session中。全局作用域与Servlet中的session作用域效果相同。 









































































































