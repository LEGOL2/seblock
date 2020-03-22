package legolando.seblock.setup;

import legolando.seblock.block.ModBlocks;
import legolando.seblock.block.SolidFuelGenerator.ScreenSolidFuelGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.containerSolidFuelGenerator, ScreenSolidFuelGenerator::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
