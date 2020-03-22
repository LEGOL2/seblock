package legolando.seblock;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_POWER_SOLID_FUEL_GENERATOR = "solid_fuel_generator";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;


    public static ForgeConfigSpec.IntValue SOLID_FUEL_GENERATOR_MAXPOWER;
    public static ForgeConfigSpec.IntValue SOLID_FUEL_GENERATOR_GENERATE;
    public static ForgeConfigSpec.IntValue SOLID_FUEL_GENERATOR_BURN_TIME;

    static {
        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Power settings").push(CATEGORY_POWER);

        setupSolidFuelGeneratorConfig();

        COMMON_BUILDER.pop();


        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {

    }

    private static void setupSolidFuelGeneratorConfig() {
        COMMON_BUILDER.comment("Solid Fuel Generator settings").push(SUBCATEGORY_POWER_SOLID_FUEL_GENERATOR);
        SOLID_FUEL_GENERATOR_MAXPOWER = COMMON_BUILDER.comment("Maximum power storage")
                .defineInRange("maxStorage", 100000, 0, 1000000);
        SOLID_FUEL_GENERATOR_GENERATE = COMMON_BUILDER.comment("Power generated per tick")
                .defineInRange("generate", 30, 10, 200);
        SOLID_FUEL_GENERATOR_BURN_TIME = COMMON_BUILDER.comment("Burning time in ticks per fuel")
                .defineInRange("burn_time", 600, 100, 3600);
        COMMON_BUILDER.pop();
    }
}
