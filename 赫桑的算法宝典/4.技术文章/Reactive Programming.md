- [关于Mono和Flux的理解](https://blog.csdn.net/TNTnine/article/details/83060950)
  - 为了方便下面理解Mono和Flux，也可以理解为 `Publisher`（**发布者**也可以理解为**被观察者**）主动推送数据给 `Subscriber`（订阅者也可以叫观察者），如果 Publisher 发布消息太快，超过了 Subscriber 的处理速度，如何处理。这时就出现了 `Backpressure`（背压-----指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略）
- [Flux、Mono、Reactor 实战](https://blog.csdn.net/crazymakercircle/article/details/124120506?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-124120506-blog-83060950.pc_relevant_multi_platform_whitelistv1_exp2&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-124120506-blog-83060950.pc_relevant_multi_platform_whitelistv1_exp2&utm_relevant_index=1)
- [java8 函数式接口——Function/Predict/Supplier/Consumer](https://www.cnblogs.com/NeilZhang/p/11086698.html)
- [Supplier 解释与例子](https://blog.csdn.net/qq_40574571/article/details/107781119)



# Reactor

- Reactor 有两个核心类： `Flux<T>` 和 `Mono<T>`，这两个类都实现 Publisher 接口。
  - Flux 类似 RxJava 的 Observable，它可以触发零到多个事件，并根据实际情况结束处理或触发错误。
  - Mono 最多只触发一个事件，所以可以把 Mono 用于在异步任务完成时发出通知。
- Flux 和 Mono 都是数据流的**发布者**，使用 Flux 和 Mono 都可以发出三种数据信号：**元素值**，**错误信号**，**完成信号**；错误信号和完成信号都代表终止信号，终止信号用于告诉订阅者数据流结束了，错误信号终止数据流同时把错误信息传递给订阅者。

## just and subscribe

- `just()`：创建 Flux 序列，并声明指定数据流

- `subscribe()`：订阅 Flux 序列，只有进行订阅后才会触发数据流，不订阅就什么都不会发生

- ```java
  public class TestReactor {
      public static void main(String[] args) {
          //just()：创建Flux序列，并声明数据流，
          Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);//整形
        
          //subscribe()：订阅Flux序列，只有进行订阅后才回触发数据流，不订阅就什么都不会发生
          integerFlux.subscribe(System.out::println);
          
          Flux<String> stringFlux = Flux.just("hello", "world");//字符串
          stringFlux.subscribe(System.out::println);
          
          //fromArray(), fromIterable() 和 fromStream()：可以从一个数组、Iterable 对象或Stream 对象中创建Flux序列
          Integer[] array = {1,2,3,4};
          Flux.fromArray(array).subscribe(System.out::println);
          
          List<Integer> integers = Arrays.asList(array);
          Flux.fromIterable(integers).subscribe(System.out::println);
          
          Stream<Integer> stream = integers.stream();
          Flux.fromStream(stream).subscribe(System.out::println);
      }
  }
  ```

- 









































