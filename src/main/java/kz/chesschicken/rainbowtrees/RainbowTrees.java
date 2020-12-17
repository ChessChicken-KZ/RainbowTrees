package kz.chesschicken.rainbowtrees;

import kz.chesschicken.rainbowtrees.api.ColourTreeStructure;
import kz.chesschicken.rainbowtrees.blocks.TileBlockColour_Flower;
import kz.chesschicken.rainbowtrees.blocks.TileBlockColour_Leaves;
import kz.chesschicken.rainbowtrees.blocks.TileBlockColour_Sapling;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.source.LevelSource;
import net.minecraft.level.source.OverworldLevelSource;
import net.modificationstation.stationloader.api.client.event.texture.TextureRegister;
import net.modificationstation.stationloader.api.client.texture.TextureFactory;
import net.modificationstation.stationloader.api.client.texture.TextureRegistry;
import net.modificationstation.stationloader.api.common.config.Category;
import net.modificationstation.stationloader.api.common.config.Property;
import net.modificationstation.stationloader.api.common.event.block.BlockRegister;
import net.modificationstation.stationloader.api.common.event.level.gen.ChunkPopulator;
import net.modificationstation.stationloader.api.common.event.recipe.RecipeRegister;
import net.modificationstation.stationloader.api.common.mod.StationMod;
import net.modificationstation.stationloader.api.common.recipe.CraftingRegistry;

import java.awt.*;
import java.util.Random;

public class RainbowTrees implements StationMod, BlockRegister, TextureRegister, ChunkPopulator, RecipeRegister
{
    @Override
    public void preInit() {
        TextureRegister.EVENT.register(this);
        BlockRegister.EVENT.register(this);
        ChunkPopulator.EVENT.register(this);
        RecipeRegister.EVENT.register(this);
    }

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


    @Override
    public void registerBlocks() {
        Category blockIdsCategory = getDefaultConfig().getCategory("Block IDs");
        Property blockSaplingCID = blockIdsCategory.getProperty("blockSaplingC", 99);
        Property blockLeavesCID = blockIdsCategory.getProperty("blockLeavesC", 100);
        Property blockFlowerCID = blockIdsCategory.getProperty("blockFlowerC", 101);

        blockSaplingC = new TileBlockColour_Sapling(blockSaplingCID.getIntValue()).setName("rainbowtrees:blockSaplingC");
        blockLeavesC = new TileBlockColour_Leaves(blockLeavesCID.getIntValue()).setName("rainbowtrees:blockLeavesC");
        blockFlowerC = new TileBlockColour_Flower(blockFlowerCID.getIntValue()).setName("rainbowtrees:blockFlowerC");
    }

    @Override
    public void registerTextures() {
        textColourSapling = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/rainbowtrees/textures/block/tileSapling.png");
        textColourLeaves = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/rainbowtrees/textures/block/tileLeaves.png");
        textColourLeaves_Fast = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/rainbowtrees/textures/block/tileLeavesOpaque.png");
        textColourFlower = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/rainbowtrees/textures/block/tileFlower.png");
    }

    @Override
    public void registerRecipes(String string) {
        Vanilla type = Vanilla.fromType(string);
        switch (type) {
            case CRAFTING_SHAPELESS: {
                for(int i = 0; i < 16; i++)
                {
                    CraftingRegistry.INSTANCE.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 1, 15 - i), new ItemInstance(blockFlowerC, 1, i));
                }

            }
        }
    }

    @Override
    public void populate(Level level, LevelSource levelSource, Biome biome, int i, int j, Random random) {
        if (levelSource.getClass() == OverworldLevelSource.class) {
            //cool method stolen from modloader
            for (int iq = 0; iq < 8; iq++) {
                int chunkpX = i + random.nextInt(16);
                int chunkpZ = j + random.nextInt(16);
                int chunkpY = random.nextInt(128);
                ColourTreeStructure structure = new ColourTreeStructure(random.nextInt(17));
                if (structure.generate(level, random, chunkpX, chunkpY, chunkpZ))
                    structure.generate(level, random, chunkpX, chunkpY, chunkpZ);
            }
        }
    }

    public static BlockBase blockSaplingC;
    public static BlockBase blockLeavesC;
    public static BlockBase blockFlowerC;

    public static int textColourSapling;
    public static int textColourLeaves;
    public static int textColourLeaves_Fast;
    public static int textColourFlower;

}
