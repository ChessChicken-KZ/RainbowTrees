package kz.chesschicken.rainbowtrees.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;

public class RainbowTreesClientListener {

    private int blockTexture(String s) {
        return Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/" + s + ".png").index;
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        RainbowTreesListener.textColourSapling = blockTexture("sapling_colour");
        RainbowTreesListener.textColourLeaves = blockTexture("leaves_colour");
        RainbowTreesListener.textColourLeaves_Fast = blockTexture("leaves_colour_opaque");
        RainbowTreesListener.textColourFlower = blockTexture("flower_colour");
    }

}
