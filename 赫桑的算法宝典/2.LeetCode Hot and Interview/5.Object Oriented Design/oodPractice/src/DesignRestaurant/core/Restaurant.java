package DesignRestaurant.core;

import DesignRestaurant.exception.NoTableException;
import DesignRestaurant.input.Party;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class Restaurant {
    List<Table> tables;
    List<Meal> menu;
    List<Order> orders;

    public Restaurant(int numTables, List<Meal> menus) {
        tables = new ArrayList<>();
        menu = new ArrayList<>();
        orders = new ArrayList<>();

        // init tables
        for (int i = 0; i < numTables; i++) {
            Table table = new Table(true, 5);
            tables.add(table);
        }

        // set menu
        menu.addAll(menus);
    }

    // Find table
    public Table findTable(Party party) throws NoTableException {
        int requiredSize = party.getSize();
        for (Table table : tables) {
            if (table.getAvailableSeats() >= requiredSize && table.isAvailable()) {
                table.setAvailable(false);
                System.out.println("Table found, booked!");
                return table;
            }
        }

        throw new NoTableException(party);
    }

    // Take order
    public Order takeOrder(Party party, Table table, List<Meal> meals) {
        Order order = new Order(meals, table, party);
        orders.add(order);

        return order;
    }

    // checkout
    public float checkout(Order order) {
        Table table = order.getTable();

        // calculate total price you need pay
        float price = order.getPrice();

        // set table available
        table.setAvailable(true);

        // remove order detail from existing orders
        orders.remove(order);

        System.out.println("Please pay: $" + price);

        return price;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void setMenu(List<Meal> menu) {
        this.menu = menu;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "tables=" + tables +
                ", menu=" + menu +
                ", orders=" + orders +
                '}';
    }
}
