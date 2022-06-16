![image-20220518224832595](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518224832595.png)

## 解：

![img](https://lh5.googleusercontent.com/GO0OSO2J7P_2d49Rgh-NQ2KeyLjCxqQefdx9e-JiBK0vKpW3NpDOJ4q1XvYYo4FYJaXRD-oyIZCzG3bfUy03M6JMZNPvJNELCD8LRoKXvPidJs6ga-5gOy0b-ODjssbtz_31-sppE8cM4dai5A)

- **Clarify**

  - *What (关键字 )*
    - Elevator
      - 重量限制 / 当前重量
      - 客梯 / 货梯（抵达的楼层）
    - Building
      - 多处电梯（一个request，多少个电梯响应） -> 本题：每层一处能搭乘，所有电梯均可响应
  - *How (题目主体的规则 )*
    - 判断电梯是否超重？
      - Passenger Class 包含重量
      - 电梯自动感应当前重量（√）
    - 按下按钮，那一台电梯会响应？
      - 同方向 > 静止 > 反向 （Scheduler）（√）
      - 一般负责奇数楼层，一半负责偶数楼层
    - 电梯运行时，那些键可以响应？
      - 是否能按下反方向的楼层

- **Core Object**（以一个 Object 为基础，线性思考；确定 Objects 之间的映射关系）

  - input（request） -> ELEVATORSYSTEM -> output（elevator 某个电梯）

- **Use Case**

  - 利用定义的 Core Object, 列举出 **每个 Object 对应产生的 use case**

  - 每个 use case 只需要先用一句话简单描述

  - > - ElevatorSystem
    >   - handleRequest
    > - Elevator
    >   - Take external request
    >   - Take internal request
    >   - Open gate
    >   - Close gate
    >   - Check weight
    > - ElevatorButton
    >   - PressButton

- **Class** 类图

  - ![image-20220524190158540](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524190158540.png)

- **Correctness**

- Different ways to handle external requests ? (Strategy Pattern)

  - ![image-20220524193429564](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524193429564.png)

  - ```java
    // Main
    class MainApplication {
        ElevatorSystem system = new ElevatorSystem();
        // set pattern
        system.setStrategy(new RandomHandleRequestStrategy());
        
        // Request
        ExternalRequest request = new ExternalRequest(Direction.UP, 3);
        
        // handle external request
        system.handleRequest(request)
    }
    
    
    // Context
    class ElevatorSystem {
        // Strategies
        private HandleRequestStrategy strategy = new HandleRequestStrategy();
        
        // Elevators
        private List<Elevator> elevators = new ArrayList<>();
        
        // Set strategy
        public void setStrategy(HandleRequestStrategy strategy){
            this.strategy = strategy;
        }
        
        public void handleRequest(ExternalRequest request){
            strategy.handleRequest(request, elevator);
        }
    }
    
    // Interface
    interface HandleRequestStrategy {
        public void handleRequest(ExternalRequest request, List<Elevator> elevators);
    }
    
    // Concrete Strategy
    class RandomHandleRequestStrategy implements HandleRequestStrategy{
        public void handleRequest(ExternalRequest request, List<Elevator> elevators){
            Random rand = new Random();
            int n = rand.nextInt(elevators.size());
            
            // choose random elevator handle external request 
            elevators.get(n).handleExternalRequest(request);
        }
    }
    
    // Concrete Strategy
    class AlwaysOneElevatorHandleRequestStrategy implements HandleRequestStrategy{
        public void handleRequest(ExternalRequest request, List<Elevator> elevators){
            // choose one elevator handle external request 
            elevator.get(0).handleExternalRequest(request);
        }
    }
    ```

```java
// K.Z
// * 上升途中不能按反向楼层
// enums
public enum Direction {
    UP,
    DOWN
}

public enum Status {
    UP,
    DOWN,
    IDLE
}

// input
public class Request {
    private int level;

    public Request(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}

public class ExternalRequest extends Request{

    private Direction direction;

    public ExternalRequest(int level, Direction direction) {
        super(level);
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }
}

public class InternalRequest extends Request{
    public InternalRequest(int level) {
        super(level);
    }
}

// Core
public class ElevatorButton {
    private int level;
    private Elevator elevator;

    public ElevatorButton(int level, Elevator elevator) {
        this.level = level;
        this.elevator = elevator;
    }

    public void pressButton() {
        InternalRequest internalRequest = new InternalRequest(level);
        elevator.handleInternalRequest(internalRequest);
    }
}

// 有External下降的请求，一旦决定电梯的大方向是向下的，就不会再接收任何上升的请求。一定会上到顶层再下来
public class Elevator {
    List<ElevatorButton> buttons;
    List<Boolean> upStops;
    List<Boolean> downStops;

    int curLevel;
    Status status;

    boolean gateOpen;
    float weightLimit;

    // constructor
    public Elevator(int n) {
        buttons = new ArrayList<>();
        // 上升状态中，需要停的层数
        upStops = new ArrayList<>();
        // 下降状态中，需要停的层数
        downStops = new ArrayList<>();

        curLevel = 0;
        status = Status.IDLE;

        for (int i = 0; i < n; i++) {
            upStops.add(false);
            downStops.add(false);
        }
    }

    public void insertButton(ElevatorButton elevatorButton) {
        buttons.add(elevatorButton);
    }

    public void handleExternalRequest(ExternalRequest externalRequest) {
        // UP request
        if (externalRequest.getDirection() == Direction.UP) {
            // set(index, element)
            upStops.set(externalRequest.getLevel() - 1, true);
            // Status = UP, 一直是 UP
            // Status = DOWN, 有下降中停靠的站 downStops, 还是会一直下降 DOWN，没有下降的站点，downStop为空时才会变为 UP (不改变方向)
            // 下降过程中才会执行此判断（上升途中不能按反向楼层 downStops）
            if (noRequests(downStops)) {
                status = Status.UP;
            }
        }
        // DOWN request
        else{
            downStops.set(externalRequest.getLevel() - 1, true);
            if (noRequests(upStops)) {
                status = Status.DOWN;
            }
        }
    }

    public void handleInternalRequest(InternalRequest internalRequest) {
        // check valid （当电梯往一个方向移动的时候，电梯内无法按反向的楼层）
        if (status == Status.UP) {
            if (internalRequest.getLevel() >= curLevel + 1) {
                upStops.set(internalRequest.getLevel() - 1, true);
            }
        }
        else if (status == Status.DOWN) {
            if (internalRequest.getLevel() <= curLevel + 1) {
                downStops.set(internalRequest.getLevel() - 1, true);
            }
        }
    }

    public void openGate() {
        if (status == Status.UP) {
            for (int i = 0; i < upStops.size(); i++) {
                int checkLevel = (curLevel + i) % upStops.size();
                // elevator stop at this level
                if (upStops.get(checkLevel)) {
                    curLevel = checkLevel;
                    upStops.set(checkLevel, false);
                    break;
                }
            }
        }
        else if (status == Status.DOWN) {
            for (int i = 0; i < downStops.size(); i++) {
                // 从最顶层开始向下
                int checkLevel = (curLevel - i + downStops.size()) % downStops.size();
                // elevator stop at this level
                if (downStops.get(checkLevel)) {
                    curLevel = checkLevel;
                    downStops.set(checkLevel, false);
                    break;
                }
            }
        }
    }

    public void closeGate() {
        if (status == Status.IDLE) {
            if (noRequests(downStops)) {
                status = Status.UP;
                return;
            }
            if (noRequests(upStops)) {
                status = Status.DOWN;
                return;
            }
        }
        else if (status == Status.UP) {
            if (noRequests(upStops)) {
                if (noRequests(downStops)) {
                    status = Status.IDLE;
                }
                else {
                    status = Status.DOWN;
                }
            }
        }
        else if (status == Status.DOWN) {
            if (noRequests(downStops)) {
                if (noRequests(upStops)) {
                    status = Status.IDLE;
                }
                else{
                    status = Status.UP;
                }
            }
        }
    }

    public boolean noRequests(List<Boolean> stops) {
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i)) {
                return false;
            }
        }
        return true;
    }

    public String elevatorStatusDescription()
    {
        String description = "Currently elevator status is : " + status
                + ".\nCurrent level is at: " + (curLevel + 1)
                + ".\nup stop list looks like: " + upStops
                + ".\ndown stop list looks like:  " + downStops
                + ".\n*****************************************\n";
        return description;
    }
}
```

```
输入:
5
ExternalRequest(3, "Down")
ExternalRequest(1, "Up")
openGate()
closeGate()
openGate()
closeGate()

输出:
Currently elevator status is : DOWN.
Current level is at: 1.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, true, false, false].
*****************************************
Currently elevator status is : DOWN.
Current level is at: 1.
up stop list looks like: [true, false, false, false, false].
down stop list looks like:  [false, false, true, false, false].
*****************************************
Currently elevator status is : DOWN.
Current level is at: 3.
up stop list looks like: [true, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************
Currently elevator status is : UP.
Current level is at: 3.
up stop list looks like: [true, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************
Currently elevator status is : UP.
Current level is at: 1.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************
Currently elevator status is : IDLE.
Current level is at: 1.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
***********************************************************************
样例2

输入:
5         
ExternalRequest(3, "Down")
ExternalRequest(2, "Up")
openGate()
InternalRequest(1)
closeGate()
openGate()
closeGate()
openGate()
closeGate()

输出:
Currently elevator status is : DOWN.
Current level is at: 1.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, true, false, false].
*****************************************

Currently elevator status is : DOWN.
Current level is at: 1.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [false, false, true, false, false].
*****************************************

Currently elevator status is : DOWN.
Current level is at: 3.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************

Currently elevator status is : DOWN.
Current level is at: 3.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [true, false, false, false, false].
*****************************************

Currently elevator status is : DOWN.
Current level is at: 3.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [true, false, false, false, false].
*****************************************

Currently elevator status is : DOWN.
Current level is at: 1.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************

Currently elevator status is : UP.
Current level is at: 1.
up stop list looks like: [false, true, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************

Currently elevator status is : UP.
Current level is at: 2.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************

Currently elevator status is : IDLE.
Current level is at: 2.
up stop list looks like: [false, false, false, false, false].
down stop list looks like:  [false, false, false, false, false].
*****************************************
```



```java
enum Direction {
	UP, DOWN
}

enum Status {
	UP, DOWN, IDLE
}

class Request {
	private int level;
	
	public Request(int l)
	{
		level = l; 
	}
	
	public int getLevel()
	{
		return level;
	}
}

class ElevatorButton {
	private int level;
	private Elevator elevator;
	
	public ElevatorButton(int level, Elevator e)
	{
		this.level = level;
		this.elevator = e;
	}
	
	public void pressButton()
	{
		InternalRequest request = new InternalRequest(level);
		elevator.handleInternalRequest(request);
	}
}

class ExternalRequest extends Request{

	private Direction direction;
	
	public ExternalRequest(int l, Direction d) {
		super(l);
		this.direction = d;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
}

class InternalRequest extends Request{

	public InternalRequest(int l) {
		super(l);
	}
}

public class Elevator {
	
	private List<ElevatorButton> buttons;
	
	private List<Boolean> upStops;
	private List<Boolean> downStops;
	
	private int currLevel;
	private Status status;
	
	public Elevator(int n)
	{
		buttons = new ArrayList<ElevatorButton>();
		upStops = new ArrayList<Boolean>();
		downStops = new ArrayList<Boolean>();
		currLevel = 0;
		status = Status.IDLE;
		
		for(int i = 0; i < n; i++)
		{
			upStops.add(false);
			downStops.add(false);
		}
	}
	
	public void insertButton(ElevatorButton eb)
	{
		buttons.add(eb);
	}
	
	public void handleExternalRequest(ExternalRequest r)
	{
		if(r.getDirection() == Direction.UP)
		{
			upStops.set(r.getLevel() - 1, true);
			if(noRequests(downStops))
			{
				status = Status.UP;
			}
		}
		else 
		{
			downStops.set(r.getLevel() - 1, true);
			if(noRequests(upStops))
			{
				status = Status.DOWN;
			}
		}
	}
	
	public void handleInternalRequest(InternalRequest r)
	{
		// check valid
		if(status == Status.UP)
		{
			if(r.getLevel() >= currLevel + 1)
			{
				upStops.set(r.getLevel() - 1, true);
			}
		}
		else if(status == Status.DOWN)
		{
			if(r.getLevel() <= currLevel + 1)
			{
				downStops.set(r.getLevel() - 1, true);
			}
		}
	}
	
	public void openGate() throws Exception
	{
		if(status == Status.UP)
		{
			for(int i = 0; i < upStops.size(); i++)
			{
				int checkLevel = (currLevel + i) % upStops.size();
				if(upStops.get(checkLevel))
				{
					currLevel = checkLevel;
					upStops.set(checkLevel, false);
					break;
				}
			}
		}
		else if(status == Status.DOWN)
		{
			for(int i = 0; i < downStops.size(); i++)
			{
				int checkLevel = (currLevel + downStops.size() - i) % downStops.size();
				if(downStops.get(checkLevel))
				{
					currLevel = checkLevel;
					downStops.set(checkLevel, false);
					break;
				}
			}
		}
	}
	
	public void closeGate()
	{
		if(status == Status.IDLE)
		{
			if(noRequests(downStops))
			{
				status = Status.UP;
				return;
			}
			if(noRequests(upStops))
			{
				status = Status.DOWN;
				return;
			}
		}
		else if(status == Status.UP)
		{
			if(noRequests(upStops))
			{
				if(noRequests(downStops))
				{
					status = Status.IDLE;
				}
				else status = Status.DOWN;
			}
		}
		else {
			if(noRequests(downStops))
			{
				if(noRequests(upStops))
				{
					status = Status.IDLE;
				}
				else status = Status.UP;
			}
		}
	}
	
	private boolean noRequests(List<Boolean> stops)
	{
		for(int i = 0; i < stops.size(); i++)
		{
			if(stops.get(i))
			{
				return false;
			}
		}
		return true;
	}
	
	public String elevatorStatusDescription()
	{	
		String description = "Currently elevator status is : " + status 
				+ ".\nCurrent level is at: " + (currLevel + 1)
				+ ".\nup stop list looks like: " + upStops
				+ ".\ndown stop list looks like:  " + downStops
				+ ".\n*****************************************\n";
		return description;
	}
}
```

