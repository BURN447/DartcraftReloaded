package burn447.dartcraftReloaded.Fluids;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by BURN447 on 6/22/2018.
 */
public class ModFluids {

    public static FluidForce fluidForce = new FluidForce();

    public static Fluid fluidForceBlock;

    public static void setUpFluids(){
        fluidForce = new FluidForce();
        fluidForce.setBlock(Blocks.WATER);
    }

    public static void registerFluids(){
        FluidRegistry.registerFluid(fluidForce);
    }
}
