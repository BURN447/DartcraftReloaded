package burn447.dartcraftReloaded.Handlers;

<<<<<<< HEAD
import burn447.dartcraftReloaded.client.gui.furnace.GUIFurnace;
=======
>>>>>>> master
import burn447.dartcraftReloaded.client.gui.infuser.GUIInfuser;
import burn447.dartcraftReloaded.container.ContainerBlockFurnace;
import burn447.dartcraftReloaded.container.ContainerBlockInfuser;
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


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == INFUSER){
            return new GUIInfuser(player.inventory, (TileEntityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
        }
        else if(ID == FURNACE) {
            return new GUIFurnace(player.inventory, (TileEntityForceFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == INFUSER) {
            return new ContainerBlockInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));
        }
        else if (ID == FURNACE) {
            return new ContainerBlockFurnace(player.inventory, (TileEntityForceFurnace)world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
