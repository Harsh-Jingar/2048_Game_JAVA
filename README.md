# 2048 Game

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Version](https://img.shields.io/badge/Version-1.0-blue.svg)
![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)

A modern implementation of the classic 2048 puzzle game for Android devices. This addictive game challenges players to slide numbered tiles on a 4x4 grid to combine them and create a tile with the number 2048.

This project was created for learning purposes to understand Android app development concepts, Java programming fundamentals, and game development principles. It serves as an educational exploration of mobile game development using the Android platform.

## Features

- **Smooth Gameplay**: Intuitive swipe controls with fluid animations
- **Sound Effects**: Engaging audio feedback for moves, merges, wins, and game over events
- **Score Tracking**: Real-time score update as you merge tiles
- **Clean UI**: Beautiful, minimalist design with color-coded tiles
- **Game State Management**: Win/lose detection with appropriate dialogs
- **Animations**: Tile movement animations for better visual feedback

## Screenshots

[Place screenshots here]

## How to Play

1. **Start**: Game begins with two tiles (2 or 4) on the grid
2. **Move**: Swipe up, down, left, or right to move all tiles in that direction
3. **Merge**: When two tiles with the same number collide, they merge into one tile with the sum value
4. **Goal**: Create a tile with the number 2048
5. **Game Over**: When there are no more possible moves
6. **Score**: Earn points with each successful merge

## Game Logic

The game follows the classic 2048 rules:
- After each move, a new tile (2 or 4) appears randomly on the grid
- Tiles slide as far as possible in the chosen direction until they hit the wall or another tile
- If two tiles of the same number collide, they merge into one tile with the sum value
- The game is won when a 2048 tile is created
- The game is over when there are no empty spaces and no adjacent tiles with the same value

## Technical Details

- **Language**: Java
- **Target SDK**: API 34 (Android 14)
- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Architecture**: MVC pattern with dedicated classes for game logic, UI components, and animations
- **Key Components**:
  - `GameGrid.java`: Core game logic implementation
  - `GridAdapter.java`: Custom adapter for rendering the grid
  - `MainActivity.java`: Main game activity with UI and gesture handling

## Java Technologies & Concepts Implemented

- **Object-Oriented Programming**: Extensive use of Java OOP principles including encapsulation, inheritance, and polymorphism
- **Java Collections Framework**: Using multi-dimensional arrays for the game grid and state management
- **Event-Driven Programming**: Implementation of touch event handling using Java interfaces
- **Custom Adapters**: Extending `BaseAdapter` for the custom grid implementation
- **Design Patterns**:
  - Model-View-Controller (MVC) pattern for separating game logic from UI
  - Adapter pattern for grid rendering
  - Observer pattern for score and game state updates
- **Java Animation Framework**: Usage of `ObjectAnimator` for tile movement animations
- **Resource Management**: Proper handling of media resources with MediaPlayer
- **Exception Handling**: Robust error handling throughout the application
- **Java Multithreading**: Using `runOnUiThread` for UI updates on the main thread
- **Random Number Generation**: Use of Java's `Random` class for tile generation
- **Interfaces & Listeners**: Implementation of custom touch listeners and event handlers

## Learning Objectives

This project was created to achieve the following learning objectives:
- Understanding Android UI development and layouts
- Implementation of game mechanics and logic
- Gesture recognition for swipe controls
- Working with animations and sound effects
- Managing application lifecycle and state
- Applying Java development best practices in a real-world application
- Implementing efficient algorithms for game logic (merging, sliding, win detection)

## Future Enhancements

- High score leaderboard
- Game state saving
- Undo move functionality
- Different grid sizes (5x5, 6x6)
- Custom themes
- Challenge modes

## Installation

1. Clone the repository:
```
git clone https://github.com/yourUsername/2048Game.git
```

2. Open the project in Android Studio

3. Build and run the application on your Android device or emulator

## Building from Source

This project uses Gradle build system. To build the app:

```
./gradlew assembleDebug
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
