# 四种权限修饰符

```java
                    public  >   protected   >   (default)   >   private
同一个类（我自己）        YES         YES             YES             YES
同一个包（我邻居）        YES         YES             YES             NO
不同包子类（我儿子）       YES         YES             NO              NO
不同包非子类（陌生人）     YES         NO              NO              NO
```

