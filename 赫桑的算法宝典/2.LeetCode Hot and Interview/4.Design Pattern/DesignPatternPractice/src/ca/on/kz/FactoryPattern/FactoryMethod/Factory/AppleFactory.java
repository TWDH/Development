package ca.on.kz.FactoryPattern.FactoryMethod.Factory;

import ca.on.kz.FactoryPattern.FactoryMethod.Product.ApplePhone;
import ca.on.kz.FactoryPattern.FactoryMethod.Product.IphoneProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class AppleFactory implements AbstractFactory{
    @Override
    public IphoneProduct produce() {
        return new ApplePhone();
    }
}
