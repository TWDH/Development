package Factory.strategy.duck;

public class WildDuck extends Duck{
    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
    }
}
