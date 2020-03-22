package legolando.seblock.block;

import legolando.seblock.container.ContainerSolidFuelGenerator;
import legolando.seblock.tile.TileSolidFuelGenerator;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    @ObjectHolder("seblock:sebonium")
    public static SeboniumBlock seboniumBlock;

    @ObjectHolder("seblock:solid_fuel_generator")
    public static BlockSolidFuelGenerator blockSolidFuelGenerator;
    @ObjectHolder("seblock:solid_fuel_generator")
    public static TileEntityType<TileSolidFuelGenerator> tileSolidFuelGenerator;
    @ObjectHolder("seblock:solid_fuel_generator")
    public static ContainerType<ContainerSolidFuelGenerator> containerSolidFuelGenerator;
}
