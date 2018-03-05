package burn447.dartcraftReloaded.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by BURN447 on 2/14/2018.
 */
public class BlockForceLog extends BlockBase {

    public BlockForceLog(String name) {
        super(Material.WOOD, name);
        setHardness(2.5f);
        setResistance(12.5f);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public BlockForceLog setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


}
