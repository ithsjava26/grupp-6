package common;

import java.util.List;
//What the world needs from the items module, creating inventories,
// generating shop offers and buying items.
public interface ShopInfo {

    Inventory createInventor();
    List<ShopOffer> generateOffers(int roomNumber);

    //True if Player could buy, False if something went wrong
    boolean buy(Hero hero, ShopOffer offer);
}
