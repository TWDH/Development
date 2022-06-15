package ca.on.kz.FactoryPattern.AbstractFactory.Product;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public interface IPhoneProduct {
    void start();

    void shutDown();

    void callUp();

    void sendSMS();
}
