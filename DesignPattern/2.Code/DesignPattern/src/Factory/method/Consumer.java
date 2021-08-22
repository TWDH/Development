package Factory.method;

import Factory.simple.CarFactory;

public class Consumer {
    public static void main(String[] args) {
        // 工厂方法模式
        // 可以自由定义，增加 Car
        Car wuling = new wulingFactory().getCar();
        Car tesla = new TeslaFactory().getCar();

        wuling.name();
        tesla.name();

    }
}
