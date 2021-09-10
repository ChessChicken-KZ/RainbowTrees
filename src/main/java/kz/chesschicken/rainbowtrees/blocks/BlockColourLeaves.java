package kz.chesschicken.rainbowtrees.blocks;

import kz.chesschicken.rainbowtrees.init.RainbowTreesListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateLeaves;

import java.util.Random;

@HasCustomBlockItemFactory(ItemColourLeaves.class)
public class BlockColourLeaves extends TemplateLeaves {

    public BlockColourLeaves(Identifier i) {
        super(i, RainbowTreesListener.textColourLeaves_Fast);
        this.setTicksRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSounds(GRASS_SOUNDS);
        this.disableStat();
        this.disableNotifyOnMetaDataChange();
        this.setTicksRandomly(true);
    }

    @Override
    public int getBaseColour(int i) {
        return RainbowTreesListener.get16ColorCode(i);
    }

    @Override
    public int getColourMultiplier(BlockView tileView, int x, int y, int z) {
        return RainbowTreesListener.get16ColorCode(tileView.getTileMeta(x,y,z));
    }

    public void onBlockRemoved(Level level, int x, int y, int z) {
        byte var5 = 1;
        int var6 = var5 + 1;
        if (level.method_155(x - var6, y - var6, z - var6, x + var6, y + var6, z + var6)) {
            for(int var7 = -var5; var7 <= var5; ++var7) {
                for(int var8 = -var5; var8 <= var5; ++var8) {
                    for(int var9 = -var5; var9 <= var5; ++var9) {
                        int var10 = level.getTileId(x + var7, y + var8, z + var9);
                        if (var10 == RainbowTreesListener.leaves_colour.id) {
                            int var11 = level.getTileMeta(x + var7, y + var8, z + var9);
                            level.method_223(x + var7, y + var8, z + var9, var11);
                        }
                    }
                }
            }
        }

    }

    private int[] collection;
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        if (!level.isClient) {
            int meta = level.getTileMeta(x, y, z);
            if ((meta & 8) != 0) {
                byte var7 = 4;
                int var8 = var7 + 1;
                byte var9 = 32;
                int var10 = var9 * var9;
                int var11 = var9 / 2;
                if (this.collection == null)
                    this.collection = new int[var9 * var9 * var9];


                int var12;
                if (level.method_155(x - var8, y - var8, z - var8, x + var8, y + var8, z + var8)) {
                    var12 = -var7;

                    label_tree:
                    while(true) {
                        int loop1;
                        int loop2;
                        int loop3;
                        if (var12 > var7) {
                            var12 = 1;

                            while(true) {
                                if (var12 > 4) {
                                    break label_tree;
                                }

                                for(loop1 = -var7; loop1 <= var7; ++loop1) {
                                    for(loop2 = -var7; loop2 <= var7; ++loop2) {
                                        for(loop3 = -var7; loop3 <= var7; ++loop3) {
                                            if (this.collection[(loop1 + var11) * var10 + (loop2 + var11) * var9 + loop3 + var11] == var12 - 1) {
                                                if (this.collection[(loop1 + var11 - 1) * var10 + (loop2 + var11) * var9 + loop3 + var11] == -2) {
                                                    this.collection[(loop1 + var11 - 1) * var10 + (loop2 + var11) * var9 + loop3 + var11] = var12;
                                                }

                                                if (this.collection[(loop1 + var11 + 1) * var10 + (loop2 + var11) * var9 + loop3 + var11] == -2) {
                                                    this.collection[(loop1 + var11 + 1) * var10 + (loop2 + var11) * var9 + loop3 + var11] = var12;
                                                }

                                                if (this.collection[(loop1 + var11) * var10 + (loop2 + var11 - 1) * var9 + loop3 + var11] == -2) {
                                                    this.collection[(loop1 + var11) * var10 + (loop2 + var11 - 1) * var9 + loop3 + var11] = var12;
                                                }

                                                if (this.collection[(loop1 + var11) * var10 + (loop2 + var11 + 1) * var9 + loop3 + var11] == -2) {
                                                    this.collection[(loop1 + var11) * var10 + (loop2 + var11 + 1) * var9 + loop3 + var11] = var12;
                                                }

                                                if (this.collection[(loop1 + var11) * var10 + (loop2 + var11) * var9 + (loop3 + var11 - 1)] == -2) {
                                                    this.collection[(loop1 + var11) * var10 + (loop2 + var11) * var9 + (loop3 + var11 - 1)] = var12;
                                                }

                                                if (this.collection[(loop1 + var11) * var10 + (loop2 + var11) * var9 + loop3 + var11 + 1] == -2) {
                                                    this.collection[(loop1 + var11) * var10 + (loop2 + var11) * var9 + loop3 + var11 + 1] = var12;
                                                }
                                            }
                                        }
                                    }
                                }

                                ++var12;
                            }
                        }

                        for(loop1 = -var7; loop1 <= var7; ++loop1) {
                            for(loop2 = -var7; loop2 <= var7; ++loop2) {
                                loop3 = level.getTileId(x + var12, y + loop1, z + loop2);
                                if (loop3 == BlockBase.LOG.id) {
                                    this.collection[(var12 + var11) * var10 + (loop1 + var11) * var9 + loop2 + var11] = 0;
                                } else if (loop3 == RainbowTreesListener.leaves_colour.id) {
                                    this.collection[(var12 + var11) * var10 + (loop1 + var11) * var9 + loop2 + var11] = -2;
                                } else {
                                    this.collection[(var12 + var11) * var10 + (loop1 + var11) * var9 + loop2 + var11] = -1;
                                }
                            }
                        }

                        ++var12;
                    }
                }

                var12 = this.collection[var11 * var10 + var11 * var9 + var11];
                if (var12 >= 0) {
                    level.method_223(x, y, z, meta);
                } else {
                    this.method_990(level, x, y, z);
                }
            }

        }
    }

    private void method_990(Level arg, int i, int j, int k) {
        this.drop(arg, i, j, k, arg.getTileMeta(i, j, k));
        arg.setTile(i, j, k, 0);
    }

    public int getDropCount(Random rand) {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

    public int getDropId(int meta, Random rand) {
        return RainbowTreesListener.sapling_colour.id;
    }

    public void afterBreak(Level arg, PlayerBase arg1, int x, int y, int z, int i1) {
        if (!arg.isClient && arg1.getHeldItem() != null && arg1.getHeldItem().itemId == ItemBase.shears.id) {
            arg1.increaseStat(Stats.mineBlock[this.id], 1);
            this.drop(arg, x, y, z, new ItemInstance(RainbowTreesListener.leaves_colour.id, 1, i1));
        } else {
            super.afterBreak(arg, arg1, x, y, z, i1);
        }

    }

    protected int droppedMeta(int i) {
        return i;
    }

    public boolean isFullOpaque() {
        return !this.isTransparent;
    }

    public int getTextureForSide(int side, int meta) {
        return RainbowTreesListener.textColourLeaves_Fast;
    }

    @Environment(EnvType.CLIENT)
    public void method_991(boolean flag) {
        this.isTransparent = flag;
        this.texture = flag ? RainbowTreesListener.textColourLeaves : RainbowTreesListener.textColourLeaves_Fast;
    }
}
