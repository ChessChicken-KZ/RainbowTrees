package kz.chesschicken.rainbowtrees.blocks.item.ext;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.template.item.MetaBlock;

public class ItemColour extends MetaBlock {
    public ItemColour(int i) {
        super(i);
        this.setDurability(0);
        this.setHasSubItems(true);
    }

    public String getTranslationKey(ItemInstance item) {
        return getTranslationKey() + item.getDamage();
    }

    public int getNameColour(int i) {
        return RainbowTreesListener.get16ColorCode(i);
    }
}
