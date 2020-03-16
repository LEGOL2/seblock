package legolando.seblock.setup;

import legolando.seblock.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {
    public ItemGroup itemGroup = new ItemGroup("seblock") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.seboniumBlock);
        }
    };

    public void init() {

    }
}
