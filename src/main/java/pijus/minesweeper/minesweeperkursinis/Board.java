package pijus.minesweeper.minesweeperkursinis;

import java.util.Random;

public class Board {
    private Tile[][] tiles;
    private int width;
    private int height;
    private int mineAmount;
    private boolean gameOver;
    private int tilesOpened;
    private int flagsLeft;
    private boolean won;

    public Board(int width, int height, int mineAmount) {
        this.width = width;
        this.height = height;
        this.mineAmount = mineAmount;
        this.flagsLeft = mineAmount;
        this.tilesOpened = 0;
        gameOver = false;
        won = false;
        initializeBoard();
        placeMines();
        countAllAdjacentMines();

    }

    private void initializeBoard() {
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile();
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mineAmount) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (!tiles[x][y].isMined()) {
                tiles[x][y].setMined(true);
                minesPlaced++;
            }
        }
    }

    private void countAllAdjacentMines() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                countAdjacentMines(x, y);
            }
        }
    }

    private void countAdjacentMines(int x, int y) {

        int count = 0;
        for (int differanceOfX = -1; differanceOfX <= 1; differanceOfX++) {
            for (int differanceOfY = -1; differanceOfY <= 1; differanceOfY++) {
                if (differanceOfX == 0 && differanceOfY == 0) continue;
                int tileBeingCheckedX = x + differanceOfX;
                int tileBeingCheckedY = y + differanceOfY;
                if (tileBeingCheckedX >= 0 && tileBeingCheckedX < width && tileBeingCheckedY >= 0 && tileBeingCheckedY < height) {
                    if (tiles[tileBeingCheckedX][tileBeingCheckedY].isMined()) {
                        count++;
                    }
                }
            }
        }
        tiles[x][y].setAdjacentMines(count);
    }

    public void flagTile(int x, int y) {
        if (tiles[x][y].isOpened()) {
            return;
        } else if (tiles[x][y].isFlagged()) {
            removeFlag(x, y);
            return;
        } else if (flagsLeft <= 0) {
            return;
        } else {
            flagsLeft--;
            tiles[x][y].setFlagged(true);
        }

    }

    public void removeFlag(int x, int y) {
        tiles[x][y].setFlagged(false);
        flagsLeft++;
    }


    public void openTile(int x, int y) {
        if (tiles[x][y].isOpened() || tiles[x][y].isFlagged()) {
            return;
        }
        tiles[x][y].setOpened(true);
        tilesOpened++;
        if (tiles[x][y].isMined()) {
            triggerGameOver();
            return;
        } else if (tiles[x][y].getAdjacentMines() == 0) {
            openAdjacentTiles(x, y);
        }
        checkWinCondition();
    }

    private void openAdjacentTiles(int x, int y) {
        for (int differanceOfX = -1; differanceOfX <= 1; differanceOfX++) {
            for (int differanceOfY = -1; differanceOfY <= 1; differanceOfY++) {
                if (differanceOfX == 0 && differanceOfY == 0) continue;
                int tileBeingCheckedX = x + differanceOfX;
                int tileBeingCheckedY = y + differanceOfY;
                if (tileBeingCheckedX >= 0 && tileBeingCheckedX < width && tileBeingCheckedY >= 0 && tileBeingCheckedY < height) {
                    if (!tiles[tileBeingCheckedX][tileBeingCheckedY].isOpened()) {
                        if (tiles[tileBeingCheckedX][tileBeingCheckedY].isFlagged()) {
                            removeFlag(tileBeingCheckedX, tileBeingCheckedY);
                        }
                        openTile(tileBeingCheckedX, tileBeingCheckedY);
                    }
                }
            }
        }
    }

    private void checkWinCondition() {
        if (tilesOpened + mineAmount == (width * height)) {
            won = true;
            gameOver = true;

        }

    }

    public boolean hasWon() {
        return won;
    }

    private void triggerGameOver() {
        gameOver = true;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y].isMined()) {
                    tiles[x][y].setOpened(true);
                }
            }
        }


    }


    public boolean isGameOver() {
        return gameOver;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
}
