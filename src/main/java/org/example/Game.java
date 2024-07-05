package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Board board;
    private final int numMines;
    private Scanner sc;
    private final int xSize;
    private final int ySize;
    private boolean gameFinished;
    public Game (int xSize, int ySize, int numMines) {
        this.gameFinished = false;
        this.xSize = xSize;
        this.ySize = ySize;
        this.board = new Board(xSize, ySize);;
        this.numMines = numMines;
        this.sc = new Scanner(System.in);
    }

    //Main gameplay loop
    public void loop() {
        //display board before initial selection
        board.renderBoard();
        while (!this.gameFinished) {

            //get tile selected from user
            int inputX;
            int inputY;

            System.out.println("Please select the x coordinate");
            inputX = this.getIntInput(xSize) - 1;

            System.out.println("Please select the Y coordinate");
            inputY = this.ySize - (this.getIntInput(ySize));

            //activate tile
            Coordinate target = new Coordinate(inputX, inputY);
            boolean lost = this.board.activateTileAtCoord(target);

            //show post activation board
            board.renderBoard();

            //calculate loss /win
            if (lost) {
                System.out.println("Oops! You clicked on a bomb!");
                this.gameFinished = true;
            } else {
                boolean won = board.checkFinished();
                if (won) {
                    this.gameFinished = true;
                    System.out.println("Well done, you cleared the minefield!!! :D");
                }
            }
        }
        System.out.println("Would you like to play again? [Y/N]");
        char response = getCharInput("YN");
        if (response == 'Y') {
            this.restart();
        }
    }

    public void setup() {
        try {
            board.regenerateBoard(this.numMines);

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private int getIntInput(int maxValue) {
        boolean isValidInt = false;
        int number = 0;
        while (!isValidInt) {
            String input = sc.next();
            isValidInt = true;
            try {
                number = Integer.parseInt(input);
            } catch (Exception E){
                isValidInt = false;
                System.out.println("Input was not an integer");
            }

            if (isValidInt && number <= 0) {
                System.out.println("Number must be equal to or greater than 1");
                isValidInt = false;
            } else if (isValidInt && number > maxValue) {
                System.out.println("Number too large. Please choose a number that is " + maxValue + " or smaller.");
            }
        }
        return number;
    }

    private char getCharInput(String validInputs) {
        boolean isValidChar = false;
        char character = 'a';
        while (!isValidChar) {
            String input = sc.next().toUpperCase();
            isValidChar = true;
            if (input.length() == 1) {
                character = input.charAt(0);
            } else {
                isValidChar = false;
                System.out.println("Input was not a single character");
            }

            if (isValidChar && validInputs.indexOf(character) == -1) {
                isValidChar = false;
                System.out.println("Input was not valid, please select a valid option.");
            }


        }
        return character;
    }

    private void restart() {
        this.board = new Board(xSize, ySize);
        this.gameFinished = false;
        this.setup();
        this.loop();
    }


}
