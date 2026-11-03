package items;

import common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Shop implements ShopInfo {

    public static final int OFFERS_PER_VISIT = 3;

    private final Random random;

    public Shop() {
        this(new Random());
    }

    Shop(Random random) {
        this.random = Objects.requireNonNull(random, "Random cannot be null");
    }

    static int priceOf(Item item) {
        int pricePerLevel = switch (item.type()) {
            case WEAPON, ARMOR -> 20;
            case POTION -> 10;
        };
        return pricePerLevel * item.level();
    }

    @Override
    public Inventory createInventory() {
        return new HeroInventory();
    }

    @Override
    public List<ShopOffer> generateOffers(int roomNumber) {
        if (roomNumber < 1) {
            throw new IllegalArgumentException("Room number must be at least 1");
        }

        List<ShopOffer> offers = new ArrayList<>();
        for (int i = 0; i < OFFERS_PER_VISIT; i++) {
            ItemType type = randomType();
            int level = randomLevel(roomNumber);
            Item item = ItemFactory.create(type, level);
            offers.add(new ShopOffer(item, priceOf(item)));
        }
        return List.copyOf(offers);
    }

    @Override
    public boolean buy(Hero hero, ShopOffer offer) {
        Objects.requireNonNull(hero, "Hero cannot be null");
        Objects.requireNonNull(offer, "Offer cannot be null");

        Inventory inventory = hero.getInventory();
        if (inventory == null) {
            return false;
        }
        if (!hero.spendGold(offer.price())) {
            return false;
        }
        inventory.add(offer.item());
        return true;
    }

    private ItemType randomType() {
        ItemType[] types = ItemType.values();
        return types[random.nextInt(types.length)];
    }

    private int randomLevel(int roomNumber) {
        int lowest = Math.max(1, roomNumber - 1);
        return lowest + random.nextInt(roomNumber - lowest + 1);
    }
}