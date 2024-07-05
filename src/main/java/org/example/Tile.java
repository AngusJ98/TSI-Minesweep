package org.example;

public class Tile {
    private final Coordinate coordinate;
    private final String bombStr = "b";
    private final String hiddenStr = "#";
    private final String flagStr = "🚩";
    private boolean isFlagged;
    private boolean isBomb;
    private boolean revealed;
    private int numBombsAdjacent;

    public Tile (int x, int y) {
        this.isFlagged = false;
        this.isBomb = false;
        this.revealed = false;
        this.coordinate = new Coordinate(x, y);
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getNumBombsAdjacent() {
        return numBombsAdjacent;
    }

    public void setNumBombsAdjacent(int numBombsAdjacent) {
        this.numBombsAdjacent = numBombsAdjacent;
    }

    //adds 1 to bomb count, used when board places bombs
    public void incrementBombCount() {
        this.numBombsAdjacent++;
    }

    public String getValue() {
        if(this.revealed) {
            if (this.isBomb) {
                return this.bombStr;
            } else {
                return String.valueOf(this.numBombsAdjacent);
            }
        } else {
            if (this.isFlagged) {
                return this.flagStr;
            } else{
                return this.hiddenStr;
            }
        }
    }
}
