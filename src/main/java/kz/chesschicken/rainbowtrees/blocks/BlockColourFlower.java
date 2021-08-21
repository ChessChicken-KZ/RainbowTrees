package kz.chesschicken.rainbowtrees.blocks;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.block.HasMetaBlockItem;
import net.modificationstation.stationapi.api.block.MetaBlockItemProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplatePlant;

import java.util.Random;

@HasMetaBlockItem
public class BlockColourFlower extends TemplatePlant implements MetaBlockItemProvider {
    public BlockColourFlower(Identifier id) {
        super(id, RainbowTreesListener.textColourFlower);
    }

    @Override
    public int getTextureForSide(int side, int meta) {
        return RainbowTreesListener.textColourFlower;
    }

    @Override
    public int getColourMultiplier(BlockView tileView, int x, int y, int z) {
        return RainbowTreesListener.get16ColorCode(tileView.getTileMeta(x, y, z));
    }

    @Override
    public int getDropId(int meta, Random rand) { return RainbowTreesListener.flower_colour.id; }

    protected int droppedMeta(int i) {
        return i;
    }

}
