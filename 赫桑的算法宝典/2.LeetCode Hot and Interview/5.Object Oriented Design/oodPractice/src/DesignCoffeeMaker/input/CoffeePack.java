package DesignCoffeeMaker.input;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class CoffeePack {
    private int milkCount;
    private int sugarCount;

    public CoffeePack(int milkCount, int sugarCount) {
        this.milkCount = milkCount;
        this.sugarCount = sugarCount;
    }

    public int getMilkCount() {
        return milkCount;
    }

    public void setMilkCount(int milkCount) {
        this.milkCount = milkCount;
    }

    public int getSugarCount() {
        return sugarCount;
    }

    public void setSugarCount(int sugarCount) {
        this.sugarCount = sugarCount;
    }

    @Override
    public String toString() {
        return "CoffeePack{" +
                "milkCount=" + milkCount +
                ", sugarCount=" + sugarCount +
                '}';
    }
}
