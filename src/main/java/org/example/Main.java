package org.example;
//Emojis used : White square "⬜", black square: "⬛", Bomb: "💣"
public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);;
        try {
            board.initialiseBoard(5);

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
            System.exit(-1);
        }

        board.renderBoard();
        board.activateTileAtCoord(new Coordinate(3,3));
        board.renderBoard();
    }
}