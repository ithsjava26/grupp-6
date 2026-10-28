package combat;

import common.Fighter;

public class HeavyAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.5;

    @Override
    public String getName() {
        return "Heavy Attack";
    }

    @Override
    public String getDescription() {
        return "A powerfull blow that deals 150% of the Base Dmg.";
    }

    @Override
    public int calculateDamage(Fighter attacker, Fighter defender) {
        return (int) Math.round(attacker.getAttack() * DAMAGE_FACTOR);
    }
}
