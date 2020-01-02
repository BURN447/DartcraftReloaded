package burn447.dartcraftReloaded.Potion.Effects;

import burn447.dartcraftReloaded.Handlers.DCRPotionHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import javax.vecmath.Vector3d;
import java.util.List;

public class EffectMagnet extends PotionEffect {


    public EffectMagnet(int duration) {
        super(DCRPotionHandler.potionMagnet, duration, 1, true, false);

    }

    @Override
    public void performEffect(EntityLivingBase entity) {
        //Inspired by Botania Code

        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        double range = 10.0d;
        PotionEffect activePotionEffect = entity.getActivePotionEffect(DCRPotionHandler.potionMagnet);
        if(activePotionEffect != null) {
            range += activePotionEffect.getAmplifier() * 0.3f;
        }

        List<EntityItem> items = entity.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
        for(EntityItem item : items) {
            if(item.getItem().isEmpty() || item.isDead) {
                continue;
            }

            // constant force!
            float strength = 0.07f;

            // calculate direction: item -> player
            Vector3d vec = new Vector3d(x, y, z);
            vec.sub(new Vector3d(item.posX, item.posY, item.posZ));

            vec.normalize();
            vec.scale(strength);

            // we calculated the movement vector and set it to the correct strength.. now we apply it \o/
            item.motionX += vec.x;
            item.motionY += vec.y;
            item.motionZ += vec.z;
        }
    }

}
