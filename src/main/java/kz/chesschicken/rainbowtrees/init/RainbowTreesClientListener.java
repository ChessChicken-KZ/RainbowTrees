package kz.chesschicken.rainbowtrees.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;

public class RainbowTreesClientListener {
    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        RainbowTreesListener.textColourSapling = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/sapling_colour.png").index;
        RainbowTreesListener.textColourLeaves = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/leaves_colour.png").index;
        RainbowTreesListener.textColourLeaves_Fast = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/leaves_colour_opaque.png").index;
        RainbowTreesListener.textColourFlower = Atlases.getStationTerrain().addTexture("/assets/rainbowtrees/textures/block/flower_colour.png").index;
    }
}
