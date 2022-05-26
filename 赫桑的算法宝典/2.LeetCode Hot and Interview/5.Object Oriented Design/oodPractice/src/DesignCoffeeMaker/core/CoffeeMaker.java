package DesignCoffeeMaker.core;

import DesignCoffeeMaker.input.CoffeePack;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class CoffeeMaker {

    public ICoffee makeCoffee(CoffeePack pack) {
        ICoffee coffee = new SimpleCoffee();

        // add coffee
        for (int i = 0; i < pack.getMilkCount(); i++) {
            coffee = new CoffeeWithMilk(coffee);
        }

        // add milk
        for (int i = 0; i < pack.getSugarCount(); i++) {
            coffee = new CoffeeWithSugar(coffee);
        }

        return coffee;
    }
}
