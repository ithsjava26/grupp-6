package combat;

import common.Fighter;

public interface AttackStrategy {

    String getName();

    String getDescription();

    int calculateDamage(Fighter attacker, Fighter defender);

}
