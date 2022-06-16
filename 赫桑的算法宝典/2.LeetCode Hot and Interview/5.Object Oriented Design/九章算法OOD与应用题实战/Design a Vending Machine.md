![image-20220518224800442](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518224800442.png)

![image-20220525120858426](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220525120858426.png)

```java
// K.Z
// enum
public enum Item {
    Coke,
    Sprite,
    CanadaDry,
}

// Core
// Vending Machine
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

// Interface
public interface State {
    public void selectItem(Item item);

    public void insertMoney(double value);

    public void executeTransaction();

    public double cancelTransaction();

    public String toString();
}

public abstract class AbstractMachineState implements State{
    protected VendingMachine vendingMachine;

    public AbstractMachineState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}

// states
// NoSelectionState
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

// InsertedMoneyState
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

// HasSelectionState
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
```



```java
// 九章算法
public class VendingMachine {
    // item & money
    private String currentSelectedItem;
    private int currentInsertedMoney;
    
    // Map<itemS>
    private Map<String, Integer> itemPrice;
    
    // state
    private AbstractState state;
    
    private NoSelectionState noSelectionState;
    private HasSelectionState hasSelectionState;
    private InsertedMoneyState insertedMoneyState;

    // Constructor
    public VendingMachine() {
        currentInsertedMoney = 0;
        currentSelectedItem = null;
        
        noSelectionState = new NoSelectionState(this);
        hasSelectionState = new HasSelectionState(this);
        insertedMoneyState = new InsertedMoneyState(this);
        
        state = noSelectionState;

        itemPrice = new HashMap<>();
        itemPrice.put("Coke", 199);
        itemPrice.put("Sprite", 299);
        itemPrice.put("MountainDew", 399);
    }

    // concrete action
    public void setSelectedItem(String item) {
        this.currentSelectedItem = item;
    }

    public String getSelectedItem() {
        return currentSelectedItem;
    }

    public void insertMoney(int amount) {
        this.currentInsertedMoney += amount;
    }
	
    public void emptyInsertedMoney() {
        this.currentInsertedMoney = 0;
    }

    public int getInsertedMoney() {
        return currentInsertedMoney;
    }

    public int getSalePrice() {
        if (currentSelectedItem == null) {
            System.out.println("Please make a selection before asking price");
            return 0;
        } else {
            return itemPrice.get(currentSelectedItem);
        }
    }
    
	// change state
    public void changeToNoSelectionState() {
        state = noSelectionState;
    }

    public void changeToHasSelectionState() {
        state = hasSelectionState;
    }

    public void changeToInsertedMoneyState() {
        state = insertedMoneyState;
    }
    
	// take action in this state ---> Go to concrete action eventully
    public void selectItem(String selection) {
        state.selectItem(selection);
    }

    public void addMoney(int value) {
        state.insertMoney(value);
    }
	
    public void executeTransaction() {
        state.executeTransaction();
    }
	
    public int cancelTransaction() {
        return state.cancelTransaction();
    }
	
    public String printState() {
        String res = "";
		
        res = "Current selection is: " + currentSelectedItem + ", current inserted money: " + currentInsertedMoney
            + ", current state is : " + state;
		
        return res;
    }
}

interface State {
    public void selectItem(String selection);
    public void insertMoney(int value);
    public void executeTransaction();
    public int cancelTransaction();
    public String toString();
}

abstract class AbstractState implements State {
    protected VendingMachine vendingMachine;

    public AbstractState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}

class NoSelectionState extends AbstractState{

    public NoSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String selection) {
        vendingMachine.setSelectedItem(selection);
        vendingMachine.changeToHasSelectionState();
    }

    @Override
    public void insertMoney(int value) {
        System.out.println("Please make a selection first");
    }

    @Override
    public void executeTransaction() {
        System.out.println("Please make a selection first");
    }

    @Override
    public int cancelTransaction() {
        System.out.println("Please make a selection first");
        return 0;
    }

    @Override
    public String toString(){
        return "NoSelection";
    }
}

class HasSelectionState extends AbstractState{

    public HasSelectionState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String selection) {
        vendingMachine.setSelectedItem(selection);
    }

    @Override
    public void insertMoney(int value) {
        vendingMachine.insertMoney(value);
        vendingMachine.changeToInsertedMoneyState();
    }

    @Override
    public void executeTransaction() {
        System.out.println("You need to insert money first");
    }

    @Override
    public int cancelTransaction() {
        System.out.println("Transaction canceled");
        vendingMachine.setSelectedItem(null);
        vendingMachine.changeToNoSelectionState();
        return 0;
    }
    @Override
    public String toString(){
        return "HasSelection";
    }
}

class InsertedMoneyState extends AbstractState{

    public InsertedMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void selectItem(String selection) {
        System.out.println("Already has a selection, please cancel transaction to make a new selection");
    }

    @Override
    public void insertMoney(int value) {
        vendingMachine.insertMoney(value);
    }

    @Override
    public void executeTransaction() {
        // TODO Auto-generated method stub
        int diff = vendingMachine.getInsertedMoney() - vendingMachine.getSalePrice();
        if(diff >= 0){
            System.out.println("Executing transaction, will return you : " + diff + " money and item: " + vendingMachine.getSelectedItem());
            vendingMachine.setSelectedItem(null);
            vendingMachine.emptyInsertedMoney();
            vendingMachine.changeToNoSelectionState();
        }
        else{
            System.out.println("Not enough money, please insert " + (-diff) + " more.");
        }
    }

    @Override
    public int cancelTransaction() {
        // TODO Auto-generated method stub
        int insertedMoney = vendingMachine.getInsertedMoney();
        vendingMachine.setSelectedItem(null);
        vendingMachine.emptyInsertedMoney();
        vendingMachine.changeToNoSelectionState();
        return insertedMoney;
    }

    @Override
    public String toString(){
        return "InsertedMoney";
    }
}
```

