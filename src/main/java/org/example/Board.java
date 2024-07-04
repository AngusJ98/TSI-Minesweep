package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Board {
    private Tile[][] tiles;
    private int xSize;
    private int ySize;

    public Board(int xSize, int ySize) {
        this.tiles = new Tile[ySize][xSize];
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void activateTileAtCoord(Coordinate c) {
        //tile to be activated

        System.out.println("Activating tile at: " + c);
        Tile activeTile = this.getTileAt(c);
        if (activeTile.isRevealed()) {

        } else if (activeTile.isBomb()) {
            activeTile.setRevealed(true);
            System.out.println("You lose");
            //lose
        } else if (activeTile.getNumBombsAdjacent() == 0){
            activeTile.setRevealed(true);
            ArrayList<Coordinate> adjcoords = this.getSurroundingTiles(c);
            for (Coordinate adjCoord : adjcoords) {
                this.activateTileAtCoord(adjCoord);
            }
        } else {
            activeTile.setRevealed(true);
        }
    }

    //public void sweepReveal

    public void initialiseBoard(int numMines) throws ExceptionInInitializerError{
        //If num mines greater than number of spaces, exit early
        if (numMines > this.xSize * this.ySize) {
            throw new ExceptionInInitializerError("Too many bombs for the board");
        }


        //add tile to every square
        for (int i = 0; i < this.ySize; i++) {
            for (int j = 0; j < this.xSize; j++){
                this.tiles[i][j] = new Tile(j, i);
            }
        }



        //randomly generate locations for mines
        HashSet<Coordinate> coordHash = new HashSet<Coordinate>();
        Random random = new Random();
        while (coordHash.size() < numMines) {
            int xRandom = random.nextInt(this.xSize);
            int yRandom = random.nextInt(this.ySize);
            Coordinate newCoord = new Coordinate(xRandom, yRandom);
            coordHash.add(newCoord);
        }

        //set those locations to be mines
        for (Coordinate c : coordHash) {
            int bombX = c.getX();
            int bombY = c.getY();
            this.tiles[bombY][bombX].setBomb(true);
            for (Coordinate c2 : this.getSurroundingTiles(c)) {
                System.out.println("Hi" + c2.getX() + "-" + c2.getY());
                this.getTileAt(c2).incrementBombCount();
            }
        }
        //System.out.println(Arrays.deepToString(this.tiles));

    }

    public void renderBoard() {
        for (int i = 0; i < this.ySize; i++) {
            for (int j = 0; j < this.xSize; j++){

                System.out.printf("%-1s", tiles[i][j].getValue());
            }
            System.out.print("\n");
        }
        System.out.println("\n--------------------------------\n");
    }

    public ArrayList<Coordinate> getSurroundingTiles(Coordinate c) {
        ArrayList<Coordinate> coords = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++){
                Coordinate newCoord = new Coordinate(c.getX() + dx, c.getY() + dy);
                if ((dy != 0 || dx !=0) && this.isValidCoord(newCoord)) {
                    coords.add(newCoord);
                    //System.out.println((x+dx+ "-" + y+dy));
                }
            }
        }
        System.out.println(coords);
        return coords;
    }

    public Tile getTileAt(Coordinate c) {
        return this.tiles[c.getY()][c.getX()];
    }

    private boolean isValidCoord(Coordinate c) {
        return (c.getX() >= 0 && c.getY() >= 0 && c.getX() < this.xSize && c.getY() < this.ySize);
    }
}
