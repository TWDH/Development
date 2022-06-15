package ca.on.kz.FactoryPattern.FactoryMethod.Factory;

import ca.on.kz.FactoryPattern.FactoryMethod.Product.IphoneProduct;
import ca.on.kz.FactoryPattern.FactoryMethod.Product.SamsungPhone;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class SamsungFactory implements AbstractFactory{
    @Override
    public IphoneProduct produce() {
        return new SamsungPhone();
    }
}
