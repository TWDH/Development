package ca.on.kz.FactoryPattern.AbstractFactory;

import ca.on.kz.FactoryPattern.AbstractFactory.Factory.AppleFactory;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.IPhoneProduct;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.IRouterProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class Main {
    public static void main(String[] args) {
        // Apple Factory
        AppleFactory appleFactory = new AppleFactory();

        // Apple Phone
        IPhoneProduct applePhone = appleFactory.producePhone();
        applePhone.start();

        // Apple Router
        IRouterProduct appleRouter = appleFactory.productRouter();
        appleRouter.start();


        // Samsung Factory ？
    }
}
