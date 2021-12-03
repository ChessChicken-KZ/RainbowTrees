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

    public ColourTreeStructure(int metadata) {
        this.selectedType = -1;
        this.selectedMetadata = metadata;
    }

    public ColourTreeStructure(int type, int metadata) {
        this.selectedType = type;
        this.selectedMetadata = metadata;
    }

    @Override
    public boolean generate(Level level, Random rand, int x, int y, int z) {
        if(selectedType < 0)
            selectedType = rand.nextInt(2);
        randomTreeBlock = rand.nextInt(3);

        if((level.isAir(x, y, z) || level.getTileId(x, y, z) == BlockBase.SNOW.id || level.getTileId(x, y, z) == RainbowTreesListener.sapling_colour.id) && level.getMaterial(x,y-1,z) == Material.ORGANIC) {
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
        int chanceHeight = rand.nextInt(3) + 4;
        if (y >= 1 && y + chanceHeight + 1 <= 128) {
            if ((level.getTileId(x, y - 1, z) == BlockBase.GRASS.id || level.getTileId(x, y - 1, z) == BlockBase.DIRT.id) && y < 128 - chanceHeight - 1) {
                level.setTileInChunk(x, y - 1, z, BlockBase.DIRT.id);

                for (int vb = y - 3 + chanceHeight; vb <= y + chanceHeight; ++vb) {
                    int am = 1 - (vb - (y + chanceHeight)) / 2;
                    for (int f = x - am; f <= x + am; ++f)
                        for (int a = z - am; a <= z + am; ++a)
                            if ((Math.abs(f - x) != am || Math.abs(a - z) != am || rand.nextInt(2) != 0 && (vb - (y + chanceHeight)) != 0) && !BlockBase.FULL_OPAQUE[level.getTileId(f, vb, a)])
                                level.setTileWithMetadata(f, vb, a, RainbowTreesListener.leaves_colour.id, selectedMetadata);
                }

                for (int a = 0; a < chanceHeight; ++a)
                    if (level.getTileId(x, y + a, z) == 0 || level.getTileId(x, y + a, z) == RainbowTreesListener.leaves_colour.id)
                        level.setTileWithMetadata(x, y + a, z, BlockBase.LOG.id, randomTreeBlock);
            }
        }
        if(level.getTileId(x, y, z) != BlockBase.LOG.id)
            level.setTileWithMetadata(x, y, z, BlockBase.LOG.id, randomTreeBlock);
    }



}