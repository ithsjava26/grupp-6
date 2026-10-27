package world;

import common.FighterInfo;
import common.ShopInfo;
/*
The main logic. It shows the menu and controls the flow of the game, start to end.
Can get the combat and items modules through the interface in common.

Person C
-> Write the menu loop
-> Write the game flow (choose difficulty, fight and more)
-> Handle svaving and loading from a file
 */
public class Game {
    private final FighterInfo fighter;
    private final ShopInfo shop;

    public Game(FighterInfo fighter, ShopInfo shop) {
        this.fighter = fighter;
        this.shop = shop;
    }

    public void run () {
        IO.println("Below the Surface is starting...");
    }
}
