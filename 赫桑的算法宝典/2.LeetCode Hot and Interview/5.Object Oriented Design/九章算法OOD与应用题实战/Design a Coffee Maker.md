![image-20220519151440589](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220519151440589.png)

1. **What**
   - input / output：
     1. input：coffee packs
     2. output: Espresso
2. **How**
   - 功能 / 按钮？（开关、加热、定时）
     1. Brew（煮咖啡）
3. **Core Object**
   - ![image-20220519153743415](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220519153743415.png)



```java
// K.Z
//Input
public class CoffeePack {
    private int milkCount;
    private int sugarCount;

    public CoffeePack(int milkCount, int sugarCount) {
        this.milkCount = milkCount;
        this.sugarCount = sugarCount;
    }

    public int getMilkCount() {
        return milkCount;
    }

    public void setMilkCount(int milkCount) {
        this.milkCount = milkCount;
    }

    public int getSugarCount() {
        return sugarCount;
    }

    public void setSugarCount(int sugarCount) {
        this.sugarCount = sugarCount;
    }

    @Override
    public String toString() {
        return "CoffeePack{" +
                "milkCount=" + milkCount +
                ", sugarCount=" + sugarCount +
                '}';
    }
}

// Interface
public interface ICoffee {
    public double getCost();
    public String getIngredients();
}

// Concrete Class (Simple)  ---> interface
public class SimpleCoffee implements ICoffee{
    @Override
    public double getCost() {
        return 1.99;
    }

    @Override
    public String getIngredients() {
        return "Plain Coffee";
    }
}

// Decorator ---> Interface
public abstract class CoffeeDecorator implements ICoffee{
}

// Concrete Decorator
public class CoffeeWithMilk extends CoffeeDecorator{

    ICoffee coffee;

    public CoffeeWithMilk(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 1.5;
    }

    @Override
    public String getIngredients() {
        return coffee.getIngredients() + " + Milk";
    }
}

public class CoffeeWithSugar extends CoffeeDecorator{

    ICoffee coffee;

    public CoffeeWithSugar(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return coffee.getIngredients() + " + Sugar";
    }
}

// Coffee Maker ()
public class CoffeeMaker {
    public ICoffee makeCoffee(CoffeePack pack) {
        ICoffee coffee = new SimpleCoffee();

        // add coffee
        for (int i = 0; i < pack.getMilkCount(); i++) {
            coffee = new CoffeeWithMilk(coffee);
        }

        // add milk
        for (int i = 0; i < pack.getSugarCount(); i++) {
            coffee = new CoffeeWithSugar(coffee);
        }

        return coffee;
    }
}

//Main
public class MainCoffeeMaker {
    public static void main(String[] args) {
        CoffeePack coffeePack = new CoffeePack(1, 2);
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        ICoffee myCoffee = coffeeMaker.makeCoffee(coffeePack);
        System.out.println("Cost: " + myCoffee.getCost());
        System.out.println("Ingredients: " + myCoffee.getIngredients());
    }
}
```



```java
// 九章算法
public class CoffeeMaker {

	public Coffee makeCoffee(CoffeePack pack) {
		Coffee coffee = new SimpleCoffee();

		for (int i = 0; i < pack.getNeededMilk(); i++) {
			coffee = new WithMilk(coffee);
		}

		for (int i = 0; i < pack.getNeededSugar(); i++) {
			coffee = new WithSugar(coffee);
		}

		return coffee;
	}
}

class CoffeePack {
	private int neededMilk;
	private int neededSugar;

	public CoffeePack(int neededMilk, int neededSugar) {
		this.neededMilk = neededMilk;
		this.neededSugar = neededSugar;
	}

	public int getNeededMilk() {
		return neededMilk;
	}

	public int getNeededSugar() {
		return neededSugar;
	}
}

interface Coffee {
	public double getCost();
	public String getIngredients();
}

class SimpleCoffee implements Coffee {

	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getIngredients() {
		// TODO Auto-generated method stub
		return "Plain Coffee";
	}

}

abstract class CoffeeDecorator implements Coffee {
	protected final Coffee decoratedCoffee;

	public CoffeeDecorator(Coffee coffee) {
		this.decoratedCoffee = coffee;
	}

	public double getCost() {
		return decoratedCoffee.getCost();
	}

	public String getIngredients() {
		return decoratedCoffee.getIngredients();
	}
}

class WithMilk extends CoffeeDecorator {

	public WithMilk(Coffee coffee) {
		super(coffee);
	}

	public double getCost() {
		return super.getCost() + 0.5;
	}

	public String getIngredients() {
		return super.getIngredients() + ", Milk";
	}
}

class WithSugar extends CoffeeDecorator {

	public WithSugar(Coffee coffee) {
		super(coffee);
	}

	public double getCost() {
		return super.getCost() + 0.5;
	}

	public String getIngredients() {
		return super.getIngredients() + ", Sugar";
	}
}
```

