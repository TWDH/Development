package SOLID.DIP;

public class SuperWorker implements IWorker{
    @Override
    public void work() {
        System.out.println("super worker...");
    }
}
