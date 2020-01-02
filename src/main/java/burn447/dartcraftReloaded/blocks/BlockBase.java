package burn447.dartcraftReloaded.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class BlockBase extends Block {

    protected String name;
    protected String oreName;

    public BlockBase(Material material, String name, String oreName) {
        super(Properties.create(material).hardnessAndResistance(2.0F));
        this.name = name;
        this.oreName = oreName;
        this.setRegistryName(name);
    }

    public BlockBase(Material material, String name, String oreName, SoundType sound) {
        super(Properties.create(material).hardnessAndResistance(2.0F).sound(sound));
        this.name = name;
        this.oreName = oreName;
        this.setRegistryName(name);
    }

    public BlockBase(Material material, String name) {
        super(Properties.create(material).hardnessAndResistance(2.0F));
        this.name = name;
        this.oreName = oreName;
        this.setRegistryName(name);
    }
}
