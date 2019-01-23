package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Items.ItemForcePack;
import burn447.dartcraftReloaded.client.gui.furnace.GUIFurnace;
import burn447.dartcraftReloaded.client.gui.infuser.GUIInfuser;
import burn447.dartcraftReloaded.client.gui.pack.GUIForcePack;
import burn447.dartcraftReloaded.container.ContainerBlockFurnace;
import burn447.dartcraftReloaded.container.ContainerBlockInfuser;
import burn447.dartcraftReloaded.container.ContainerItemForcePack;
import burn447.dartcraftReloaded.tileEntity.TileEntityForceFurnace;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class DCRGUIHandler implements IGuiHandler {

    public static final int INFUSER = 0;
    public static final int FURNACE = 1;
    public static final int PACK = 2;


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == INFUSER){
            return new GUIInfuser(player.inventory, (TileEntityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
        }
        else if(ID == FURNACE) {
            return new GUIFurnace(player.inventory, (TileEntityForceFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        }
        else if (ID == PACK) {
            return new GUIForcePack(player.inventory, player.getHeldItemMainhand());
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == INFUSER) {
            return new ContainerBlockInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));
        } else if (ID == FURNACE) {
            return new ContainerBlockFurnace(player.inventory, (TileEntityForceFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        } else if (ID == PACK) {
            return new ContainerItemForcePack(player.inventory, player.getHeldItemMainhand());
        }
        return null;
    }
}
