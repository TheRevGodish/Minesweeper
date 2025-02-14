package com.minesweeper.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MinesweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);

		Grid grid = new Grid(4, 4);
		Scanner sc = new Scanner(System.in);

		grid.display();

		while (!grid.isGameOver()){
			System.out.print("\nx = ");
			int x = sc.nextInt();
			System.out.print("y = ");
			int y = sc.nextInt();

			grid.revealCell(y - 1, x - 1);
			grid.display();
		}

		if (grid.gameLost) System.out.println("GAME LOST!");
		else System.out.println("GAME WON!");

		grid.displayAll();
	}

}
