package combat;

import common.Fighter;

import java.util.Random;

public abstract class ChanceAttack implements AttackStrategy {
    private final Random random = new Random();
    private final int chanceProcent;
    private final double heavyFactor;
    private boolean heavy;

    protected ChanceAttack(int chanceProcent, double heavyFactor) {
        this.chanceProcent = chanceProcent;
        this.heavyFactor = heavyFactor;
    }

    protected boolean isHeavy() {
        return heavy;
    }

    @Override
    public double getDamageMultiplier(Fighter attacker, Fighter defender) {
        heavy = random.nextInt(100) < chanceProcent;
        return heavy ? heavyFactor : 1.0;
    }
}
