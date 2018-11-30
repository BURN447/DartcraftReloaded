package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGuideBook extends ItemBase {

    public ItemGuideBook(String name) {
        super(name);
        this.name = name;
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    @Override
    public void registerItemModel() {
        super.registerItemModel();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            dartcraftReloaded.proxy.openGuideGUI();
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }





}
