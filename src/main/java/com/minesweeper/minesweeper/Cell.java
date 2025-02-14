package com.minesweeper.minesweeper;

import java.util.Objects;

public class Cell {

    private boolean bomb;
    private boolean reveal;
    private int adjacentBombs;

    public Cell() {
        this.bomb = false;
        this.reveal = false;
    }

    public void reveal() {
        this.reveal = true;
    }

    public boolean isRevealed() {
        return reveal;
    }

    public void plantBomb() {
        this.bomb = true;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

    public String display() {
        if (reveal) {
            if (bomb) {
                return "\uD83D\uDCA3";
            } else {
                return adjacentBombs > 0 ? " " + adjacentBombs + "": "⬛";
            }
        } else {
            return "⬜";
        }
    }

    public String displayAll() {
        if (bomb) {
            return "\uD83D\uDCA3";
        } else {
            return "⬛";
        }
    }
}
