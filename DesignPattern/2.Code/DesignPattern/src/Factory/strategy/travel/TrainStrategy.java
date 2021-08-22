package Factory.strategy.travel;

public class TrainStrategy implements TravelStrategy{
    @Override
    public void travelAlgorithm() {
        System.out.println("火车出行");
    }
}
