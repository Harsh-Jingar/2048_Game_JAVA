package com.example.a2048game;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private GridView gameGrid;
    private GameGrid game;
    private GridAdapter gridAdapter;

    // Constants for swipe detection
    private float startX, startY;
    private final int SWIPE_THRESHOLD = 100;

    // Media players for sound effects
    private MediaPlayer mergeSound;
    private MediaPlayer moveSound;  // New sound for moves
    private MediaPlayer gameOverSound;
    private MediaPlayer winSound; // New sound for win

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        tvScore = findViewById(R.id.tvScore);
        gameGrid = findViewById(R.id.gameGrid);

        // Initialize game and adapter
        game = new GameGrid();
        gridAdapter = new GridAdapter(this, game.getGrid());
        gameGrid.setAdapter(gridAdapter);

        // Initialize media players
        mergeSound = MediaPlayer.create(this, R.raw.merge); // Add your sound file for merge
        moveSound = MediaPlayer.create(this, R.raw.move);   // Add your sound file for move
        gameOverSound = MediaPlayer.create(this, R.raw.gameover); // Add your sound file for game over
        winSound = MediaPlayer.create(this, R.raw.win); // Add your sound file for win

        // Update UI when the game state changes
        updateUI();

        // Set up swipe gesture detection for the game grid
        gameGrid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        float deltaX = endX - startX;
                        float deltaY = endY - startY;

                        // Detect horizontal or vertical swipes
                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            // Horizontal swipe
                            if (Math.abs(deltaX) > SWIPE_THRESHOLD) {
                                handleSwipe(deltaX > 0 ? "right" : "left");
                            }
                        } else {
                            // Vertical swipe
                            if (Math.abs(deltaY) > SWIPE_THRESHOLD) {
                                handleSwipe(deltaY > 0 ? "down" : "up");
                            }
                        }
                        return true;
                }
                return false;
            }
        });
    }

    // Method to handle swipe and perform the respective move
    private void handleSwipe(String direction) {
        moveSound.start(); // Play move sound on swipe

        if (game.move(direction)) {
            mergeSound.start(); // Play merge sound on valid move
            game.addRandomTile();
            updateUI();

            // Check for win condition after a move
            if (game.checkWin()) {
                winSound.start(); // Play win sound
                showWinDialog(); // Show win dialog
            }

            // Check for game over after a move
            if (game.isGameOver()) {
                gameOverSound.start(); // Play game over sound
                showGameOverDialog(); // Show game over dialog
            }
        }
    }

    // Method to show win dialog
    private void showWinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You Win!")
                .setMessage("Your final score is: " + game.getScore())
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        restartGame();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // Finish the current activity
                        System.exit(0); // Close the app
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // Method to show game over dialog
    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over")
                .setMessage("Your final score is: " + game.getScore())
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        restartGame();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // Finish the current activity
                        System.exit(0); // Close the app
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to restart the game
    private void restartGame() {
        game.reset(); // Reset the game state
        updateUI(); // Update the UI
    }

    // Method to update UI with new grid and score
    private void updateUI() {
        // Update UI on the main thread to ensure smooth performance
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                tvScore.setText("Score: " + game.getScore());
                gridAdapter.updateGrid(game.getGrid());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release media player resources
        if (mergeSound != null) {
            mergeSound.release();
        }
        if (moveSound != null) {
            moveSound.release(); // Release the move sound
        }
        if (gameOverSound != null) {
            gameOverSound.release();
        }
        if (winSound != null) {
            winSound.release(); // Release the win sound
        }
    }
}
