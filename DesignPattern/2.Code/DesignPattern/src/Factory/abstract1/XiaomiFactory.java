package Factory.abstract1;

public class XiaomiFactory implements IProductFactory{
    @Override
    public IphoneProduct producePhone() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct produceRouter() {
        return new XiaoMiRouter();
    }
}
