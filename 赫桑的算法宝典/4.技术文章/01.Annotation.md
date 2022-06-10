# 01.Annotation

1. 在进行开发自定义注解前需要在POM文件中添加aop依赖

   - ```xml
     <dependency>
     	<groupId>org.springframework.boot</groupId>
        	<artifactId>spring-boot-starter-aop</artifactId>
     </dependency>
     ```

2. 创建`annotation`文件夹(名字随意)，然后创建注解类，注意这是一个`@interface`,在Kind出选择`Annotation`

   - ![在这里插入图片描述](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/20190627132406743.png)

3. ```java
   @Target({ElementType.METHOD, ElementType.TYPE})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   public @interface Token {
       String value() default "";
   }
   ```

4. 注解：**@Target**、**@Retention**、**@Documented**

   - `@Target({ElementType.TYPE})` 注解

     - ElementType 这个枚举类型的常量提供了一个简单的分类：注解可能出现在Java程序中的语法位置（这些常量与元注解类型(@Target)一起指定**在何处写入注解**的合法位置）

     - ```java
       public enum ElementType {
           /** Class, interface (including annotation type), or enum declaration */
           TYPE,
       
           /** Field declaration (includes enum constants) */
           FIELD,
       
           /** Method declaration */
           METHOD,
       
           /** Formal parameter declaration */
           PARAMETER,
       
           /** Constructor declaration */
           CONSTRUCTOR,
       
           /** Local variable declaration */
           LOCAL_VARIABLE,
       
           /** Annotation type declaration */
           ANNOTATION_TYPE,
       
           /** Package declaration */
           PACKAGE,
       
           /**
            * Type parameter declaration
            *
            * @since 1.8
            */
           TYPE_PARAMETER,
       
           /**
            * Use of a type
            *
            * @since 1.8
            */
           TYPE_USE
       }
       ```

   - `@Retention({RetentionPolicy.Runtime})` 注解

     - RetentionPolicy这个枚举类型的常量描述保留注解的各种策略，它们与元注解(@Retention)一起指定**注释要保留多长时间**

     - ```java
       public enum RetentionPolicy {
           /**
            * Annotations are to be discarded by the compiler.
            */
           SOURCE,
       
           /**
            * Annotations are to be recorded in the class file by the compiler
            * but need not be retained by the VM at run time.  This is the default
            * behavior.
            */
           CLASS,
       
           /**
            * Annotations are to be recorded in the class file by the compiler and
            * retained by the VM at run time, so they may be read reflectively.
            *
            * @see java.lang.reflect.AnnotatedElement
            */
           RUNTIME
       }
       ```

   - `@Documented`注解

     - Documented注解表明这个注解是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注解了文档化，它的**注解成为公共API的一部分**。

5. 创建Aspect文件夹(名称随意)，然后创建切面类。注意：要选择`@Aspect`

   - ```java
     @Aspect
     @Component
     @SuppressWarnings({"unused"})
     public class TokenAspect {
     
         public static final Logger logger = LoggerFactory.getLogger(TokenAspect.class);
     
         public static final String TOKEN_KEY = "token";
     
     	/**
     	 * checkUrl,keyUrl,tokenScope是通过Spring的@Value注解来获取配置文件中的配置项
     	 * @Value等同于Spring原先的配置模式中的value
     	 * <bean id="" class="">
     	 * 		<property name="" value="">
     	 * </bean>
     	 */
         @Value(value = "${jwt.checkUrl}")
         String checkUrl;
     
         @Value(value = "${jwt.keyUrl}")
         String keyUrl;
     
         @Value(value = "${jwt.clientId}")
         String tokenScope;
     
         @Pointcut("@annotation(com.**.***.项目名.annotation.Token)")
         public void annotationPointcut() {
     
         }
     
         @Before("annotationPointcut()")
         public void beforePointcut(JoinPoint joinPoint) {
         	// 此处进入到方法前  可以实现一些业务逻辑
         }
     
         @Around("annotationPointcut()")
         public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
             MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
             String[] params = methodSignature.getParameterNames();// 获取参数名称
             Object[] args = joinPoint.getArgs();// 获取参数值
             if (null == params || params.length == 0){
                 String mes = "Using Token annotation, the token parameter is not passed, and the parameter is not valid.";
                 logger.info(mes);
                 throw new Exception(mes);
             }
             boolean hasToken = false;
             int index = 0;
             for (int i = 0; i < params.length; i++) {
                 if (TOKEN_KEY.equals(params[i])) {
                     hasToken = true;
                     index = i;
                     break;
                 }
             }
             if (!hasToken){
                 String mes = "The token parameter is not included in the requested parameter, the parameter is not valid.";
                 logger.info(mes);
                 throw new Exception(mes);
             }
             this.checkToken(String.valueOf(args[index]));
             return joinPoint.proceed();
         }
     
         /**
          * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
          * @param joinPoint
          */
         @AfterReturning("annotationPointcut()")
         public void doAfterReturning(JoinPoint joinPoint) {
         }
     
         private void checkToken(String token) {
             Decrypt decrypt = new Decrypt();// 这个类是自己的业务类，主要进行token验证(JWT)
             try {
                 decrypt.check(token, checkUrl, keyUrl, tokenScope);
             } catch (Exception e) {
                 logger.info(e.getMessage());
                 throw new RuntimeException(e.getMessage());
             }
         }
     
     }
     ```

6. 上面基本上完成了自定义注解的开发，在使用中可以直接使用`@Token`即可。如：

   - ```java
     /**
     	 * 使用了@Token，如果在切面类中没有经过校验验证，会直接抛出异常，checkToken方法中不会进入
     	 */
     @Token
     @RequestMapping(value = "checkToken", method = RequestMethod.POST)
     public String checkToken(@RequestParam String token) {
         // 自己的业务实现
         return null;
     }
     ```

   - 









































































