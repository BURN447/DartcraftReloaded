package burn447.dartcraftReloaded.Items.NonBurnable;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class EntityNonBurnableItem extends EntityItem {
    public EntityNonBurnableItem(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.isImmuneToFire = true;
    }

    public EntityNonBurnableItem(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
        this.isImmuneToFire = true;
    }

    public EntityNonBurnableItem(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getDamageType().equals(DamageSource.OUT_OF_WORLD.damageType)) {
            return true;
        }
        return false;
    }

    public static class EventHandler {
        public static final EventHandler instance = new EventHandler();

        private EventHandler(){

        }

        @SubscribeEvent
        public void onExpire(ItemExpireEvent event) {
            if(event.getEntityItem() instanceof EntityNonBurnableItem) {
                event.setCanceled(true);
            }
        }
    }
}
