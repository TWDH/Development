package ca.on.kz.FactoryPattern.SimpleFactory.Factory;

import ca.on.kz.FactoryPattern.SimpleFactory.Product.ApplePhone;
import ca.on.kz.FactoryPattern.SimpleFactory.Product.IphoneProduct;
import ca.on.kz.FactoryPattern.SimpleFactory.Product.SamsungPhone;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class PhoneFactory {
    public IphoneProduct produce(String brand) {
        // S.O.L.I.D ?
        if ("apple".equals(brand)) {
            return new ApplePhone();
        }
        else if ("samsung".equals(brand)) {
            return new SamsungPhone();
        }

        System.out.println("Please type the correct info");
        return null;
    }
}
