package ca.on.kz.FactoryPattern.AbstractFactory.Product;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public interface IRouterProduct {
    void start();

    void shutDown();

    void openWifi();

    void setting();
}
