package Factory.abstract1;

public class Client {
    public static void main(String[] args) {
        System.out.println("===== 小米系列产品 =====");

        // 小米工厂
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        // 小米手机
        IphoneProduct iphoneProduct = xiaomiFactory.producePhone();
        iphoneProduct.callup();
        iphoneProduct.sendSms();
        // 小米路由器
        IRouterProduct iRouterProduct = xiaomiFactory.produceRouter();
        iRouterProduct.openwifi();
    }
}
