# Singleton

- ```java
  public class ParkingLot{
      // static instance: ParkingLot.getInstance()
      private static ParkingLot instance = null;
      private List<Level> levels;
      
      // private modifier
      private ParkingLot() {
          levels = new ArrayList<>();
      }
      
      // get instance
      public static ParkingLot getInstance(){
          if(instance == null){
              instance = new ParkingLot();
          }
          return instance;
      }
      
  }
  ```

- ![image-20220525204209776](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525204209776.png)

  - 加锁：线程安全

- ![image-20220525204241677](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525204241677.png)

  - 静态内部类
  - final：多个线程也只有一个

- 用法

  - ElevatorSystem（Singleton）
  - Elevator（不能单例）
  - 全局 configuration 

# State Design Pattern

- Vending Machine
- ATM
- 条件：**use case 导致 State 转换**
  - ParkingLot，open/close 是时间导致的，而不是 use case 导致



# Factory Design Pattern

- Simple Factory

- ![image-20220525210932127](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525210932127.png)

- ```java
  // Interface
  public interface Reader{
      public void display();
  }
  
  // Concrete Class
  public class PDFReader implements Reader{
      @Override
      public void display(){
          System.out.println("This is PDF display")
      }
  }
  
  // ReaderFactory
  class ReaderFactory{
      public Reader produce(Book){
          if(book.getFormat == Format.PDF){
              return new PDFReader();
          }
          else if(book.getFormat == Format.MOBI){
              return new MOBIReader();
          }
          else if(book.getFormat == Format.EPUB){
              return new EPUBReader();
          }
          return null;
      }
  }
  
  // main
  class Kindle{
      public void read (){
          ReaderFactory factory = new ReaderFactory();
          Reader reader = factory.produce(book);
          reader.display();
      }
  }
  
  ```

- ![image-20220525214710973](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525214710973.png)

- ```java
  // Interface
  public interface Reader{
      public void display();
  }
  
  // Concrete Class
  public class PDFReader implements Reader{
      @Override
      public void display(){
          System.out.println("This is PDF display")
      }
  }
  
  // Factory Interface
  public interface KindleFactory {
      public Reader produce();
  }
  
  // Concrete PDF Kindle Factory
  class PDFKindleFactory implements KindleFactory{
      @Override
      public Reader produce() {
          return new PDFReader();
      }
  }
  
  // main
  class Kindle{
      public void read (){
          KindleFactory factory = new PDFKindleFactory();
          Reader reader = factory.createReader(book);
          reader.display();
      }
  }
  
  ```

- 

- ![image-20220525214724677](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525214724677.png)

# Strategy Design Pattern

- Strategy is about behavior
- Factory is about creation
- ![image-20220525221205787](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525221205787.png)