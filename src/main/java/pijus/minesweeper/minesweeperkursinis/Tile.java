package pijus.minesweeper.minesweeperkursinis;

public class Tile {
    private boolean isMined;
    private boolean isOpened;
    private boolean isFlagged;
    private int adjacentMines;

    public Tile() {
        isMined = false;
        isFlagged = false;
        isOpened = false;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;

    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public boolean isMined() {
        return isMined;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

}
