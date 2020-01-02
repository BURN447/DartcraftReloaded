package burn447.dartcraftReloaded.Fluids;

import burn447.dartcraftReloaded.util.References;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by BURN447 on 6/22/2018.
 */
public class FluidForce extends Fluid {

    private String name = "force";

    protected static int mapColor = 0xFFFFFFFF;
    protected static float overlayAlpha = 0.2F;
    protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
    protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
    protected static Material material = Material.WATER;


    public FluidForce(){
        super("force", new ResourceLocation(References.modId, "fluids/force_still"), new ResourceLocation(References.modId, "fluids/force_flow"));

        setRarity(EnumRarity.COMMON);
        setLuminosity(0);
        setDensity(2000);
        setViscosity(1000);
        setTemperature(120);
        setFillSound(SoundEvents.ITEM_BUCKET_FILL);
        FluidRegistry.addBucketForFluid(this);
    }

    @Override
    public int getColor() {
        return mapColor;
    }

    @Override
    public Fluid setEmptySound(SoundEvent parSound) {
        emptySound = parSound;
        return this;
    }

    @Override
    public Fluid setFillSound(SoundEvent parSound) {
        fillSound = parSound;
        return this;
    }

    @Override
    public SoundEvent getFillSound() {
        return fillSound;
    }

    @Override
    public SoundEvent getEmptySound() {
        return emptySound;
    }

    public Material getMaterial()
    {
        return material;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack)
    {
        if (block == null)
            return false;
        return block.getDefaultState().getMaterial() == getMaterial();
    }
}
