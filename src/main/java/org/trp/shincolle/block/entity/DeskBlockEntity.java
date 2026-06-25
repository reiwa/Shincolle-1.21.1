package org.trp.shincolle.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.menu.DeskMenu;

public class DeskBlockEntity extends BlockEntity implements MenuProvider {
    private int guiFunc = 0;
    private int radarZoomLv = 0;
    private int bookChap = 0;
    private int bookPage = 0;

    public DeskBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DESK.get(), pos, blockState);
    }

    public int getGuiFunc() {
        return this.guiFunc;
    }

    public void setGuiFunc(int guiFunc) {
        this.guiFunc = guiFunc;
        setChanged();
    }

    public int getRadarZoomLv() {
        return this.radarZoomLv;
    }

    public void setRadarZoomLv(int radarZoomLv) {
        this.radarZoomLv = radarZoomLv;
        setChanged();
    }

    public int getBookChap() {
        return this.bookChap;
    }

    public void setBookChap(int bookChap) {
        this.bookChap = bookChap;
        setChanged();
    }

    public int getBookPage() {
        return this.bookPage;
    }

    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("guiFunc", this.guiFunc);
        tag.putInt("radarZoom", this.radarZoomLv);
        tag.putInt("bookChap", this.bookChap);
        tag.putInt("bookPage", this.bookPage);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.guiFunc = tag.getInt("guiFunc");
        this.radarZoomLv = tag.getInt("radarZoom");
        this.bookChap = tag.getInt("bookChap");
        this.bookPage = tag.getInt("bookPage");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.shincolle.blockdesk");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new DeskMenu(containerId, playerInventory, 0, this.bookChap, this.bookPage, this.guiFunc, this.radarZoomLv, this);
    }
}
