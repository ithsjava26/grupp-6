package items;

import common.Hero;
import common.Inventory;
import common.ShopInfo;
import common.ShopOffer;

import java.util.List;

public class Shop implements ShopInfo {
    @Override
    public Inventory createInventor() {
        throw new UnsupportedOperationException("TODO -> Person B");
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
