package world;

import common.Difficulty;
import common.FighterInfo;
import common.ShopInfo;

/*
The main logic. It shows the menu and controls the flow of the game, start to end.
Can get the combat and items modules through the interface in common.

Person C
-> Write the menu loop
-> Write the game flow (choose difficulty, fight and more)
-> Handle saving and loading from a file
 */

public class Game {
    private final FighterInfo fighter;
    private final ShopInfo shop;

    public Game(FighterInfo fighter, ShopInfo shop) {
        this.fighter = fighter;
        this.shop = shop;
    }

    public void run() {
        IO.println("Below the Surface is starting...");

        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = IO.readln("Choose: ").trim();

            switch (choice) {
                case "1" -> newGame();
                case "2" -> IO.println("Load game is coming soon.");
                case "3" -> {
                    IO.println("Goodbye!");
                    running = false;
                }
                default -> IO.println("Not a valid choice. Type 1, 2 or 3.");
            }
        }
    }

    private void showMainMenu() {
        IO.println();
        IO.println("=== BELOW THE SURFACE ===");
        IO.println("1. New game");
        IO.println("2. Load game");
        IO.println("3. Quit");
    }

    private void newGame() {
        String name = askForName();
        Difficulty difficulty = askForDifficulty();

        IO.println("Welcome, " + name + "! Difficulty: " + difficulty);

        // Next step: create the inventory and hero, then start the rooms.
    }

    private String askForName() {
        while (true) {
            String name = IO.readln("What is your hero's name? ").trim();
            if (!name.isEmpty()) {
                return name;
            }
            IO.println("The name can't be empty.");
        }
    }

    private Difficulty askForDifficulty() {
        while (true) {
            IO.println("Choose difficulty:");
            IO.println("1. Easy");
            IO.println("2. Normal");
            IO.println("3. Hard");
            String choice = IO.readln("Choose: ").trim();

            switch (choice) {
                case "1" -> { return Difficulty.EASY; }
                case "2" -> { return Difficulty.NORMAL; }
                case "3" -> { return Difficulty.HARD; }
                default -> IO.println("Not a valid choice. Type 1, 2 or 3.");
            }
        }
    }
}