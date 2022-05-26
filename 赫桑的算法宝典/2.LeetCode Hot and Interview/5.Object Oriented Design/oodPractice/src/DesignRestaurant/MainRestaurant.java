package DesignRestaurant;

import DesignRestaurant.core.Meal;
import DesignRestaurant.core.Order;
import DesignRestaurant.core.Restaurant;
import DesignRestaurant.core.Table;
import DesignRestaurant.enums.Food;
import DesignRestaurant.exception.NoTableException;
import DesignRestaurant.input.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class MainRestaurant {
    public static void main(String[] args) throws NoTableException {
        // init menu
        Meal meal1 = new Meal(Food.BEEF, 29.9f);
        Meal meal2 = new Meal(Food.LAMB, 19.9f);
        Meal meal3 = new Meal(Food.PORK, 9.9f);

        List<Meal> menu = new ArrayList<>();
        menu.add(meal1);
        menu.add(meal2);
        menu.add(meal3);

        // init restaurant
        Restaurant restaurant = new Restaurant(10, menu);

        // init party
        Party party = new Party(5);

        // find table
        Table table = restaurant.findTable(party);

        // take order
        List<Meal> dinner = new ArrayList<>();
        dinner.add(meal1);
        dinner.add(meal2);

        Order order = restaurant.takeOrder(party, table, dinner);

        // pay bill
        restaurant.checkout(order);

    }
}
