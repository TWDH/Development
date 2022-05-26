package DesignRestaurant.exception;

import DesignRestaurant.input.Party;

/**
 * @Author He Zhu
 * @Date 2022-05-26
 * @Version 0.1
 */
public class NoTableException extends Exception{
    public NoTableException(Party party) {
        super("No table available for this party size: " + party.getSize());
    }
}
