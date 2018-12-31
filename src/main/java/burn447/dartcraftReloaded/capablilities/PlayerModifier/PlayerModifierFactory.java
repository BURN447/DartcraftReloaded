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

            @Override
            public float getAttackDamage() {
                return attackDamage;
            }

            @Override
            public void setAttackDamage(float newDamage) {
                attackDamage = newDamage;
            }
        };
    }
}
