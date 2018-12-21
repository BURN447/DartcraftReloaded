package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by BURN447 on 6/2/2018.
 */
public class ItemFortune extends ItemBase {

    private String name;
    private String message;
    private String[] fortunes = new String[21];

    public ItemFortune(String name) {
        super(name);
        this.name = name;
        this.setCreativeTab(dartcraftReloaded.creativeTab);

        Arrays.fill(fortunes, "0");

        for(int i = 0; i < 21; i++) {
            fortunes[i] = "text.dartcraftReloaded.fortune" + i;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        NBTTagCompound nbt;
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(stack.hasTagCompound()) {
            nbt = stack.getTagCompound();
        }
        else {
            nbt = new NBTTagCompound();
        }

        if(!nbt.hasKey("message")) {
            addMessage(stack, nbt);
        }

        if(!worldIn.isRemote){
            playerIn.sendMessage(new TextComponentString(I18n.format(nbt.getString("message"))));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void addMessage(ItemStack stack, NBTTagCompound nbt) {
        Random generator = new Random();
        int rand = generator.nextInt(fortunes.length);
        nbt.setString("message", fortunes[rand]);
        stack.setTagCompound(nbt);
    }
}
