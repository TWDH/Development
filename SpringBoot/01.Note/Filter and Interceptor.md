# Filter and Interceptor

### SpringBoot中使用Filter

1、在 Filter 上加入 `@WebFilter(urlPatterns = "/path 也可以是*", filterName = "filterName")`注解，配置 urlPatterns 和filterName

2、在启动类上加入 `@ServletComponentScan` 注解

### 在SpringBoot中使用Interceptor

1. 创建类，实现 `HandlerInterceptor` 接口
2. `Interceptor` 和 `Filter` 有所不同，`HandlerInterceptor` 中有三个方法，由于JDK8之后支持了default关键字，其内的方法都是使用default修饰的，不会提示我们手动重写，需要点击进入源码找到并复制到上面创建的类中，修改 defalut 为 public。

