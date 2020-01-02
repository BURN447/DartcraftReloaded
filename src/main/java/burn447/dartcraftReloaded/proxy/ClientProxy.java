package burn447.dartcraftReloaded.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ClientProxy implements IProxy {
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
