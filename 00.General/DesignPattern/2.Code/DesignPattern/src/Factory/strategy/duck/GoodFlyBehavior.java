package Factory.strategy.duck;

public class GoodFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("Good Fly Behavior");
    }
}
