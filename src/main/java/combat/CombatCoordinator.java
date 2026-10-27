package combat;

import common.*;
/*
Person A Combat and Character
this class is everything Person C needs to be able to call for running the game

 */

public class CombatCoordinator implements FighterInfo {


    @Override
    public Hero createNewHero(String name, Inventory inventory) {
        throw new UnsupportedOperationException("TODO -> Person A");
    }

    @Override
    public Hero loadHero(String name, int health, int gold, Inventory inventory) {
        throw new UnsupportedOperationException("TODO -> Person A");
    }

    @Override
    public Enemy createEnemy(int roomNumber, Difficulty difficulty) {
        throw new UnsupportedOperationException("TODO -> Person A");
    }

    @Override
    public boolean fight(Hero hero, Enemy enemy) {
        throw new UnsupportedOperationException("TO DO -> Person A");
    }
}
