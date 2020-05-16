package legolando.seblock.datagen;

import legolando.seblock.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void addTables() {
        lootTables.put(ModBlocks.blockSolidFuelGenerator, createStandardTable("solidFuelGenerator",
                ModBlocks.blockSolidFuelGenerator));
    }
}
