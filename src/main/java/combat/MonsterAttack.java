package combat;

import common.Fighter;

import java.util.Random;

public class MonsterAttack implements AttackStrategy {
    private static final double HEAVY_DAMAGE_FACTOR = 1.5;
    private static final double CHANCE_FOR_HEAVY = 20;
    private static final double DAMAGE_FACTOR = 1.0;

    private final Random random = new Random();
    private boolean heavyAttack;

    @Override
    public String getName() {
        return "Scavenger scratch";
    }

    @Override
    public String getDescription() {
        return "A quick claw attack that deals standard damage to its target, " +
                "with 20% chance to deal 50% more damage. ";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter enemy) {
        if (heavyAttack) {
            return "The creature lowers its body, before suddenly striking " +
                    "you  with its full weight and razor sharp claws";
        }
        return "The creature lunges forward with a frantic hiss, slashing its jagged claws across your skin";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        heavyAttack = random.nextInt(100) < CHANCE_FOR_HEAVY;
        return heavyAttack ? HEAVY_DAMAGE_FACTOR : DAMAGE_FACTOR;
    }
}
