package legolando.seblock.item;

import legolando.seblock.Seblock;
import net.minecraft.item.Item;

public class ItemSeboniumIngot extends Item {

    public ItemSeboniumIngot() {
        super(new Item.Properties()
                .maxStackSize(64)

                .group(Seblock.setup.itemGroup));
        setRegistryName("sebonium_ingot");
    }
}
