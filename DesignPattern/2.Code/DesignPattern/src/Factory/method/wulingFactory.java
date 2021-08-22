package Factory.method;

public class wulingFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
