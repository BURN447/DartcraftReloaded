package burn447.dartcraftReloaded.capablilities.PlayerModifier;

import net.minecraft.entity.SharedMonsterAttributes;

import java.util.concurrent.Callable;

/**
 * Created by BURN447 on 6/21/2018.
 */
public class PlayerModifierFactory implements Callable<IPlayerModifier> {
    @Override
    public IPlayerModifier call() throws Exception {
        return new IPlayerModifier() {

            private float attackDamage = ((float) SharedMonsterAttributes.ATTACK_DAMAGE.getDefaultValue());
            private float wingPower = 0.0f;
            private float flightCounter = wingPower;

            @Override
            public float getAttackDamage() {
                return attackDamage;
            }

            @Override
            public void setAttackDamage(float newDamage) {
                attackDamage = newDamage;
            }

            @Override
            public float getWingPower() {
                return wingPower;
            }

            @Override
            public void setWingPower(float newWingPower) {
                wingPower = newWingPower;
            }

            @Override
            public float getFlightTimer() {
                return flightCounter;
            }

            @Override
            public void subtractFlightTimer() {
                flightCounter--;
            }

            @Override
            public void setFlightTimer(float newFlightCounter) {
                flightCounter = newFlightCounter;
            }
        };
    }
}
