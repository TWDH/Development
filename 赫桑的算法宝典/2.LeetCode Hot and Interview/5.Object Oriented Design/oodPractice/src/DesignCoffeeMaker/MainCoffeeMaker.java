package DesignCoffeeMaker;

import DesignCoffeeMaker.core.CoffeeMaker;
import DesignCoffeeMaker.core.ICoffee;
import DesignCoffeeMaker.input.CoffeePack;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class MainCoffeeMaker {
    public static void main(String[] args) {
        CoffeePack coffeePack = new CoffeePack(1, 2);
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        ICoffee myCoffee = coffeeMaker.makeCoffee(coffeePack);
        System.out.println("Cost: " + myCoffee.getCost());
        System.out.println("Ingredients: " + myCoffee.getIngredients());
    }
}
