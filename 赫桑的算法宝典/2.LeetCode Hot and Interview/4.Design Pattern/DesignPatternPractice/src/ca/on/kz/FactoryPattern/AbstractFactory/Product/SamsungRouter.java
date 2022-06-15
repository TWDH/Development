package ca.on.kz.FactoryPattern.AbstractFactory.Product;

/**
 * @Author He Zhu
 * @Date 2022-06-15
 * @Version 0.1
 */
public class SamsungRouter implements IRouterProduct{
    @Override
    public void start() {
        System.out.println("Samsung Router Start");
    }

    @Override
    public void shutDown() {
        System.out.println("Samsung Router Shut Down");
    }

    @Override
    public void openWifi() {
        System.out.println("Samsung Router Open Wifi");
    }

    @Override
    public void setting() {
        System.out.println("Samsung Router Setting");
    }
}
