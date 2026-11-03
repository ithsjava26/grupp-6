package common;

import java.util.List;
//What the world needs from the items module, creating inventories,
// generating shop offers and buying items.
public interface ShopInfo {

    Inventory createInventory();
    List<ShopOffer> generateOffers(int roomNumber);

    // Recreates an item from its type and level, e.g. when loading a saved game
    Item createItem(ItemType type, int level);

    //True if Player could buy, False if something went wrong
    boolean buy(Hero hero, ShopOffer offer);
}
