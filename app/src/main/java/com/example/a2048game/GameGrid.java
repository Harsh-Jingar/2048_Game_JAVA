package com.example.a2048game;

import java.util.Random;

public class GameGrid {
    private int[][] grid; // 4x4 grid
    private int score;
    private final Random random;

    public GameGrid() {
        grid = new int[4][4];
        score = 0;
        random = new Random();
        addRandomTile(); // Start with one random tile
        addRandomTile(); // Add another random tile
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    // Method to add a random tile (2 or 4)
    public void addRandomTile() {
        int emptyCount = 0;

        // Count empty spaces
        for (int[] row : grid) {
            for (int value : row) {
                if (value == 0) {
                    emptyCount++;
                }
            }
        }

        // If there are no empty spaces, return
        if (emptyCount == 0) {
            return;
        }

        // Pick a random empty position to place a 2 or 4
        int position = random.nextInt(emptyCount);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    if (position == 0) {
                        grid[i][j] = random.nextInt(10) < 9 ? 2 : 4; // 90% chance for 2, 10% for 4
                        return;
                    }
                    position--;
                }
            }
        }
    }

    // Method to reset the game state
    public void reset() {
        grid = new int[4][4]; // Reinitialize the grid
        score = 0; // Reset the score
        addRandomTile(); // Add a random tile
        addRandomTile(); // Add another random tile
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        // Check for empty spaces
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    return false; // Still have empty spaces
                }
                // Check if adjacent tiles can merge (horizontal or vertical)
                if (i < 3 && grid[i][j] == grid[i + 1][j]) {
                    return false; // Can merge down
                }
                if (j < 3 && grid[i][j] == grid[i][j + 1]) {
                    return false; // Can merge right
                }
            }
        }
        return true; // No moves left
    }

    // Method to check if the player has won (2048 tile)
    public boolean checkWin() {
        for (int[] row : grid) {
            for (int value : row) {
                if (value == 2048) {
                    return true; // Player has reached 2048
                }
            }
        }
        return false; // Player has not won yet
    }

    // Method to move tiles in the specified direction (left, right, up, down)
    public boolean move(String direction) {
        boolean moved = false;

        switch (direction) {
            case "left":
                moved = slideLeft();
                break;
            case "right":
                reverse(); // Reverse the grid for right move
                moved = slideLeft(); // Treat right as left
                reverse(); // Reverse back
                break;
            case "up":
                transpose(); // Transpose for up move
                moved = slideLeft(); // Treat up as left
                transpose(); // Transpose back
                break;
            case "down":
                transpose(); // Transpose for down move
                reverse(); // Reverse the grid for down move
                moved = slideLeft(); // Treat down as left
                reverse(); // Reverse back
                transpose(); // Transpose back
                break;
        }

        return moved; // Return if any tile was moved
    }

    // Method to slide tiles left
    private boolean slideLeft() {
        boolean moved = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (grid[i][j] != 0) {
                    for (int k = j; k > 0; k--) {
                        if (grid[i][k - 1] == 0) {
                            // Move tile left
                            grid[i][k - 1] = grid[i][k];
                            grid[i][k] = 0;
                            moved = true;
                        } else if (grid[i][k - 1] == grid[i][k]) {
                            // Merge tiles
                            grid[i][k - 1] *= 2;
                            score += grid[i][k - 1];
                            grid[i][k] = 0;
                            moved = true;
                        }
                        break; // Exit after the first move
                    }
                }
            }
        }
        return moved;
    }

    // Reverse the grid for right and down moves
    private void reverse() {
        for (int i = 0; i < 4; i++) {
            int left = 0, right = 3;
            while (left < right) {
                int temp = grid[i][left];
                grid[i][left] = grid[i][right];
                grid[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    // Transpose the grid for up and down moves
    private void transpose() {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = temp;
            }
        }
    }
}
