package world;

import common.Difficulty;
import common.Enemy;
import common.FighterInfo;
import common.Hero;
import common.GameEventListener;

public class Dungeon {
    public static final int TOTAL_ROOMS = 5;

    private final FighterInfo fighter;
    private final ConsoleUI ui;
    private final GameEventListener listener;

    public Dungeon(FighterInfo fighter, ConsoleUI ui, GameEventListener listener) {
        this.fighter = fighter;
        this.ui = ui;
        this.listener = listener;
    }

    // Returns true if the hero cleared all rooms, false if the hero died
    public boolean run(Hero hero, Difficulty difficulty) {
        for (int room = 1; room <= TOTAL_ROOMS; room++) {
            ui.showRoom(room, TOTAL_ROOMS);
            listener.onEvent("Room " + room + " started");

            Enemy enemy = fighter.createEnemy(room, difficulty);
            ui.showEnemy(enemy);

            boolean heroWon = fighter.fight(hero, enemy);
            if (!heroWon) {
                listener.onEvent(hero.getName() + " was defeated by " + enemy.getName());
                ui.showGameOver();
                return false;
            }

            listener.onEvent(hero.getName() + " defeated " + enemy.getName());

            hero.addGold(enemy.getReward());
            listener.onEvent(hero.getName() + " got " + enemy.getReward() + " gold");
            ui.showStatus(hero);
        }

        listener.onEvent(hero.getName() + " cleared the dungeon");
        ui.showVictory();
        return true;
    }
}
