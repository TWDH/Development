package ca.on.kz.FactoryPattern.AbstractFactory.Factory;

import ca.on.kz.FactoryPattern.AbstractFactory.Product.IPhoneProduct;
import ca.on.kz.FactoryPattern.AbstractFactory.Product.IRouterProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public interface IProductFactory {
    IPhoneProduct producePhone();

    IRouterProduct productRouter();
}
