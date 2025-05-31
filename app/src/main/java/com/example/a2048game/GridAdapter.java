package com.example.a2048game;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private final Context context;
    private int[][] grid;

    public GridAdapter(Context context, int[][] grid) {
        this.context = context;
        this.grid = grid;
    }

    @Override
    public int getCount() {
        return 16; // 4x4 grid
    }

    @Override
    public Object getItem(int position) {
        return grid[position / 4][position % 4];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(200, 200)); // Size of each tile
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(24);
            textView.setTypeface(Typeface.DEFAULT_BOLD);  // Bold text for numbers
            textView.setTextColor(Color.BLACK);  // Text color
            textView.setPadding(5, 5, 5, 5);  // Padding inside the tiles
        } else {
            textView = (TextView) convertView;
        }

        int value = grid[position / 4][position % 4];
        textView.setText(value == 0 ? "" : String.valueOf(value));  // Only show numbers on non-empty tiles
        textView.setBackgroundColor(getTileColor(value));  // Set tile background color based on value

        // Add animation to tile
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationY", -100f, 0f);
        animator.setDuration(200); // Duration of the animation
        animator.start(); // Start animation

        return textView;
    }

    // Set tile colors based on value
    private int getTileColor(int value) {
        switch (value) {
            case 2: return 0xFFEEE4DA;
            case 4: return 0xFFEDE0C8;
            case 8: return 0xFFF2B179;
            case 16: return 0xFFF59563;
            case 32: return 0xFFF67C5F;
            case 64: return 0xFFF65E3B;
            case 128: return 0xFFEDCF72;
            case 256: return 0xFFEDCC61;
            case 512: return 0xFFEDC850;
            case 1024: return 0xFFEDC53F;
            case 2048: return 0xFFEDC22E;
            default: return 0xFFCDC1B4;
        }
    }

    // Method to update the grid
    public void updateGrid(int[][] newGrid) {
        this.grid = newGrid;
        notifyDataSetChanged(); // Notify adapter to refresh the grid view
    }
}
