package com.minesweeper.minesweeper;

import java.util.Random;
import static java.lang.Math.round;
import static java.lang.String.valueOf;

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

        AMOUNT_OF_BOMBS = round((float) WIDTH * HEIGHT / 10);
        for (int i = 0; i < AMOUNT_OF_BOMBS; i++) {
            int col = random.nextInt(WIDTH - 1);
            int row = random.nextInt(HEIGHT - 1);
            if (gameGrid[col][row].isBomb()) {i -= 1;}
            else {gameGrid[col][row].plantBomb();}
        }
    }


    public void revealCell(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            System.out.println("out of the grid");
            return;
        }

        if (gameGrid[x][y].isRevealed()) {
            return;
        }

        gameGrid[x][y].reveal();

        if (gameGrid[x][y].isBomb()) {
            gameLost = true;
            return;
        }

        int adjacentBombs = getAdjacentBombs(x, y);
        gameGrid[x][y].setAdjacentBombs(adjacentBombs);
        if (adjacentBombs == 0) {
            revealAdjacentCells(x, y);
        }
    }


    public void revealAdjacentCells(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int dx = x + i;
                int dy = y + j;
                if (dx >= 0 && dx < WIDTH && dy >= 0 && dy < HEIGHT) {
                    if (!gameGrid[dx][dy].isRevealed() && !gameGrid[dx][dy].isBomb()) {
                        revealCell(dx, dy);
                    }
                }
            }
        }
    }


    public int getAdjacentBombs(int x, int y) {
        int bombCount = 0;
        for (int k = x - 1; k <= x + 1; k++) {
            for (int l = y - 1; l <= y + 1; l++) {
                if (k >= 0 && k < WIDTH && l >= 0 && l < HEIGHT) {
                    if (gameGrid[k][l].isBomb()) {
                        bombCount++;
                    }
                }
            }
        }
        return bombCount;
    }


    public void display() {
        System.out.print("   ");
        for (int j = 0; j < WIDTH; j++) {
            System.out.print(j + 1 + "  ");
        }
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(gameGrid[i][j].display() + " ");
            }
            System.out.println();
        }
        /*for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                System.out.print(gameGrid[i][j].display());
            }
            System.out.print("\n");
        }*/
    }


    public void displayAll() {
        System.out.print("   ");
        for (int j = 0; j < WIDTH; j++) {
            System.out.print(j + 1 + "  ");
        }
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(gameGrid[i][j].displayAll() + " ");
            }
            System.out.println();
        }
    }


    public boolean isGameOver() {
        return gameLost || remainingCells() == AMOUNT_OF_BOMBS;
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
}
