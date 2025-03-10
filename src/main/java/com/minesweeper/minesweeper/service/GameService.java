package com.minesweeper.minesweeper.service;

import com.minesweeper.minesweeper.Grid;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private Grid grid;

    public GameService() {startNewGame(4, 4);}

    public void startNewGame(int width, int height) {
        this.grid = new Grid(width, height);
    }

    public Grid getGrid() {
        return grid;
    }

    public void revealCell(int x, int y) {
        grid.revealCell(x, y);
    }

    public boolean isGameLost() {
        return grid.isGameLost();
    }

    public boolean isGameOver() {
        return grid.isGameOver();
    }

    public boolean checkWin() {
        return !grid.isGameLost();
    }

    public Object getCell(int x, int y) {
        return grid.getCell(x, y);
    }
}
