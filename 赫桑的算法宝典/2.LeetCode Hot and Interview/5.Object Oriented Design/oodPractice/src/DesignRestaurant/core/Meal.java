package DesignRestaurant.core;

import DesignRestaurant.enums.Food;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Meal {
    private Food food;
    private Float price;

    public Meal(Food food, Float price) {
        this.food = food;
        this.price = price;
    }

    public Meal(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
