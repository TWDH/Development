package DesignElevator.core;

import DesignElevator.input.InternalRequest;

/**
 * @Author He Zhu
 * @Date 2022-05-24
 * @Version 0.1
 */
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
