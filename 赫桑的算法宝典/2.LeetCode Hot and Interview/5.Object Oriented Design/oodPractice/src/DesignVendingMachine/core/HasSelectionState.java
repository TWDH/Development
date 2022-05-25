package DesignVendingMachine.core;

import DesignVendingMachine.enums.Item;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class HasSelectionState extends AbstractMachineState{

    public HasSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(Item item) {
        vendingMachine.setSelection(item);
    }

    @Override
    public void insertMoney(double value) {
        vendingMachine.insertMoney(value);
        vendingMachine.changeToInsertedMoneyState();

    }

    @Override
    public void executeTransaction() {
        System.out.println("Please insert Money!");
    }

    @Override
    public double cancelTransaction() {
        vendingMachine.setSelection(null);
        vendingMachine.changeToNoSelectionState();

        System.out.println("Transaction Cancelled!");

        return 0;
    }

    @Override
    public String toString() {
        return "Has Selection State";
    }
}
