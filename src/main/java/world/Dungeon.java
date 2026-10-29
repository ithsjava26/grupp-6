package world;

import common.Difficulty;
import common.Enemy;
import common.FighterInfo;
import common.Hero;

public class Dungeon {
    public static final int TOTAL_ROOMS = 5;

    private final FighterInfo fighter;
    private final ConsoleUI ui;

    public Dungeon(FighterInfo fighter, ConsoleUI ui) {
        this.fighter = fighter;
        this.ui = ui;
    }

    // Returns true if the hero cleared all rooms, false if the hero died
    public boolean run(Hero hero, Difficulty difficulty) {
        for (int room = 1; room <= TOTAL_ROOMS; room++) {
            ui.showRoom(room, TOTAL_ROOMS);

            Enemy enemy = fighter.createEnemy(room, difficulty);
            ui.showEnemy(enemy);

            boolean heroWon = fighter.fight(hero, enemy);
            if (!heroWon) {
                ui.showGameOver();
                return false;
            }

            hero.addGold(enemy.getReward());
            ui.showStatus(hero);
        }

        ui.showVictory();
        return true;
    }
}
