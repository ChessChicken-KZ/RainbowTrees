package kz.chesschicken.rainbowtrees.init;

import kz.chesschicken.rainbowtrees.api.ColourTreeStructure;
import kz.chesschicken.rainbowtrees.blocks.BlockColourFlower;
import kz.chesschicken.rainbowtrees.blocks.BlockColourLeaves;
import kz.chesschicken.rainbowtrees.blocks.BlockColourSapling;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.level.gen.LevelGenEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.block.TemplateLeaves;
import net.modificationstation.stationapi.api.template.block.TemplatePlant;
import net.modificationstation.stationapi.api.util.Null;

public class RainbowTreesListener {

    public static int get16ColorCode(int i) {
        switch (i) {
            case 0: return 14540253; //221;221;221
            case 1: return 14384446; //219;125;62
            case 2: return 11751612; //179;80;188
            case 3: return 7047881; //107;138;201
            case 4: return 11642407; //177;166;39
            case 5: return 4301880; //65;164;56
            case 6: return 13665433; //208;132;153
            case 7: return 4210752; //64;64;64
            case 8: return 10133921; //154;161;161
            case 9: return 3042953; //46;110;137
            case 10: return 8273333; //126;61;181
            case 11: return 3029133; //46;56;141
            case 12: return 5190175; //79;50;31
            case 13: return 3491355; //53;70;27
            case 14: return 9843760; //150;52;48
            case 15: return 1644054; //25;22;22
        }
        return 0;
    }

    @Entrypoint.ModID
    public static ModID modID = Null.get();

    public static TemplatePlant sapling_colour;
    public static TemplateLeaves leaves_colour;
    public static TemplatePlant flower_colour;

    public static int textColourSapling;
    public static int textColourLeaves;
    public static int textColourLeaves_Fast;
    public static int textColourFlower;

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        sapling_colour = new BlockColourSapling(Identifier.of(modID, "sapling_colour")).setTranslationKey(modID, "sapling_colour");
        leaves_colour = new BlockColourLeaves(Identifier.of(modID, "leaves_colour")).setTranslationKey(modID, "leaves_colour");
        flower_colour = new BlockColourFlower(Identifier.of(modID, "flower_colour")).setTranslationKey(modID, "flower_colour");
    }

    @SuppressWarnings("unused")
    @EventListener
    public void generateTrees(LevelGenEvent.ChunkDecoration event) {
        if (event.level.dimension.id == 0) {
            for (int iq = 0; iq < 8; iq++) {
                int chunkpX = event.x + event.random.nextInt(16);
                int chunkpZ = event.z + event.random.nextInt(16);
                int chunkpY = event.random.nextInt(128);
                ColourTreeStructure structure = new ColourTreeStructure(event.random.nextInt(17));
                if (structure.generate(event.level, event.random, chunkpX, chunkpY, chunkpZ))
                    structure.generate(event.level, event.random, chunkpX, chunkpY, chunkpZ);
            }
        }
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (RecipeRegisterEvent.Vanilla.fromType(event.recipeId) == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS) {
            for (int i = 0; i < 16; i++)
                CraftingRegistry.addShapelessRecipe(
                        new ItemInstance(ItemBase.dyePowder, 1, 15 - i),
                        new ItemInstance(flower_colour, 1, i));
        }

    }

}
