package DesignElevator.core;

import DesignElevator.enums.Direction;
import DesignElevator.enums.Status;
import DesignElevator.input.ExternalRequest;
import DesignElevator.input.InternalRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author He Zhu
 * @Date 2022-05-24
 * @Version 0.1
 */
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
        // 上升状态中，在某层停
        upStops = new ArrayList<>();
        // 下降状态中，在某层停
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
        // check valid
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
