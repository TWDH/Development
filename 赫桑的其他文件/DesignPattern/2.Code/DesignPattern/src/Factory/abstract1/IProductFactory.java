package Factory.abstract1;

// 抽象产品工厂
public interface IProductFactory {
    // 生产手机
    public IphoneProduct producePhone();

    // 生产路由器
    public IRouterProduct produceRouter();
}
