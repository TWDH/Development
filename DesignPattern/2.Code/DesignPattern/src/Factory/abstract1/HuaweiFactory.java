package Factory.abstract1;

public class HuaweiFactory implements IProductFactory{
    @Override
    public IphoneProduct producePhone() {
        return new HuaweiPhone();
    }

    @Override
    public IRouterProduct produceRouter() {
        return new HuaweiRouter();
    }
}
