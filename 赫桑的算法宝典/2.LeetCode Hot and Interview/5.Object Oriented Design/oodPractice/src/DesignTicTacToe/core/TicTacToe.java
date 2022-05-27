package DesignTicTacToe.core;

import DesignTicTacToe.exception.AlreadyTakenException;
import DesignTicTacToe.exception.GameEndException;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameEnd;

    public TicTacToe() {
        board = new char[3][3];
        initialize();
    }

    public void initialize() {
        gameEnd = false;
        currentPlayer = 'x';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Use Case
    public void changePlayer() {
        if (currentPlayer == 'x') {
            currentPlayer = 'o';
        }
        else {
            currentPlayer = 'x';
        }
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        gameEnd = true;
        return true;
    }

    public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {

        if (gameEnd) {
            throw new GameEndException();
        }

        if (board[row][col] != '-') {
            throw new AlreadyTakenException();
        }

        // make move
        board[row][col] = currentPlayer;

        boolean win;

        // Tic Tac Toe
        //check row
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != currentPlayer) {
                win = false;
                break;
            }
        }

        if (win) {
            gameEnd = true;
            return win;
        }

        //check column
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != currentPlayer) {
                win = false;
                break;
            }
        }

        if (win) {
            gameEnd = true;
            return win;
        }

        //check back diagonal
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != currentPlayer) {
                win = false;
                break;
            }
        }

        if (win) {
            gameEnd = true;
            return win;
        }

        //check forward diagonal
        win = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - i - 1] != currentPlayer) {
                win = false;
                break;
            }
        }

        if (win) {
            gameEnd = true;
            return win;
        }
        changePlayer();
        return win;
    }
}
