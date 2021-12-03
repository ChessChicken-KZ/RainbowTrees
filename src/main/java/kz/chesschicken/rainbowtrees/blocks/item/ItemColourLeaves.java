package kz.chesschicken.rainbowtrees.blocks.item;

import kz.chesschicken.rainbowtrees.blocks.item.ext.ItemColour;
import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class ItemColourLeaves extends ItemColour {

    public ItemColourLeaves(int i) {
        super(i);
    }

    @Environment(EnvType.CLIENT)
    public int getTexturePosition(int damage) {
        return RainbowTreesListener.textColourLeaves;
    }
}
