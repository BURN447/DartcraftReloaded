package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Networking.InfuserMessage;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by BURN447 on 5/27/2018.
 */
public class DCRPacketHandler {

    public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(References.modId);

    private static int id = 0;

    public static void init(){
        packetHandler.registerMessage(InfuserMessage.class, InfuserMessage.class, id++, Side.SERVER);
    }

    public static void sendToServer(IMessage message){
        packetHandler.sendToServer(message);
    }

    public static void sendToClient(IMessage message, EntityPlayerMP player){
        packetHandler.sendTo(message, player);
    }
}
