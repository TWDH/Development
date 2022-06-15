package ca.on.kz.FactoryPattern.AbstractFactory.Factory;

import ca.on.kz.FactoryPattern.AbstractFactory.Product.IPhoneProduct;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.IRouterProduct;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.SamsungPhone;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.SamsungRouter;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class SamsungFactory implements IProductFactory{
    @Override
    public IPhoneProduct producePhone() {
        return new SamsungPhone();
    }

    @Override
    public IRouterProduct productRouter() {
        return new SamsungRouter();
    }
}
