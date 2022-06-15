package ca.on.kz.FactoryPattern.AbstractFactory.Product;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class ApplePhone implements IPhoneProduct{
    @Override
    public void start() {
        System.out.println("This is Apple Start");
    }

    @Override
    public void shutDown() {
        System.out.println("This is Apple shutDown");
    }

    @Override
    public void callUp() {
        System.out.println("This is Apple callUp");
    }

    @Override
    public void sendSMS() {
        System.out.println("This is Apple sendSMS");
    }
}
