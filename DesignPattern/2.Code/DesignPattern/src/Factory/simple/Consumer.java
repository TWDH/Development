package Factory.simple;

public class Consumer {
    public static void main(String[] args) {
        // 原设计模式
        System.out.println("===== 原设计模式 =====");
        Car wuLing = new WuLing();
        Car tesla = new Tesla();

        wuLing.name();
        tesla.name();

        // 使用工厂创建
        System.out.println("===== 简单工厂 =====");
        Car wuLing2 = CarFactory.getCar("wuling");
        Car tesla2 = CarFactory.getCar("tesla");

        wuLing2.name();
        tesla2.name();

    }
}
