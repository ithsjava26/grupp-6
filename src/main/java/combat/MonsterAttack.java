package combat;

import common.Fighter;

public class MonsterAttack implements AttackStrategy {
    private static final double DAMAGE_FACTOR = 1.0;

    @Override
    public String getName() {
        return "Scavenger scratch";
    }

    @Override
    public String getDescription() {
        return "A quick claw attack that deals standart damage to its target";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter enemy) {
        return "The creature lunges forward with a frantic hiss, slashing its jagged claws across your skin";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        return DAMAGE_FACTOR;
    }
}
