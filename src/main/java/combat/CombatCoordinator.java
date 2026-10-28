package combat;

import common.*;
/*
Person C will call this class to create heroes and enemies and to run a fight
(it does not handle the work it sends the work to your own classes in your package!)

Person A Combat and Character

- write a Hero and Enemy class
- make enemies stronger in later rooms
- write the fight loop (Who attacks first and more)

 */

public class CombatCoordinator implements FighterInfo {


    @Override
    public Hero createNewHero(String name, Inventory inventory) {
        return new Player(name, Player.MAX_HEALTH, Player.MAX_HEALTH,
                Player.START_ATTACK, Player.START_GOLD, inventory);
    }

    @Override
    public Hero loadHero(String name, int health, int gold, Inventory inventory) {
        return new Player(name, Player.MAX_HEALTH, health,
                Player.START_ATTACK, gold, inventory);
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
