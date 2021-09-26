# 对象数组

## 对象数组

* 定义一个数组，储存3个Person对象
* ```java
  public static void main(String[] args) {
  // 首先创建一个长度为3的数组，里面用来存放Person类型的对象
      Person[] array = new Person[3];
    
      Person one = new Person("迪丽热巴", 18);
      Person two = new Person("古力娜扎", 28);
      Person three = new Person("玛尔扎哈", 38);
    
      // 将one当中的地址值赋值到数组的0号元素位置
      array[0] = one;
      array[1] = two;
      array[2] = three;
    
      System.out.println(array[0]); // 地址值
      System.out.println(array[1]); // 地址值
      System.out.println(array[2]); // 地址值
    
      System.out.println(array[1].getName()); // 古力娜扎
  }
  ```



