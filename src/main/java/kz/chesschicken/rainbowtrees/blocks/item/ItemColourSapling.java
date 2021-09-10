package kz.chesschicken.rainbowtrees.blocks.item;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.template.item.MetaBlock;

public class ItemColourSapling extends MetaBlock {
    public ItemColourSapling(int i) {
        super(i);
        this.setDurability(0);
        this.setHasSubItems(true);
    }

    public String getTranslationKey(ItemInstance item) {
        return getTranslationKey() + item.getDamage();
    }

    @Environment(EnvType.CLIENT)
    public int getTexturePosition(int damage) {
        return RainbowTreesListener.sapling_colour.getTextureForSide(0, damage);
    }

    public int getNameColour(int i) {
        return RainbowTreesListener.get16ColorCode(i);
    }
}
