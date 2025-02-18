package com.minesweeper.minesweeper;

import java.util.Random;
import static java.lang.Math.round;

public class Grid {

    public final int WIDTH;
    public final int HEIGHT;
    public int AMOUNT_OF_BOMBS;
    private final Cell[][] gameGrid;
    protected boolean gameLost = false;
    private final Random random = new Random();

    public Grid(final int width, final int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        gameGrid = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gameGrid[i][j] = new Cell();
            }
        }

        plantRandomBombs();
        calculateAdjacentBombs();
    }

    public void plantRandomBombs() {
        AMOUNT_OF_BOMBS = round((float) WIDTH * HEIGHT / 10);
        for (int i = 0; i < AMOUNT_OF_BOMBS; i++) {
            int col = random.nextInt(WIDTH - 1);
            int row = random.nextInt(HEIGHT - 1);
            if (gameGrid[col][row].isBomb()) {i -= 1;}
            else {gameGrid[col][row].plantBomb();}
        }
    }

    public void calculateAdjacentBombs() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (!gameGrid[i][j].isBomb()) {
                    int count = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            int newX = i + k;
                            int newY = j + l;
                            if (isValidPosition(newX, newY) && gameGrid[newY][newX].isBomb()) {
                                count++;
                            }
                        }
                    }
                    gameGrid[i][j].setAdjacentBombs(count);
                }
            }
        }
    }

    public void revealCell(int x, int y) {
        if (!isValidPosition(x, y) || gameGrid[x][y].isRevealed()) {return;}

        gameGrid[x][y].reveal();

        if (gameGrid[x][y].isBomb()) {
            gameLost = true;
            return;
        }

        if (gameGrid[x][y].getAdjacentBombs() == 0) {
            revealAdjacentCells(x, y);
        }
    }

    public void revealAdjacentCells(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (isValidPosition(newX, newY)) {
                    if (!gameGrid[newX][newY].isRevealed() && !gameGrid[newX][newY].isBomb()) {
                        revealCell(newX, newY);
                    }
                }
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public int remainingCells() {
        int remainingCell = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (!gameGrid[i][j].isRevealed()) {
                    remainingCell++;
                }
            }
        }
        return remainingCell;
    }

    public boolean isGameOver() {return gameLost || remainingCells() == AMOUNT_OF_BOMBS;}
    public boolean isGameLost() {return gameLost;}
    public Object getWidth() {return WIDTH;}
    public Object getHeight() {return HEIGHT;}
    public Object getAmountOfBombs() {return AMOUNT_OF_BOMBS;}
    public Object getCell(int x, int y) {return gameGrid[x][y];}
    public Object getCells() {return gameGrid;}
}
