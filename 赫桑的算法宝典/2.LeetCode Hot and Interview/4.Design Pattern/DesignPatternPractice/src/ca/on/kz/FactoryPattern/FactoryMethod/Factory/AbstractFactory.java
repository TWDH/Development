package ca.on.kz.FactoryPattern.FactoryMethod.Factory;

import ca.on.kz.FactoryPattern.FactoryMethod.Product.IphoneProduct;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public interface AbstractFactory {
    public IphoneProduct produce();
}
