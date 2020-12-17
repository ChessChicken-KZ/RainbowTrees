package kz.chesschicken.rainbowtrees.blocks;

import kz.chesschicken.rainbowtrees.RainbowTrees;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.PlaceableTileEntity;

public class TileItemColour_Leaves extends PlaceableTileEntity {
    public TileItemColour_Leaves(int i) {
        super(i);
        this.setDurability(0);
        this.setHasSubItems(true);
    }

    public int getMetaData(int i) {
        return i;
    }

    @Environment(EnvType.CLIENT) @Override public String getTranslationKey(ItemInstance item) {
        return getTranslationKey() + item.getDamage();
    }

    @Environment(EnvType.CLIENT) @Override
    public int getTexturePosition(int damage) {
        return RainbowTrees.textColourLeaves;
    }

    @Override public int getNameColour(int i) { return RainbowTrees.get16ColorCode(i); }
}
