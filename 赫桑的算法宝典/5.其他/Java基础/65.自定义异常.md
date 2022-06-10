# 自定义异常

## 1.概述

* 自定义异常类
  * java提供的异常类,不够我们使用,需要自己定义一些异常类
* 格式
  * ```java
    public class XXXExcepiton extends Exception | RuntimeException{
        添加一个空参数的构造方法
        添加一个带异常信息的构造方法
    }
    ```
* 注意：
  1. 自定义异常类一般都是以Exception结尾,说明该类是一个异常类
  2. 自定义异常类,必须的继承Exception或者RuntimeException
     * 继承Exception:那么自定义的异常类就是一个**编译期异常**,如果方法内部抛出了编译期异常,就必须处理这个异常,要么throws,要么try...catch
     * 继承RuntimeException:那么自定义的异常类就是一个**运行期异常**,无需处理,交给虚拟机处理\(中断处理\)

```java
public class Demo01RegisterException {
    // 1.使用数组保存已经注册过的用户名(数据库)
    static String[] usernames = {"张三","李四","王五"};

    public static void main(String[] args) /*throws RegisterException*/ {
        //2.使用Scanner获取用户输入的注册的用户名(前端,页面)
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要注册的用户名:");
        String username = sc.next();
        checkUsername(username);

    }

    //3.定义一个方法,对用户输入的中注册的用户名进行判断
    public static void checkUsername(String username) /*throws RegisterException*/ {
        //遍历存储已经注册过用户名的数组,获取每一个用户名
        for (String name : usernames) {
            //使用获取到的用户名和用户输入的用户名比较
            if(name.equals(username)){
                //true:用户名已经存在,抛出RegisterException异常,告知用户"亲，该用户名已经被注册";
                try {
                    throw new RegisterException("亲，该用户名已经被注册");
                } catch (RegisterException e) {
                    e.printStackTrace();
                    return; //结束方法
                }
            }
        }

        //如果循环结束了,还没有找到重复的用户名,提示用户"恭喜您,注册成功!";
        System.out.println("恭喜您,注册成功!");
    }
}

```

