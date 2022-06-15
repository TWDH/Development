package ca.on.kz.FactoryPattern.FactoryMethod;

import ca.on.kz.FactoryPattern.FactoryMethod.Factory.AppleFactory;
import ca.on.kz.FactoryPattern.FactoryMethod.Product.IphoneProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class Main {
    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        IphoneProduct applePhone = appleFactory.produce();
        applePhone.start();
    }
}
