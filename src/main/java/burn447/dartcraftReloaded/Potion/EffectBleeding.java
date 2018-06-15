package burn447.dartcraftReloaded.Potion;

import burn447.dartcraftReloaded.Handlers.DCRPotionHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

/**
 * Created by BURN447 on 6/14/2018.
 */
public class EffectBleeding extends PotionEffect {
    public EffectBleeding(int duration) {
        super(DCRPotionHandler.potionBleeding, duration, 0, false, true);
    }

    @Override
    public void performEffect(EntityLivingBase entityIn) {
        entityIn.attackEntityFrom(DamageSource.GENERIC, 1);
    }
}
