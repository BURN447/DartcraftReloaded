package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.Handlers.DCRPotionHandler;
import burn447.dartcraftReloaded.Items.Tools.ItemMagnetGlove;
import burn447.dartcraftReloaded.Potion.Effects.EffectMagnet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.*;

/**
 * Created by BURN447 on 7/6/2018.
 */
public class onLivingUpdate {

    public static List<EntityPlayer> flightList = new ArrayList<EntityPlayer>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving().hasCapability(CAPABILITY_SHEARABLE, null)) {
            event.getEntityLiving().getCapability(CAPABILITY_SHEARABLE, null).update();
        }

        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = ((EntityPlayer) event.getEntityLiving());
            Iterable<ItemStack> armor = player.getArmorInventoryList();
            int wings = 0;
            for (ItemStack slotSelected : armor) {
                if (slotSelected.getItem() instanceof burn447.dartcraftReloaded.Items.ItemArmor && slotSelected.hasCapability(CAPABILITY_TOOLMOD, null)) {
                    //Camo
                    if (slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasCamo()) {
                        PotionEffect invisibility = new PotionEffect(MobEffects.INVISIBILITY, 20);
                        player.addPotionEffect(invisibility);
                    }
                    //Speed
                    if (slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasSpeed()) {
                        PotionEffect speed = new PotionEffect(MobEffects.SPEED, 20);
                        player.addPotionEffect(speed);
                    }
                    //Wing
                    if (slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasWing()) {
                        wings++;
                    }
                }
            }
            Iterable<ItemStack> hotBar = player.getHeldEquipment();
            for(ItemStack slotSelected : hotBar) {
                if(slotSelected.getItem() instanceof ItemMagnetGlove && slotSelected.hasCapability(CAPABILITY_MAGNET, null)) {
                    if(slotSelected.getCapability(CAPABILITY_MAGNET, null).isActivated()) {
                        PotionEffect magnet = new EffectMagnet(20);
                        player.addPotionEffect(magnet);
                    }
                }
            }
            if (!player.isCreative()) {
                if (!player.world.isRemote) {
                    if (wings == 4) {
                        if (!player.isSpectator() && !player.capabilities.allowFlying) {
                            player.capabilities.allowFlying = true;
                            player.sendPlayerAbilities();
                            flightList.add(player);
                        }
                    } else {
                        if (flightList.contains(player)) {
                            player.capabilities.allowFlying = false;
                            player.capabilities.isFlying = false;
                            player.sendPlayerAbilities();
                            flightList.remove(player);
                        }
                    }
                }
            }
        }
    }
}
