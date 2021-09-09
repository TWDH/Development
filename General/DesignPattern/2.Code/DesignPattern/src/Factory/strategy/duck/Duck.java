package Factory.strategy.duck;

public abstract class Duck {

    FlyBehavior flyBehavior;

    public void fly() {
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }
}
