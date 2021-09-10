package kz.chesschicken.rainbowtrees.init;

import kz.chesschicken.rainbowtrees.api.ColourTreeStructure;
import kz.chesschicken.rainbowtrees.blocks.BlockColourFlower;
import kz.chesschicken.rainbowtrees.blocks.BlockColourLeaves;
import kz.chesschicken.rainbowtrees.blocks.BlockColourSapling;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.source.OverworldLevelSource;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
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

import java.awt.*;

public class RainbowTreesListener {

    public static int get16ColorCode(int colortype)
    {
        switch (colortype)
        {
            case 0:
                return new Color(221,221,221).getRGB();
            case 1:
                return new Color(219,125,62).getRGB();
            case 2:
                return new Color(179,80,188).getRGB();
            case 3:
                return new Color(107,138,201).getRGB();
            case 4:
                return new Color(177,166,39).getRGB();
            case 5:
                return new Color(65,164,56).getRGB();
            case 6:
                return new Color(208,132,153).getRGB();
            case 7:
                return new Color(64,64,64).getRGB();
            case 8:
                return new Color(154,161,161).getRGB();
            case 9:
                return new Color(46,110,137).getRGB();
            case 10:
                return new Color(126,61,181).getRGB();
            case 11:
                return new Color(46,56,141).getRGB();
            case 12:
                return new Color(79,50,31).getRGB();
            case 13:
                return new Color(53,70,27).getRGB();
            case 14:
                return new Color(150,52,48).getRGB();
            case 15:
                return new Color(25,22,22).getRGB();
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
    public void registerTextures(TextureRegisterEvent event) {
        textColourSapling = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/sapling_colour.png").index;
        textColourLeaves = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/leaves_colour.png").index;
        textColourLeaves_Fast = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/leaves_colour_opaque.png").index;
        textColourFlower = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/flower_colour.png").index;
    }

    @SuppressWarnings("unused")
    @EventListener
    public void generateTrees(LevelGenEvent.ChunkDecoration chunkDecoration)
    {
        if (chunkDecoration.levelSource.getClass() == OverworldLevelSource.class)
        {
            for (int iq = 0; iq < 8; iq++) {
                int chunkpX = chunkDecoration.x + chunkDecoration.random.nextInt(16);
                int chunkpZ = chunkDecoration.z + chunkDecoration.random.nextInt(16);
                int chunkpY = chunkDecoration.random.nextInt(128);
                ColourTreeStructure structure = new ColourTreeStructure(chunkDecoration.random.nextInt(17));
                if (structure.generate(chunkDecoration.level, chunkDecoration.random, chunkpX, chunkpY, chunkpZ))
                    structure.generate(chunkDecoration.level, chunkDecoration.random, chunkpX, chunkpY, chunkpZ);
            }
        }
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event)
    {
        if (RecipeRegisterEvent.Vanilla.fromType(event.recipeId) == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS) {
            for (int i = 0; i < 16; i++)
                CraftingRegistry.addShapelessRecipe(
                        new ItemInstance(ItemBase.dyePowder, 1, 15 - i),
                        new ItemInstance(flower_colour, 1, i));
        }

    }

}
