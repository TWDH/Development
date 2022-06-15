package ca.on.kz.FactoryPattern.SimpleFactory;

import ca.on.kz.FactoryPattern.SimpleFactory.Factory.PhoneFactory;
import ca.on.kz.FactoryPattern.SimpleFactory.Product.IphoneProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class Main {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        IphoneProduct applePhone = phoneFactory.produce("apple");
        applePhone.start();
    }
}
