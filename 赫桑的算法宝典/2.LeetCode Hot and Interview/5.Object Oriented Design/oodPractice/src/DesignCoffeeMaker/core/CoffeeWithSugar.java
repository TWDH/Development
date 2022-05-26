package DesignCoffeeMaker.core;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class CoffeeWithSugar extends CoffeeDecorator{

    ICoffee coffee;

    public CoffeeWithSugar(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return coffee.getIngredients() + " + Sugar";
    }
}
