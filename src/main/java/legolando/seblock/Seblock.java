package legolando.seblock;

import legolando.seblock.block.SolidFuelGenerator.BlockSolidFuelGenerator;
import legolando.seblock.block.ModBlocks;
import legolando.seblock.block.SeboniumBlock;
import legolando.seblock.block.SolidFuelGenerator.ContainerSolidFuelGenerator;
import legolando.seblock.item.ItemSeboniumIngot;
import legolando.seblock.setup.ClientProxy;
import legolando.seblock.setup.IProxy;
import legolando.seblock.setup.ModSetup;
import legolando.seblock.setup.ServerProxy;
import legolando.seblock.block.SolidFuelGenerator.TileSolidFuelGenerator;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("seblock")
public class Seblock {
    public static final String MODID = "seblock";
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ModSetup setup = new ModSetup();
    public static final Logger LOGGER = LogManager.getLogger();


    public Seblock() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("seblock-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("seblock-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Started Seblock preinit");

        setup.init();
        proxy.init();

        LOGGER.info("Finished Seblock preinit");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("Started Register Blocks job");

            blockRegistryEvent.getRegistry().registerAll(
                    new SeboniumBlock(),
                    new BlockSolidFuelGenerator()
            );

            LOGGER.info("Finished Register Blocks job");
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new block here
            LOGGER.info("Started Register Items job");

            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            itemRegistryEvent.getRegistry().registerAll(
                    new BlockItem(ModBlocks.seboniumBlock, properties).setRegistryName("sebonium"),
                    new BlockItem(ModBlocks.blockSolidFuelGenerator, properties).setRegistryName("solid_fuel_generator"),
                    new ItemSeboniumIngot()
            );

            LOGGER.info("Finished Register Items job");
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent) {
            tileEntityRegistryEvent.getRegistry().registerAll(
                    TileEntityType.Builder.create(TileSolidFuelGenerator::new, ModBlocks.blockSolidFuelGenerator)
                            .build(null).setRegistryName("solid_fuel_generator")
            );
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> containerTypeRegister) {
            containerTypeRegister.getRegistry().registerAll(
                    IForgeContainerType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        return new ContainerSolidFuelGenerator(windowId, Seblock.proxy.getClientWorld(), pos, inv, Seblock.proxy.getClientPlayer());
                    }).setRegistryName("solid_fuel_generator")
            );
        }
    }
}
