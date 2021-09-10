package kz.chesschicken.rainbowtrees.blocks;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.template.item.MetaBlock;

public class TileItemColour_Flower extends MetaBlock {
    public TileItemColour_Flower(int i) {
        super(i);
        this.setDurability(0);
        this.setHasSubItems(true);
    }

    @Environment(EnvType.CLIENT)
    public String getTranslationKey(ItemInstance item) {
        return getTranslationKey() + item.getDamage();
    }

    @Environment(EnvType.CLIENT)
    public int getTexturePosition(int damage) {
        return RainbowTreesListener.flower_colour.getTextureForSide(0, damage);
    }

    @Override public int getNameColour(int i) { return RainbowTreesListener.get16ColorCode(i); }
}
