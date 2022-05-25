package DesignVendingMachine.core;

import DesignVendingMachine.enums.Item;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class InsertedMoneyState extends AbstractMachineState{

    public InsertedMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(Item item) {
        System.out.println("Already has a selection, please cancel to make a new selection!");
    }

    @Override
    public void insertMoney(double value) {
        vendingMachine.insertMoney(value);
    }

    @Override
    public void executeTransaction() {
        double diff = vendingMachine.getInsertedMoney() - vendingMachine.getSalePrice();

        if (diff >= 0) {
            System.out.println("Executing transaction, will return you: " + diff + " money and item: " + vendingMachine.getCurrentSelectedItem());

            vendingMachine.setSelection(null);
            vendingMachine.emptyInsertedMoney();
            vendingMachine.changeToNoSelectionState();
        }
        else{
            System.out.println("Not enough money!");
        }
    }

    @Override
    public double cancelTransaction() {
        double insertedMoney = vendingMachine.getInsertedMoney();
        vendingMachine.setSelection(null);
        vendingMachine.changeToNoSelectionState();
        vendingMachine.emptyInsertedMoney();

        return insertedMoney;
    }

    @Override
    public String toString() {
        return "Inserted Money State";
    }
}
