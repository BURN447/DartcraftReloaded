package burn447.dartcraftReloaded.proxy;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on Client");
    }
}
