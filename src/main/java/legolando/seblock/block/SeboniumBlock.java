package legolando.seblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SeboniumBlock extends Block {
    public SeboniumBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2.0f)
                .lightValue(8)
        );
        setRegistryName("sebonium");
    }
}
