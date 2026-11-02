package items;

import common.Hero;
import common.Inventory;
import common.ShopInfo;
import common.ShopOffer;

import java.util.List;

/*
Person C will call this class to create an inventory and to buy Items to use inGame.

Person B: Items and Inventory
- Write inventory class
- Use factry pattern to create items (Type and level)
- Generate random shopOffers
- Write buying items
 */

public class Shop implements ShopInfo {

    @Override
    public Inventory createInventory() {
        return new HeroInventory();
    }
    @Override
    public List<ShopOffer> generateOffers(int roomNumber) {
        throw new UnsupportedOperationException("TODO -> Person B");
    }

    @Override
    public boolean buy(Hero hero, ShopOffer offer) {
        throw new UnsupportedOperationException("TODO -> Person B");
    }
}
