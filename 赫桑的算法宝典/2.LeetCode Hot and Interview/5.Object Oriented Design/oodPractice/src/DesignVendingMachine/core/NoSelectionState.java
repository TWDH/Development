package DesignVendingMachine.core;

import DesignVendingMachine.enums.Item;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class NoSelectionState extends AbstractMachineState{

    public NoSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(Item item) {
        vendingMachine.setSelection(item);
        vendingMachine.changeToHasSelectionState();
        System.out.println("Selected a Item!" + item);
    }

    @Override
    public void insertMoney(double value) {
        System.out.println("Please make a selection first!");
    }

    @Override
    public void executeTransaction() {
        System.out.println("Please make a selection first!");
    }

    @Override
    public double cancelTransaction() {
        System.out.println("Please make a selection first!");
        return 0;
    }

    @Override
    public String toString() {
        return "NO Selection";
    }
}
