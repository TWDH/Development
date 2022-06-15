package ca.on.kz.FactoryPattern.SimpleFactory.Product;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */

// Concrete class
public class SamsungPhone implements IphoneProduct{
    @Override
    public void start() {
        System.out.println("This is SamsungPhone Start");
    }

    @Override
    public void shutDown() {
        System.out.println("This is SamsungPhone shutDown");
    }

    @Override
    public void callUp() {
        System.out.println("This is SamsungPhone callUp");
    }

    @Override
    public void sendSMS() {
        System.out.println("This is SamsungPhone sendSMS");
    }
}
