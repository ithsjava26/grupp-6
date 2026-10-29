package world;

import common.Difficulty;
import common.Hero;

public class ConsoleUI {

    public void showStatus(Hero hero) {
        IO.println(hero.getName() + " - HP: " + hero.getHealth() + "/" + hero.getMaxHealth() +
                " - Gold: " + hero.getGold());
    }

    public void showMainMenu() {
        IO.println();
        IO.println("=== BELOW THE SURFACE ===");
        IO.println("1. New game");
        IO.println("2. Load game");
        IO.println("3. Quit");
    }

    public String askForName() {
        while (true) {
            String name = IO.readln("What is your hero's name? ").trim();
            if (!name.isEmpty()) {
                return name;
            }
            IO.println("The name can't be empty.");
        }
    }

    public Difficulty askForDifficulty() {
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


