package burn447.dartcraftReloaded.Potion.Effects;

import burn447.dartcraftReloaded.Handlers.DCRPotionHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;

import javax.vecmath.Vector3d;
import java.util.List;

public class EffectMagnet extends PotionEffect {


    public EffectMagnet(int duration) {
        super(DCRPotionHandler.potionMagnet, duration, 0, false, true);
    }

    @Override
    public void performEffect(EntityLivingBase entityIn) {
        //Inspired By Botania/TiC code

        double x = entityIn.posX;
        double y = entityIn.posY;
        double z = entityIn.posZ;
        double range = 1.8d;

        PotionEffect activePotionEffect = entityIn.getActivePotionEffect(DCRPotionHandler.potionMagnet);
        if(activePotionEffect != null) {
            range += activePotionEffect.getAmplifier() * 0.3F;
        }

        List<EntityItem> items = entityIn.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
        int pulled  = 0;
        for(EntityItem item: items) {
            if(item.getItem().isEmpty() || item.isDead) {
                continue;
            }

            if(pulled > 200) {
                break;
            }

            float strength = 0.07F;

            Vector3d vec = new Vector3d(x, y, z);
            vec.sub(new Vector3d(item.posX, item.posY, item.posZ));

            vec.normalize();
            vec.scale(strength);

            item.motionX += vec.x;
            item.motionY += vec.y;
            item.motionZ += vec.z;

            pulled++;
        }
    }
}
