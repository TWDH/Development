package Factory.strategy.travel;

public class CarStrategy implements TravelStrategy{
    @Override
    public void travelAlgorithm() {
        System.out.println("汽车出行");
    }
}
