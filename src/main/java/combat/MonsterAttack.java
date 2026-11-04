package combat;

import common.Fighter;

public class MonsterAttack extends ChanceAttack {
    private static final int CHANCE_FOR_HEAVY = 20;
    private static final double HEAVY_DAMAGE_FACTOR = 1.5;


    public MonsterAttack() {
        super(CHANCE_FOR_HEAVY, HEAVY_DAMAGE_FACTOR);
    }
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
        if (isHeavy()) {
            return "💢 The creature lowers its body, before suddenly striking " +
                    "you  with its full weight and razor sharp claws";
        }
        return "🐾 The creature lunges forward with a frantic hiss, slashing its jagged claws across your skin";
    }
}
