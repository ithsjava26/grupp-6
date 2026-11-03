package items;

import common.Item;
import common.ItemType;
import common.ShopOffer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    private final Shop shop = new Shop(new Random(42));
    private final Item sword = ItemFactory.create(ItemType.WEAPON, 2);
    private final ShopOffer swordOffer = new ShopOffer(sword, 40);

    @Test
    void createsEmptyInventory() {
        assertTrue(shop.createInventory().getItems().isEmpty());
    }

    @Test
    void generatesFixedNumberOfOffers() {
        assertEquals(Shop.OFFERS_PER_VISIT, shop.generateOffers(1).size());
    }

    @Test
    void offerLevelsMatchTheRoom() {
        for (int i = 0; i < 50; i++) {
            for (ShopOffer offer : shop.generateOffers(4)) {
                int level = offer.item().level();
                assertTrue(level == 3 || level == 4, "Unexpected level " + level);
            }
        }
    }

    @Test
    void firstRoomOnlyOffersLevelOne() {
        for (ShopOffer offer : shop.generateOffers(1)) {
            assertEquals(1, offer.item().level());
        }
    }

    @Test
    void offerPriceFollowsPriceRule() {
        for (ShopOffer offer : shop.generateOffers(3)) {
            assertEquals(Shop.priceOf(offer.item()), offer.price());
        }
    }

    @Test
    void sameSeedGivesSameOffers() {
        List<ShopOffer> first = new Shop(new Random(7)).generateOffers(3);
        List<ShopOffer> second = new Shop(new Random(7)).generateOffers(3);

        assertEquals(first, second);
    }

    @Test
    void roomBelowOneThrows() {
        assertThrows(IllegalArgumentException.class, () -> shop.generateOffers(0));
    }

    @Test
    void buyingTakesGoldAndAddsItem() {
        FakeHero hero = new FakeHero(50, new HeroInventory());

        assertTrue(shop.buy(hero, swordOffer));

        assertEquals(10, hero.getGold());
        assertEquals(List.of(sword), hero.getInventory().getItems());
    }

    @Test
    void cannotBuyWithoutEnoughGold() {
        FakeHero hero = new FakeHero(30, new HeroInventory());

        assertFalse(shop.buy(hero, swordOffer));

        assertEquals(30, hero.getGold());
        assertTrue(hero.getInventory().getItems().isEmpty());
    }

    @Test
    void canBuyWithExactlyEnoughGold() {
        FakeHero hero = new FakeHero(40, new HeroInventory());

        assertTrue(shop.buy(hero, swordOffer));
        assertEquals(0, hero.getGold());
    }

    @Test
    void heroWithoutInventoryKeepsGold() {
        FakeHero hero = new FakeHero(50, null);

        assertFalse(shop.buy(hero, swordOffer));
        assertEquals(50, hero.getGold());
    }

    @Test
    void createItemGivesSameItemAsFactory() {
        assertEquals(ItemFactory.create(ItemType.ARMOR, 3), shop.createItem(ItemType.ARMOR, 3));
    }
}