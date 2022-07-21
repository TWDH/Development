package Factory.strategy.travel;

public class AirStrategy implements TravelStrategy{
    @Override
    public void travelAlgorithm() {
        System.out.println("飞机出行");
    }
}
