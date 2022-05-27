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

    // UP: 就从底向上停
    // DOWN：就从上向底停
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
