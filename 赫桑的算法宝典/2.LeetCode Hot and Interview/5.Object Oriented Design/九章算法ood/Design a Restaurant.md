# 预定类

![image-20220518224938468](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518224938468.png)

- 管理类
  - noshare![image-20220526131852876](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220526131852876.png)
  - share![image-20220526132144956](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220526132144956.png)

- 预定类
  - ![image-20220524222833096](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524222833096.png)

- **Clarify**
  - *What* (Party ---> Restaurant ---> Table) (大堂，所有桌子都一样, 暂不考虑人数限制)
    - Restaurant
    - 桌子的规格不一样，能坐的人数区别
    - 吧台里，party的区分
    - 收费？
  - *How* (功能) 
    - 是否能够预约？(没有Reservation)
    - 是否能够送外卖？
    - 每个Order需要区分是Dine-in还是Dine-out
- **Core Object**
  - ![image-20220524224254879](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524224254879.png)
  - Order 为 Receipt 类，存储 Meals、Tables、Party
- **Use Case**
  - 思考
    - Reserve：/
    - Serve：Find table、Take order
      - Restaurant find an available table, an change the table to be unavailable
      - Restaurant takes an order
    - Checkout：checkout
      - Restaurant checks out a table/order, mark the table available again
      - Calculate order price
  - Restaurant
    - Find table
    - Take Order
    - Checkout

- Reservation
  - Search Criteria
    - FindTableForReservation(Timeslot t)
  - 方法一：
    - 返回最近可用的几个时间段
    - `List<Timeslot (Result)> findTableForReservation(Timeslot t)`
  - 方法二：
    - 可以预定：直接进入 confirm
      - `Pair<Table, Timeslot> findTableForReservation(Time slot)`
      - `void confirmReservation(Pair<Table, Timeslot> reservation)`
    - 不能预订：Throw Exception

```java
// K.Z
// enums
public enum Food {
    BEEF,
    PORK,
    LAMB
}

// input
public class Party {
    private int size;

    public Party(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

// Core Objects
// Restaurant
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

// Table
public class Table {
    private boolean available;
    private int availableSeats;

    public Table(boolean available, int availableSeats) {
        this.available = available;
        this.availableSeats = availableSeats;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

// Meal
public class Meal {
    private Food food;
    private Float price;

    public Meal(Food food, Float price) {
        this.food = food;
        this.price = price;
    }

    public Meal(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}

// Order
public class Order {
    private List<Meal> meals;
    private Table table;
    private Party party;

    public Order() {
    }

    public Order(List<Meal> meals, Table table, Party party) {
        this.meals = meals;
        this.table = table;
        this.party = party;
    }

    // get order price
    public float getPrice() {
        float totalPrice = 0f;
        for (Meal meal : meals) {
            totalPrice += meal.getPrice();
        }

        return totalPrice;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                ", table=" + table +
                ", party=" + party +
                '}';
    }
}

// Main
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
```



```java
// 九章算法
class NoTableException extends Exception{

	public NoTableException(Party p)
	{
		super("No table available for party size: " + p.getSize());
	}
}

class Meal {
	private float price;
	
	public Meal(float price)
	{
		this.price = price;
	}
	
	public float getPrice()
	{
		return this.price;
	}
}

class Order {
	private List<Meal> meals;
	
	public Order()
	{
		meals = new ArrayList<Meal>();
	}
	
	public List<Meal> getMeals()
	{
		return meals;
	}
	
	public void mergeOrder(Order order)
	{
		if(order != null)
		{
			for(Meal meal : order.getMeals())
			{
				meals.add(meal);
			}
		}
	}
	
	public float getBill()
	{
		int bill = 0;
		for(Meal meal : meals)
		{
			bill += meal.getPrice();
		}
		return bill;
	}
}

class Party {
	private int size;
	
	public Party(int size)
	{
		this.size = size;
	}
	
	public int getSize()
	{
		return this.size;
	}
}

class Table implements Comparable<Table>{
	private int capacity;
	private boolean available;
	private Order order;
	
	public Table(int capacity)
	{
		this.capacity = capacity;
		available = true;
		order = null;
	}
	
	public int getCapacity()
	{
		return this.capacity;
	}
	
	public boolean isAvailable()
	{
		return this.available;
	}
	
	public void markAvailable()
	{
		this.available = true;
	}
	
	public void markUnavailable()
	{
		this.available = false;
	}
	
	public Order getCurrentOrder()
	{
		return this.order;
	}
	
	public void setOrder(Order o)
	{
		if(order == null)
		{
			this.order = o;
		}
		else 
		{
			if(o != null)
			{
				this.order.mergeOrder(o);
			} else {
				this.order = o;
			}
		}
	}

	@Override
	public int compareTo(Table compareTable) {
		// TODO Auto-generated method stub
		return this.capacity - compareTable.getCapacity();
	}
}

public class Restaurant {
	private List<Table> tables;
	private List<Meal> menu;
	
	public Restaurant()
	{
		tables = new ArrayList<Table>();
		menu = new ArrayList<Meal>();
	}
	
	public void findTable(Party p) throws NoTableException
	{
		for(Table t: tables)
		{
			if(t.isAvailable())
			{
				if(t.getCapacity() >= p.getSize())
				{
					t.markUnavailable();
					return;
				}
			}
		}
		throw new NoTableException(p);
	}
	
	public void takeOrder(Table t, Order o)
	{
		t.setOrder(o);
	}
	
	public float checkOut(Table t)
	{
		float bill = 0;
		if(t.getCurrentOrder() != null)
		{
			bill = t.getCurrentOrder().getBill();
		}

		t.markAvailable();
		t.setOrder(null);
		
		return bill;
	}
	
	public List<Meal> getMenu()
	{
		return menu;
	}
	
	public void addTable(Table t)
	{
		tables.add(t);
		Collections.sort(tables);
	}
	
	public String restaurantDescription()
	{
        // Keep them, don't modify.
		String description = "";
		for(int i = 0; i < tables.size(); i++)
		{
			Table table = tables.get(i);
			description += ("Table: " + i + ", table size: " + table.getCapacity() + ", isAvailable: " + table.isAvailable() + ".");
			if(table.getCurrentOrder() == null)
				description += " No current order for this table"; 
			else
				description +=  " Order price: " + table.getCurrentOrder().getBill();
			
			description += ".\n";
		}
		description += "*****************************************\n";
		return description;
	}
}
```

