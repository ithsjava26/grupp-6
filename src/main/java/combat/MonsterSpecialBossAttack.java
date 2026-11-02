package combat;

import common.Fighter;

import java.util.Random;

public class MonsterSpecialBossAttack implements AttackStrategy {
    private static final double CHANCE_PERCENT = 20;
    private static final double DAMAGE_FACTOR = 1.0;
    private static final double SPEACIAL_DAMAGE_FACTOR = 2.0;

    private final Random random = new Random();
    private boolean heavyAttack;

    @Override
    public String getName() {
        return "Soul Devourer";
    }

    @Override
    public String getDescription() {
        return "A devastating boss attack with a 20% chance to deal double damage";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter defender) {
        if (heavyAttack) {
            return "The boss unleashes a masive roar as dark energy gathers around its body," +
                    "before releasing a heavy force that drains the life of your soul in seconds.";
        }
        return "The boss lunges forward with a powerful strike, its heavy blow aimed straight at you.";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        heavyAttack = random.nextInt(100) < CHANCE_PERCENT;
        return heavyAttack ? SPEACIAL_DAMAGE_FACTOR : DAMAGE_FACTOR;
    }
}
