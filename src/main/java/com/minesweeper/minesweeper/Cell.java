package com.minesweeper.minesweeper;

public class Cell {

    private boolean reveal;
    private boolean bomb;
    private boolean flag;
    private int adjacentBombs;

    public Cell() {
        this.bomb = false;
        this.reveal = false;
        this.flag = false;
    }

    public void reveal() {this.reveal = true;}
    public boolean isRevealed() {return reveal;}

    public void plantBomb() {this.bomb = true;}
    public boolean isBomb() {return bomb;}

    public void setFlag(boolean flag) {this.flag = flag;}
    public boolean isFlag() {return flag;}

    public void setAdjacentBombs(int adjacentBombs) {this.adjacentBombs = adjacentBombs;}
    public int getAdjacentBombs() {return adjacentBombs;}
}
