package world;

import combat.CombatCoordinator;
import common.FighterInfo;
import items.Shop;

public class Main {
    public static void main(String[] args) {
        Game gameEngine =new Game(new CombatCoordinator(), new Shop());
        gameEngine.run();
    }
}
