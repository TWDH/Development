package DesignVendingMachine.core;

import DesignVendingMachine.enums.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */

// VendingMachine ---> State ---> StateAction ---> VendingMachineAction
public class VendingMachine {
    private Item currentSelectedItem;
    private double currentInsertedMoney;

    // Map<Item, price>
    private Map<Item, Double> itemPrice;

    private AbstractMachineState state;

    private NoSelectionState noSelectionState;
    private HasSelectionState hasSelectionState;
    private InsertedMoneyState insertedMoneyState;

    public VendingMachine() {
        currentInsertedMoney = 0;
        currentSelectedItem = null;

        noSelectionState = new NoSelectionState(this);
        hasSelectionState = new HasSelectionState(this);
        insertedMoneyState = new InsertedMoneyState(this);

        state = noSelectionState;

        itemPrice = new HashMap<>();
        itemPrice.put(Item.Coke, 1.99);
        itemPrice.put(Item.Sprite, 2.99);
        itemPrice.put(Item.CanadaDry, 5.99);
    }

    // concrete action
    public void setSelection(Item item) {
        this.currentSelectedItem = item;
    }

    public Item getCurrentSelectedItem() {
        return this.currentSelectedItem;
    }

    public void insertMoney(double amount) {
        this.currentInsertedMoney += amount;
    }

    public double getInsertedMoney() {
        return this.currentInsertedMoney;
    }

    public void emptyInsertedMoney() {
        this.currentInsertedMoney = 0;
    }

    public Double getSalePrice() {
        if (currentSelectedItem == null) {
            System.out.println("Please make a selection before");
            return 0.0;
        }
        else {
            return itemPrice.get(currentSelectedItem);
        }
    }

    public State getCurrentState() {
        return state;
    }

    // change state
    public void changeToNoSelectionState() {
        state = noSelectionState;
    }

    public void changeToHasSelectionState(){
        state = hasSelectionState;
    }

    public void changeToInsertedMoneyState() {
        state = insertedMoneyState;
    }

    // External Object invoke these functions：state dedicated action
    public void selectItem(Item item) {
        state.selectItem(item);
    }

    public void addMoney(double value) {
        state.insertMoney(value);
    }

    public void executeTransaction() {
        state.executeTransaction();
    }

    public double cancelTransaction() {
        return state.cancelTransaction();
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "currentSelectedItem=" + currentSelectedItem +
                ", currentInsertedMoney=" + currentInsertedMoney +
                ", itemPrice=" + itemPrice +
                ", state=" + state +
                ", noSelectionState=" + noSelectionState +
                ", hasSelectionState=" + hasSelectionState +
                ", insertedMoneyState=" + insertedMoneyState +
                '}';
    }
}
