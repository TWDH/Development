package DesignElevator.input;

import DesignElevator.enums.Direction;

/**
 * @Author He Zhu
 * @Date 2022-05-24
 * @Version 0.1
 */
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
