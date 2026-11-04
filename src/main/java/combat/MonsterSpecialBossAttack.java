package combat;

import common.Fighter;

public class MonsterSpecialBossAttack extends ChanceAttack {
    private static final int CHANCE_FOR_HEAVY = 20;
    private static final double SPECIAL_DAMAGE_FACTOR = 2.0;

    public MonsterSpecialBossAttack() {
        super(CHANCE_FOR_HEAVY, SPECIAL_DAMAGE_FACTOR);
    }

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
        if (isHeavy()) {
            return "☠️ The boss unleashes a massive roar as dark energy gathers around its body," +
                    "before releasing a heavy force that drains the life of your soul in seconds.";
        }
        return "👹 The boss lunges forward with a powerful strike, its heavy blow aimed straight at you.";
    }

}
