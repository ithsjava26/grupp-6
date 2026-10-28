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
    public int calculateDamage(Fighter attacker, Fighter defender) {
        int damage = attacker.getAttack();
        if (defender.getHealth() <= defender.getMaxHealth() * FINISH_LIMIT) {
            damage = damage * FINISH_MULTIPLIER;
        }
        return damage;
    }
}
