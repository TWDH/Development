package SOLID.DIP;

public class Manager {
    IWorker iworker;

    public void setIworker(IWorker worker) {
        this.iworker = worker;
    }

    public void manage() {
        this.iworker.work();
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        Worker worker = new Worker();
        SuperWorker superWorker = new SuperWorker();
        manager.setIworker(worker);
        manager.manage();
        manager.setIworker(superWorker);
        manager.manage();
    }
}
