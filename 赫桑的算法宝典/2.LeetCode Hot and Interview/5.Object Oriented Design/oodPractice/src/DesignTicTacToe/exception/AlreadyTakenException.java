package DesignTicTacToe.exception;

public class AlreadyTakenException extends Exception {
    public AlreadyTakenException() {
        super("Already taken!");
    }
}
