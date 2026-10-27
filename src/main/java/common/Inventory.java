package common;

import java.util.List;

public interface Inventory {
    void add(Item item);
    boolean remove (Item item);
    List<Item> getItems();
}
