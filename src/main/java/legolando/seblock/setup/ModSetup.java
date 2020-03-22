package legolando.seblock.setup;

import legolando.seblock.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {
    public ItemGroup itemGroup = new ItemGroup("seblock") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.itemSeboniumIngot);
        }
    };

    public void init() {

    }
}
