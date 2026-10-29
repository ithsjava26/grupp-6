package items;

final class ItemRules {

    private ItemRules() {
    }

    static void requireValidBasics(String name, int level) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (level < 1) {
            throw new IllegalArgumentException("Level must be at least 1");
        }
    }
}