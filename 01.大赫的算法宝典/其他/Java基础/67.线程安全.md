# 线程安全

## 1.概述

* 多线程访问共享数据

## 2.线程同步

### 2.1 方案一：同步代码块

* 同步中的线程，没有执行完毕就不会释放锁；同步外的线程没有锁进不去同步
* **格式**
  * ```java
    synchronized(锁对象){
        可能会出现线程安全问题的代码(访问了共享数据的代码)
    }
    ```
* **注意事项：**
  1. 通过代码块中的锁对象,可以使用任意的对象
  2. 但是必须保证多个线程使用的锁对象是同一个
  3. 锁对象作用: 把同步代码块锁住,只让一个线程在同步代码块中执行

```java
//main
public static void main(String[] args) {
    //创建Runnable接口的实现类对象
    RunnableImpl run = new RunnableImpl();
    //创建Thread类对象,构造方法中传递Runnable接口的实现类对象
    Thread t0 = new Thread(run);
    Thread t1 = new Thread(run);
    Thread t2 = new Thread(run);
    //调用start方法开启多线程
    t0.start();
    t1.start();
    t2.start();
}
```

```java
//RunnableImpl
public class RunnableImpl implements Runnable {
    private int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            while (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票 ");
                ticket--;
            }
        }
    }
}
```

### 2.2 方法二：同步方法

* **使用步骤：**
  1. 把访问了共享数据的代码抽取出来,放到一个方法中
  2. 在方法上添加synchronized修饰符 
* **格式：**
  * ```java
    修饰符 synchronized 返回值类型 方法名(参数列表){
        可能会出现线程安全问题的代码(访问了共享数据的代码)
    }
    ```
* 同步方法的锁对象是谁? 
  * 就是实现类对象 new RunnableImpl\(\) 也是就是**this**

```java
//同步方法
public class RunnableImpl implements Runnable {
    private int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {
        payticket();
    }

    public synchronized void payticket() {
        while (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票 ");
            ticket--;
        }
    }
}
```

```java
//或者this
public /*synchronized*/ void payTicket(){
    synchronized (this){
        //先判断票是否存在
        if(ticket>0){
            //提高安全问题出现的概率,让程序睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //票存在,卖票 ticket--
            System.out.println(Thread.currentThread().getName()+"-->正在卖第"+ticket+"张票");
            ticket--;
        }
    }
}
```

* **静态的同步方法**

```java
/*
    静态的同步方法
    锁对象是谁?
    不能是this
    this是创建对象之后产生的,静态方法优先于对象
    静态方法的锁对象是本类的class属性-->class文件对象(反射)
 */
public static /*synchronized*/ void payTicketStatic(){
    synchronized (RunnableImpl.class){
        //先判断票是否存在
        if(ticket>0){
            //提高安全问题出现的概率,让程序睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //票存在,卖票 ticket--
            System.out.println(Thread.currentThread().getName()+"-->正在卖第"+ticket+"张票");
            ticket--;
        }
    }

}
```

### 2.3 方法三：Lock

* Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。 
* 方法
  * `void lock()`获取锁。
  * `void unlock()` 释放锁。 
* 使用步骤
  1. 在成员位置创建一个ReentrantLock对象
  2. 在可能会出现安全问题的代码前调用Lock接口中的方法lock获取锁
  3. 在可能会出现安全问题的代码后调用Lock接口中的方法unlock释放锁

```java
public class RunnableImpl implements Runnable {
    private int ticket = 100;
    Lock l = new ReentrantLock();
    
    @Override
    public void run() {
        l.lock();
        while (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票 ");
            ticket--;
        }
        l.unlock();
    }
}
```

try...catch...finally 最终一定释放锁

```java
    @Override
    public void run() {
        //使用死循环,让卖票操作重复执行
        while(true){
            //2.在可能会出现安全问题的代码前调用Lock接口中的方法lock获取锁
            l.lock();

            //先判断票是否存在
            if(ticket>0){
                //提高安全问题出现的概率,让程序睡眠
                try {
                    Thread.sleep(10);
                    //票存在,卖票 ticket--
                    System.out.println(Thread.currentThread().getName()+"-->正在卖第"+ticket+"张票");
                    ticket--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //3.在可能会出现安全问题的代码后调用Lock接口中的方法unlock释放锁
                    l.unlock();//无论程序是否异常,都会把锁释放
                }
            }
        }
    }
```

??? 所有都是Thread-0卖出的票，多线程有何意义

