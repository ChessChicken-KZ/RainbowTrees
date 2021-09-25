package kz.chesschicken.rainbowtrees.api;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

import java.util.Random;

public class ColourTreeStructure extends Structure {
    public int randomTreeBlock;
    private int selectedType;
    private final int selectedMetadata;

    public ColourTreeStructure(int metadata)
    {
        selectedMetadata = metadata;
    }

    public ColourTreeStructure(int type, int metadata)
    {
        this.selectedType = type;
        this.selectedMetadata = metadata;
    }

    @Override
    public boolean generate(Level level, Random rand, int x, int y, int z) {
        selectedType = rand.nextInt(2);
        randomTreeBlock = rand.nextInt(3);

        if((level.isAir(x, y, z) || level.getTileId(x, y, z) == BlockBase.SNOW.id || level.getTileId(x, y, z) == RainbowTreesListener.sapling_colour.id) && level.getMaterial(x,y-1,z) == Material.ORGANIC)
        {
            if(selectedType == 0)
                generateType1(level, rand, x, y, z);
            else
                generateType2(level, rand, x, y, z);
            return true;
        }
        return false;
    }

    public void generateType1(Level level, Random rand, int x, int y, int z)
    {
        int h1 = rand.nextInt(5);
        if(h1<3)
            h1 = 3;
        for(int i1 = 0; i1 < h1; i1++)
            if(i1 == 0)
                for(int i2 = 0; i2 < 3; i2++)
                    level.setTileWithMetadata(x,y+i2,z,BlockBase.LOG.id, randomTreeBlock);
            else {
                for (int x1 = 0; x1 < 3; x1++)
                    for (int z1 = 0; z1 < 3; z1++)
                        for (int y1 = 0; y1 < 3; y1++)
                            level.setTileWithMetadata(x - 1 + x1, y + (i1 * 3) + y1, z - 1 + z1, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                level.setTileWithMetadata(x - 2, y + (i1 * 3) + 1, z, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                level.setTileWithMetadata(x + 2, y + (i1 * 3) + 1, z, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                level.setTileWithMetadata(x, y + (i1 * 3) + 1, z - 2, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                level.setTileWithMetadata(x, y + (i1 * 3) + 1, z + 2, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                level.setTileWithMetadata(x, y + (i1 * 3) + 3, z, RainbowTreesListener.leaves_colour.id, selectedMetadata);

                for(int i2 = 0; i2 < 2; i2++)
                {
                    level.setTile(x - 1, y + (i1 * 3) + (i2*2), z - 1, 0);
                    level.setTile(x + 1, y + (i1 * 3) + (i2*2), z - 1, 0);
                    level.setTile(x - 1, y + (i1 * 3) + (i2*2), z + 1, 0);
                    level.setTile(x + 1, y + (i1 * 3) + (i2*2), z + 1, 0);
                }
                for (int y1 = 0; y1 < 4; y1++)
                    level.setTileWithMetadata(x, y + (i1 * 3) - 1 + y1, z, BlockBase.LOG.id, randomTreeBlock);
            }
    }

    public void generateType2(Level level, Random rand, int x, int y, int z)
    {
        int var6 = rand.nextInt(3) + 4;
        boolean b = true;
        if (y >= 1 && y + var6 + 1 <= 128) {
            int i;
            int q;
            int var11;
            int var12;
            for(i = y; i <= y + 1 + var6; ++i) {
                byte sz = 1;
                if (i == y) {
                    sz = 0;
                }

                if (i >= y + 1 + var6 - 2) {
                    sz = 2;
                }

                for(q = x - sz; q <= x + sz && b; ++q) {
                    for(var11 = z - sz; var11 <= z + sz && b; ++var11) {
                        if (i >= 0 && i < 128) {
                            var12 = level.getTileId(q, i, var11);
                            if (var12 != 0 && var12 != RainbowTreesListener.leaves_colour.id) {
                                b = false;
                            }
                        } else {
                            b = false;
                        }
                    }
                }
            }

                i = level.getTileId(x, y - 1, z);
                if ((i == BlockBase.GRASS.id || i == BlockBase.DIRT.id) && y < 128 - var6 - 1) {
                    level.setTileInChunk(x, y - 1, z, BlockBase.DIRT.id);

                    int var16;
                    for (var16 = y - 3 + var6; var16 <= y + var6; ++var16) {
                        q = var16 - (y + var6);
                        var11 = 1 - q / 2;

                        for (var12 = x - var11; var12 <= x + var11; ++var12) {
                            int var13 = var12 - x;

                            for (int var14 = z - var11; var14 <= z + var11; ++var14) {
                                int var15 = var14 - z;
                                if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || rand.nextInt(2) != 0 && q != 0) && !BlockBase.FULL_OPAQUE[level.getTileId(var12, var16, var14)]) {
                                    level.setTileWithMetadata(var12, var16, var14, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                                }
                            }
                        }
                    }

                    for (var16 = 0; var16 < var6; ++var16) {
                        q = level.getTileId(x, y + var16, z);
                        if (q == 0 || q == RainbowTreesListener.leaves_colour.id) {
                            level.setTileWithMetadata(x, y + var16, z, BlockBase.LOG.id, randomTreeBlock);
                        }
                    }
                }

        }
        if(level.getTileId(x, y, z) != BlockBase.LOG.id)
            level.setTileWithMetadata(x, y, z, BlockBase.LOG.id, randomTreeBlock);
    }



}
