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
            boolean camo = false;
            int speed = 0;
            int damage = 0;
            int heat = 0;
            int luck = 0;
            int bane = 0;
            int bleed = 0;
            for (ItemStack slotSelected : armor) {
                if (slotSelected.getItem() instanceof burn447.dartcraftReloaded.Items.ItemArmor && slotSelected.hasCapability(CAPABILITY_TOOLMOD, null)) {
                    //Camo
                    if(slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasCamo()) {
                        camo = true;
                    }
                    //Speed
                    speed += slotSelected.getCapability(CAPABILITY_TOOLMOD, null).getSpeedLevel();
                    //Wing
                    if(slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasWing()) {
                        wings++;
                    }
                    //Damage
                    damage += slotSelected.getCapability(CAPABILITY_TOOLMOD, null).getSharpLevel();
                    //Heat
                    if(slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasHeat()) {
                        heat++;
                    }
                }
            }
            //Checks Hotbar
            Iterable<ItemStack> hotBar = player.inventoryContainer.inventoryItemStacks.subList(36,45);
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
                    if (camo) {
                        PotionEffect camoEffect = new PotionEffect(MobEffects.INVISIBILITY, 200, 1);
                        player.addPotionEffect(camoEffect);
                    }
                    if (speed != 0) {
                        PotionEffect speedEffect = new PotionEffect(MobEffects.SPEED, 200, speed);
                    }
                    if (damage != 0) {
                        player.getCapability(CAPABILITY_PLAYERMOD, null).addAttackDamage((float)(.5 * damage));
                    }
                    if (heat != 0) {
                        player.getCapability(CAPABILITY_PLAYERMOD, null).addHeatDamage((float)(.5 * heat));
                    }
                }
            }
        }
    }
}
