package kz.chesschicken.rainbowtrees.blocks;

import kz.chesschicken.rainbowtrees.api.ColourTreeStructure;
import kz.chesschicken.rainbowtrees.blocks.item.ItemColourSapling;
import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplatePlant;

import java.util.Random;

@HasCustomBlockItemFactory(ItemColourSapling.class)
public class BlockColourSapling extends TemplatePlant {

    public BlockColourSapling(Identifier id) {
        super(id, 0);
        this.setHardness(0.0F);
        this.setSounds(GRASS_SOUNDS);
        this.setTicksRandomly(true);
        this.disableNotifyOnMetaDataChange();
        float var3 = 0.4F;
        this.setBoundingBox(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
    }

    @Override
    public int getColourMultiplier(BlockView tileView, int x, int y, int z) {
        return RainbowTreesListener.get16ColorCode(tileView.getTileMeta(x, y, z));
    }

    @Override
    public int getTextureForSide(int side) {
        return RainbowTreesListener.textColourSapling;
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        if (!level.isClient) {
            super.onScheduledTick(level, x, y, z, rand);
            if (level.getLightLevel(x, y + 1, z) >= 9 && rand.nextInt(90) == 0)
                    growMetaTree(level, x, y, z, rand, level.getTileMeta(x, y, z));
        }
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        if(player.getHeldItem() != null)
            if(player.getHeldItem().itemId == ItemBase.dyePowder.id)
                if(player.getHeldItem().getDamage() == 15) {
                    int var8 = level.getTileMeta(x, y, z);
                    (new ColourTreeStructure(var8)).generate(level, level.rand, x, y, z);
                    --player.getHeldItem().count;
                    return true;
                }
        return false;
    }

    public void growMetaTree(Level arg, int i, int j, int k, Random random, int metadata) {
        int meta = arg.getTileMeta(i, j, k);
        arg.setTileInChunk(i, j, k, 0);
        if ((new ColourTreeStructure(metadata).generate(arg, random, i, j, k) ? 1 : 0) == 0)
            arg.setTileWithMetadata(i, j, k, this.id, meta);
    }

    protected int droppedMeta(int i) {
        return i;
    }

}
