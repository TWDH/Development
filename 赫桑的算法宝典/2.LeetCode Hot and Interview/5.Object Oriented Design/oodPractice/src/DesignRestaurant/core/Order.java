package DesignRestaurant.core;

import DesignRestaurant.input.Party;

import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Order {
    private List<Meal> meals;
    private Table table;
    private Party party;

    public Order() {
    }

    public Order(List<Meal> meals, Table table, Party party) {
        this.meals = meals;
        this.table = table;
        this.party = party;
    }

    // get order price
    public float getPrice() {
        float totalPrice = 0f;
        for (Meal meal : meals) {
            totalPrice += meal.getPrice();
        }

        return totalPrice;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                ", table=" + table +
                ", party=" + party +
                '}';
    }
}
