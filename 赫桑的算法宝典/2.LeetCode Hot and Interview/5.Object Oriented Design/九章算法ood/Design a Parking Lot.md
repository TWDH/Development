# 管理类 (mid)

- Gym
- Parking lot
- Restaurant
- Library
- Super market
- Hotel

![image-20220518225021187](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220518225021187.png)

![image-20220524205659180](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524205659180.png)

1. `parkVehicle(Vehicle v)`
   1. `- List<Spot> findSpotsForVehicle(Vehicle v)` 
   2. `Spot.takeSpot()`

- **Clarify**
  - *What* (Vehicle ---> ParkingLot ---> ParkingSpot)
    - Vehicle (type of vehicles)
      - [Bus, sedan, motorCycle]
    - Parking lot  (multiple layers) 
      - [parking lot ---> parking level ---> parking spaces]
    - ParkingSpots (multiple types)
      - no disable / no electrical spot
  - *How*
    - 停车场有哪些规则、功能？
      - 规则一：停车规则（根据车大小，横向停车）
      - 规则二：停车场能够显示空闲位置的个数
      - 规则三：收费 / 免费？ （根据时间收费）
- **Core Object**
  - input (vehicles) ---> PARKINGLOT ---> output (parking spot)
  - <img src="https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524210909548.png" alt="image-20220524210909548" style="zoom:50%;" />
    - 问题一：ParkingLot 不需要知道任何 Car 类型的信息
    - 问题二：给 ParkingLot 引入多余的依赖 Car 类型
    - 问题三：ParkingLot静态，Car动态；动态类会不断修改静态类
    - ParkingLot 和 Car 也是一种动态关系（图书馆 User <---> Book）, *采用 Receipt 思想，Ticket类*
  - <img src="https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524211355527.png" alt="image-20220524211355527" style="zoom:50%;" />
    - ParkingLot 和 ParkingSpot 都是静态的类， ParkingLot一定依赖 ParkingSpot
- **Use Case** (管理类)
  - 思考
    - Reserve (提前预定) (×)
    - Serve (过程, 提供的服务) (Park Vehicle + Get available count)
    - Checkout (离开) (Clear spot + Calculate price)
  - ParkingLot
    - Get available count
    - Park Vehicle
      - Parking lot checks the size of vehicle
      - Parking lot find an available spot for this vehicle
      - Vehicle takes the spot
    - Clear spot
    - Calculate price
- **Class**
  - 小技巧：*收据*
    - 图书馆 (User <---> Book)
    - ticket ( `-User` ;  `-List<Book>`)
- **Correctness**
  - validate use case
  - follow good practice (modifier, exception)
  - SOLID
  - Design Pattern

```java
// enum type for Vehicle
enum VehicleSize {
	Motorcycle,
	Compact,
	Large,
}

//abstract Vehicle class
abstract class Vehicle {
    
	protected int spotsNeeded;
	protected VehicleSize size;
	protected String licensePlate;  // id for a vehicle

	protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>(); // id for parking where may occupy multi

	public int getSpotsNeeded() {
		return spotsNeeded;
	}
	
	public VehicleSize getSize() {
		return size;
	}

	/* Park vehicle in this spot (among others, potentially) */
	public void parkInSpot(ParkingSpot spot) {
		parkingSpots.add(spot);
	}
	
	/* Remove car from spot, and notify spot that it's gone */
	public void clearSpots() {
		for (int i = 0; i < parkingSpots.size(); i++) {
			parkingSpots.get(i).removeVehicle();
		}
		parkingSpots.clear();
	}
	//this need to be implement in subclass
	public abstract boolean canFitInSpot(ParkingSpot spot);
    public abstract void print(); 
}

class Motorcycle extends Vehicle {
    // Write your code here
	public Motorcycle() {
		spotsNeeded = 1;
		size = VehicleSize.Motorcycle;
	}
	
	public boolean canFitInSpot(ParkingSpot spot) {
		return true;
	}
    
    public void print() {  
        System.out.print("M");  
    }
}

class Car extends Vehicle {
    // Write your code here
	public Car() {
		spotsNeeded = 1;
		size = VehicleSize.Compact;
	}
	
	public boolean canFitInSpot(ParkingSpot spot) {
		return spot.getSize() == VehicleSize.Large || spot.getSize() == VehicleSize.Compact;
	}

    public void print() {  
        System.out.print("C");  
    } 
}

class Bus extends Vehicle {
    // Write your code here
	public Bus() {
		spotsNeeded = 5;
		size = VehicleSize.Large;
	}

	public boolean canFitInSpot(ParkingSpot spot) {
		return spot.getSize() == VehicleSize.Large;
	}

    public void print() {  
        System.out.print("B");  
    } 
}

class ParkingSpot {
    // Write your code here
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;

	public ParkingSpot(Level lvl, int r, int n, VehicleSize sz) {
		level = lvl;
		row = r;
		spotNumber = n;
		spotSize = sz;
	}
	
	public boolean isAvailable() {
		return vehicle == null;
	}
	/* Checks if the spot is big enough for the vehicle (and is available). This compares
	 * the SIZE only. It does not check if it has enough spots. */
	public boolean canFitVehicle(Vehicle vehicle) {
		return isAvailable() && vehicle.canFitInSpot(this);
	}
	/* Park vehicle in this spot. */
	public boolean park(Vehicle v) {
		if (!canFitVehicle(v)) {
			return false;
		}
		vehicle = v;
		vehicle.parkInSpot(this);
		return true;
	}
	/* Remove vehicle from spot, and notify level that a new spot is available */
	public void removeVehicle() {
		level.spotFreed();
		vehicle = null;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}
	
	public VehicleSize getSize() {
		return spotSize;
	}

    public void print() {  
        if (vehicle == null) {  
            if (spotSize == VehicleSize.Compact) {  
                System.out.print("c");  
            } else if (spotSize == VehicleSize.Large) {  
                System.out.print("l");  
            } else if (spotSize == VehicleSize.Motorcycle) {  
                System.out.print("m");  
            }  
        } else {  
            vehicle.print();  
        }  
    }
}

/* Represents a level in a parking garage */
class Level {
    // Write your code here
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0; // number of free spots
	private int SPOTS_PER_ROW;


	public Level(int flr, int num_rows, int spots_per_row) {
		floor = flr;
        int SPOTS_PER_ROW = spots_per_row;
        int numberSpots  = 0;
		spots = new ParkingSpot[num_rows * spots_per_row];

		//init size for each spot in array spots
        for (int row = 0; row < num_rows; ++row) {
            for (int spot = 0; spot < spots_per_row / 4; ++spot) {
                VehicleSize sz = VehicleSize.Motorcycle;
                spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
                numberSpots ++;
            }
            for (int spot = spots_per_row / 4; spot < spots_per_row / 4 * 3; ++spot) {
                VehicleSize sz = VehicleSize.Compact;
                spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
                numberSpots ++;
            }
            for (int spot = spots_per_row / 4 * 3; spot < spots_per_row; ++spot) {
                VehicleSize sz = VehicleSize.Large;
                spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
                numberSpots ++;
            }
        }

        availableSpots = numberSpots;
	}

	/* Try to find a place to park this vehicle. Return false if failed. */
	public boolean parkVehicle(Vehicle vehicle) {
		if (availableSpots() < vehicle.getSpotsNeeded()) {
			return false; // no enough spots
		}
		int spotNumber = findAvailableSpots(vehicle);
		if(spotNumber < 0) {
			return false;
		}
		return parkStartingAtSpot(spotNumber, vehicle);
	}

	/* find a spot to park this vehicle. Return index of spot, or -1 on failure. */
	private int findAvailableSpots(Vehicle vehicle) {
		int spotsNeeded = vehicle.getSpotsNeeded();
		int lastRow = -1;
		int spotsFound = 0;

		for(int i = 0; i < spots.length; i++){
			ParkingSpot spot = spots[i];
			if(lastRow != spot.getRow()){
				spotsFound = 0;
				lastRow = spot.getRow();
			}
			if(spot.canFitVehicle(vehicle)){
				spotsFound++;
			}else{
				spotsFound = 0;
			}
			if(spotsFound == spotsNeeded){
				return i - (spotsNeeded - 1); // index of spot
			}
		}
		return -1;
	}

	/* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */
	private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
		vehicle.clearSpots();

		boolean success = true;
		
		for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
			 success &= spots[i].park(vehicle);
		}
		
		availableSpots -= vehicle.spotsNeeded;
		return success;
	}

	/* When a car was removed from the spot, increment availableSpots */
	public void spotFreed() {
		availableSpots++;
	}

	public int availableSpots() {
		return availableSpots;
	}

    public void print() {  
        int lastRow = -1;  
        for (int i = 0; i < spots.length; i++) {  
            ParkingSpot spot = spots[i];  
            if (spot.getRow() != lastRow) {  
                System.out.print("  ");  
                lastRow = spot.getRow();  
            }  
            spot.print();  
        }  
    }
}

public class ParkingLot {
	private Level[] levels;
	private int NUM_LEVELS;
	
    // @param n number of leves
    // @param num_rows  each level has num_rows rows of spots
    // @param spots_per_row each row has spots_per_row spots
	public ParkingLot(int n, int num_rows, int spots_per_row) {
        // Write your code here
        NUM_LEVELS = n;
		levels = new Level[NUM_LEVELS];
		for (int i = 0; i < NUM_LEVELS; i++) {
			levels[i] = new Level(i, num_rows, spots_per_row);
		}
	}

	// Park the vehicle in a spot (or multiple spots)
    // Return false if failed
	public boolean parkVehicle(Vehicle vehicle) {
        // Write your code here
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].parkVehicle(vehicle)) {
				return true;
			}
		}
		return false;
	}

    // unPark the vehicle
	public void unParkVehicle(Vehicle vehicle) {
        // Write your code here
		vehicle.clearSpots();
	}

    public void print() {  
        for (int i = 0; i < levels.length; i++) {  
            System.out.print("Level" + i + ": ");  
            levels[i].print();
            System.out.println("");  
        }  
        System.out.println("");  
    } 
}
```

![image-20220524220835144](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524220835144.png)

![image-20220524221233835](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524221233835.png)