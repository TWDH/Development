# HashTable&lt;K,V&gt;

## 1.概述

* 键-值不能为null
* 同步
* Hashtable:
  * 底层也是一个哈希表,是一个线程安全的集合,是单线程集合,速度慢
* HashMap:
  * 底层是一个哈希表,是一个线程不安全的集合,是多线程的集合,速度快 
* Hashtable和Vector集合一样,在jdk1.2版本之后被更先进的集合\(HashMap,ArrayList\)取代了
  * Hashtable的子类Properties依然活跃在历史舞台； Properties集合是一个唯一和IO流相结合的集合

```java
public class Demo02Hashtable {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put(null,"a");
        map.put("b",null);
        map.put(null,null);
        System.out.println(map);//{null=null, b=null}

        Hashtable<String,String> table = new Hashtable<>();
        //table.put(null,"a");//NullPointerException
        //table.put("b",null);//NullPointerException
        table.put(null,null);//NullPointerException
    }
}
```

### 练习：

```java
/*
练习:
    计算一个字符串中每个字符出现次数

分析:
    1.使用Scanner获取用户输入的字符串
    2.创建Map集合,key是字符串中的字符,value是字符的个数
    3.遍历字符串,获取每一个字符
    4.使用获取到的字符,去Map集合判断key是否存在
        key存在:
            通过字符(key),获取value(字符个数)
            value++
            put(key,value)把新的value存储到Map集合中
        key不存在:
            put(key,1)
    5.遍历Map集合,输出结果
 */
public class HashMap_Exercise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a String:");
        String str = sc.next();

        //hashmap
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                Integer value = map.get(c);
                value++;
                map.put(c, value);
            }
            else {
                map.put(c, 1);
            }
        }

        for (Character key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key+"--->"+value);
        }
    }
}
```

