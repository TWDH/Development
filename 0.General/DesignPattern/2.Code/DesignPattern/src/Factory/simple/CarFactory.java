package Factory.simple;

// 静态工厂模式
// 不满足开闭原则
public class CarFactory {
    // 方法一
    public static Car getCar(String car) {
        if ("wuling".equals(car)) {
            return new WuLing();
        } else if ("tesla".equals(car)) {
            return new Tesla();
        } else {
            return null;
        }
    }

    // 方法二
    public static Car getWuling() {
        return new WuLing();
    }

    public static Car getTesla() {
        return new Tesla();
    }
}
