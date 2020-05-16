package legolando.seblock.datagen;

import legolando.seblock.block.ModBlocks;
import legolando.seblock.item.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.blockSolidFuelGenerator)
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', ModItems.itemSeboniumIngot)
                .key('#', Blocks.FURNACE)
                .setGroup("Seblock")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }
}
