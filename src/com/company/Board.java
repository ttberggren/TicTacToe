package com.company;

public class Board extends Player {
    private int currentRows;
    private int currentCols;
    private char[][] board;

    // constructor

    public Board(int row, int col) {
        this.board = new char[row][col];
        clearBoard();

    }

    // clears the board from placed markers

    public void clearBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = '-';
            }
        }
    }

    // method to print the board


    public void printBoard() {

        //Print out a top border
        for (int a = 0; a < board.length; a++) {
            System.out.print("----");
        }
        System.out.print("-");
        System.out.println();

        //Prints out the board
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " | ");
            }

            // bottom  border
            System.out.println();
            for (int k = 0; k < board.length; k++) {
                System.out.print("----");
            }
            System.out.print("-");
            System.out.println();

        }
    }

    public char getMarker() {
        return this.marker;
    }

    // checks if the spot is available

    public boolean checkSpot() {
        boolean available = false;
        if (this.board[currentRows][currentCols] == '-') {
            available = true;
        }
        return available;
    }

    // translate column and rows to integer between 1-9

    public void suggestMarkerPlace(int input) {
        inputToColumn(input);
        inputToRows(input);
    }

    // places the marker at selected spot

    public void placeMarker(char marker) {
        this.board[currentRows][currentCols] = marker;
    }

    private void inputToColumn(int input) {
        if (input == 1 || input == 4 || input == 7) {
            this.currentCols = 0;
        } else if (input == 2 || input == 5 || input == 8) {
            this.currentCols = 1;
        } else if (input == 3 || input == 6 || input == 9) {
            this.currentCols = 2;
        }

    }

    private void inputToRows(int input) {
        if (input == 1 || input == 2 || input == 3) {
            this.currentRows = 2;
        } else if (input == 4 || input == 5 || input == 6) {
            this.currentRows = 1;
        } else if (input == 7 || input == 8 || input == 9) {
            this.currentRows = 0;
        }

    }

    // checks if the board is full for the draw method

    public boolean checkFull() {
        boolean full = true;
        for (char[] chars : board) {
            for (int j = 0; j < 3; j++) {
                if (chars[j] == '-') {
                    full = false;

                }
            }
        }
        return full;
    }

    public boolean checkForWin(char playerMark) {
        boolean win = false;
        for (int i = 0; i < 3; i++) {

            // Check the rows for a win

            if (this.board[i][0] == playerMark && this.board[i][1] == playerMark && this.board[i][2] == playerMark) {
                win = true;
            }
            // Check the columns for a win

            else if (this.board[0][i] == playerMark && this.board[1][i] == playerMark && this.board[2][i] == playerMark) {
                win = true;
            }
        }
        // Check the diagonals for a win

        if ((this.board[0][0] == playerMark) && this.board[1][1] == playerMark && (this.board[2][2] == playerMark)) {
            win = true;
        } else if (this.board[0][2] == playerMark && this.board[1][1] == playerMark && this.board[2][0] == playerMark) {
            win = true;
        }

        return win;
    }
}