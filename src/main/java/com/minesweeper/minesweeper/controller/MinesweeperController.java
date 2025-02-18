package com.minesweeper.minesweeper.controller;

import com.minesweeper.minesweeper.Grid;
import com.minesweeper.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/minesweeper")
@CrossOrigin(origins = "http://localhost:4200")
public class MinesweeperController {

    private final GameService gameService;

    @Autowired
    public MinesweeperController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public String startNewGame(@RequestParam(defaultValue = "10") int width,
                               @RequestParam(defaultValue = "10") int height) {
        gameService.startNewGame(width, height);
        return "New game started with grid size: " + width + "x" + height;
    }

    @GetMapping("/grid")
    public ResponseEntity<Map<String, Object>> getGrid() {
        Map<String, Object> response = new HashMap<>();
        Grid grid = gameService.getGrid();

        response.put("WIDTH", grid.getWidth());
        response.put("HEIGHT", grid.getHeight());
        response.put("AMOUNT_OF_BOMBS", grid.getAmountOfBombs());
        response.put("gameLost", grid.isGameLost());
        response.put("gameOver", grid.isGameOver());
        response.put("grid", grid.getCells());
        System.out.println("Response sent: " + response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reveal")
    public ResponseEntity<Map<String, Object>> revealCell(@RequestParam int x, @RequestParam int y) {
        gameService.revealCell(x, y);

        Map<String, Object> response = new HashMap<>();
        response.put("grid", gameService.getGrid().getCells());
        response.put("gameOver", gameService.isGameOver());
        response.put("gameLost", gameService.isGameLost());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/win")
    public ResponseEntity<Boolean> checkWin() {
        boolean isWon = gameService.checkWin();
        return ResponseEntity.ok(isWon);
    }
}