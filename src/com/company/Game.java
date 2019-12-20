package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int activePlayer = 0;
    private ArrayList<Player> playerList;
    private Board board = new Board(3, 3);


    // runs the game and checks for draw/winner and asks for rematch when game is over

    void runGame() {
        playerList = initialize();
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i).getName() + " will be: " + playerList.get(i).getMarker());

        }
        boolean playing = true;
        while (playing == true) {
            playerTurn();

            // runs the checkWin method after each placement and asks for rematch if the game has ended

            if (board.checkForWin(playerList.get(activePlayer).marker)) {
                board.printBoard();
                System.out.println(playerList.get(activePlayer).getName() + " is the winner!");
                playing = false;

            // checks if the board is full of markers and asks for rematch if the game has ended

            } else if (board.checkFull()) {
                board.printBoard();
                System.out.println("The game is a draw!");
                playing = false;
            }
            activePlayer = (activePlayer + 1) % 2;
        }


        reMatch();  // runs reMatch after game has ended
    }

    // handles the placement and checks if position is empty or taken

    private void playerTurn() {

        String playerName = playerList.get(activePlayer).getName();
        char playerMark = playerList.get(activePlayer).getMarker();

        board.printBoard();

        System.out.println("It's " + playerName + "'s turn. Choose an empty spot between 1-9.");
        int selection = correctInput();

        // this while loop checks the spot and places the marker

        boolean check = true;
        while (check == true) {

            board.suggestMarkerPlace(selection);    // checks if spot is empty or not

            if (board.checkSpot()) {    // if spot is empty, put marker there
                board.placeMarker(playerMark);

                break;

            } else {    // if spot is taken, prompted to try a new one
                System.out.println("Spot taken! Try a new one!");
                check = false;
            }
            activePlayer = (activePlayer + 1) % 2;
        }
    }



    // creates the playerList and assigning X and O to player 1 and player 2

    private ArrayList<Player> initialize() {
        ArrayList<Player> playerList = new ArrayList<Player>();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to TicTacToe!");
        System.out.println("The game is about getting three in a row");
        System.out.println("Please enter player names!");
        System.out.println();

        for (int i = 0; i < 2; i++) {
            System.out.println("Name of player " + (i + 1));
            String playerName = input.nextLine();
            char marker = '-';



            if (i == 0) {   // sets X to first player and O to second player
                marker = 'X';
            } else {
                marker = 'O';

            }
            Player player = new Player(playerName, marker);

            playerList.add(player);
        }
        return playerList;

    }

    // asks if the player wants to play again

    private void reMatch() {

        int menuSelection = 0;

        while (menuSelection != 1 && menuSelection != 2) {
            System.out.println("Would you like to play again?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            menuSelection = correctInput();
            if (menuSelection == 1) {
                board.clearBoard();
                runGame();
            }
        }
        if (menuSelection == 2) {
            System.out.println("The game will now end, bye! Thanks for playing!");
        }
    }

    // makes sure that int is used when choosing where to place marker

    public int correctInput() {
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                String userInput = input.next();
                int integer = Integer.parseInt(userInput);
                if (integer <= 9 && integer >= 1 ) {
                    return integer;
                } else {
                    System.out.println("Please enter a number between 1-9");
                }
            } catch (Exception e) {
                System.out.println("Please enter a correct input (number)");
            }
        }
    }
}



