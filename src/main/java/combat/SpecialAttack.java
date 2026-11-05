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
        return "You gather all your Strength, waiting for the perfect opportunity to unleash " +
                "your full power. \nDealing double damage against enemies with less than 20% health!";
    }

    @Override
    public String getMessage(Fighter attacker, Fighter defender) {
        if (defender.getHealth() <= defender.getMaxHealth() * FINISH_LIMIT) {
            return "🗡️ You sense your enemy's weakness and seize the opportunity. " +
                    "Gathering all your strength, you unleash a final devastating blow.";
        }
        return "💨 You gather all your strength and unleash a mighty blow, but your enemy is still " +
                "too strong.\n" +
                " They withstand the full force behind your attack, leaving only a normal strike " +
                "breaking through their skin.";
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        if (defender.getHealth() <= defender.getMaxHealth() * FINISH_LIMIT) {
            return FINISH_MULTIPLIER;
        }
        return 1.0;
    }
}
