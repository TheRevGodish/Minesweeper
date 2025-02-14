package com.minesweeper.minesweeper.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minesweeper")
public class MinesweeperController {

    @GetMapping
    public String getRequest() {
        return "GET request received";
    }

    @PostMapping("/start")
    public String postRequest() {
        return "POST request received";
    }

    @PutMapping
    public String putRequest() {
        return "PUT request received";
    }

    @DeleteMapping
    public String deleteRequest() {
        return "DELETE request received";
    }
}