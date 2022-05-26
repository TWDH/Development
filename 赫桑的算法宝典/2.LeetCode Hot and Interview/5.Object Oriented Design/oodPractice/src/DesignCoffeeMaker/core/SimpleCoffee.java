package DesignCoffeeMaker.core;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class SimpleCoffee implements ICoffee{
    @Override
    public double getCost() {
        return 1.99;
    }

    @Override
    public String getIngredients() {
        return "Plain Coffee";
    }
}
