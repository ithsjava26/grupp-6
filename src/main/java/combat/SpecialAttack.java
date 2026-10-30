package combat;

import common.Fighter;

public class SpecialAttack implements AttackStrategy {
    private static final double FINISH_LIMIT = 0.20;
    private static final int FINISH_MULTIPLIER = 2;

    @Override
    public String getName() {
        return "Finisher";
    }

    @Override
    public String getDescription() {
        return "Deals double damage against a target with 20% health or less";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        if (defender.getHealth() <= defender.getMaxHealth() * FINISH_LIMIT) {
            return FINISH_MULTIPLIER;
        }
        return 1.0;
    }
}
