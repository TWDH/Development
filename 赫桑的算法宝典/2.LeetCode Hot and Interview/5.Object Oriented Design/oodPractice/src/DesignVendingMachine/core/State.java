package DesignVendingMachine.core;

import DesignVendingMachine.enums.Item;

/**
 * @Author He Zhu
 * @Date 2022-05-25
 * @Version 0.1
 */
public interface State {
    public void selectItem(Item item);

    public void insertMoney(double value);

    public void executeTransaction();

    public double cancelTransaction();

    public String toString();
}
