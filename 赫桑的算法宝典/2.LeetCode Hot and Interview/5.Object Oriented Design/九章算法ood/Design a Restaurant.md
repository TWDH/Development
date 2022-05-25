# 预定类

![image-20220518224938468](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518224938468.png)

![image-20220524222833096](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524222833096.png)

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
    - Checkout：checkout
  - Restaurant
    - Find table
    - Take Order
    - Checkout





```java
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

