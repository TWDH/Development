package DesignVendingMachine;

import DesignVendingMachine.core.VendingMachine;
import DesignVendingMachine.enums.Item;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public class mainVendingMachine {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.selectItem(Item.Coke);
        vendingMachine.addMoney(0.5);
        vendingMachine.addMoney(3.0);
        vendingMachine.executeTransaction();
        System.out.println(vendingMachine);
    }
}
