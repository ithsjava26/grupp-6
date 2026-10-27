package world;

import common.FighterInfo;
import common.ShopInfo;

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
