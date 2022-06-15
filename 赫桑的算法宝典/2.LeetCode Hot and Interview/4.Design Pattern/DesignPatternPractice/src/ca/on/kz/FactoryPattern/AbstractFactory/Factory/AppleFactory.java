package ca.on.kz.FactoryPattern.AbstractFactory.Factory;

import ca.on.kz.FactoryPattern.AbstractFactory.Product.*;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class AppleFactory implements IProductFactory{
    @Override
    public IPhoneProduct producePhone() {
        return new ApplePhone();
    }

    @Override
    public IRouterProduct productRouter() {
        return new AppleRouter();
    }
}
