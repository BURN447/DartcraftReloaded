package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * Created by BURN447 on 3/26/2018.
 */
public class ItemToolBase extends ItemTool {

    String name;

    public ItemToolBase(String name, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn){
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
    }

    public float getAttackDamage(){
        return attackDamage;
    }

    public float getAttackSpeed(){
        return attackSpeed;
    }

    public void setAttackDamage(float newDamage){
        attackDamage = newDamage;
    }

    public void setAttackSpeed(float newSpeed){
        attackSpeed = newSpeed;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }
}
