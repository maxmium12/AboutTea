package com.maximum.abouttea.init;

import com.maximum.abouttea.tile.TileBookConverter;
import com.maximum.abouttea.tile.TileTeaGenerator;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.tile.TileWire;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import com.maximum.abouttea.tile.machine.TileMachineMixer;
import com.maximum.abouttea.tile.machine.TileTeaStoneWorkstation;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTiles {
    public static final RegistryObject<TileEntityType<TileTeaSet>> TEA_SET_TILE = RegistryUtil.registryTile("tea_set", () -> TileEntityType.Builder.create(TileTeaSet::new, ModBlock.blockTeaSet.get()).build(null));
    public static final RegistryObject<TileEntityType<TileWire>> WIRE_TILE = RegistryUtil.registryTile("wire", () -> TileEntityType.Builder.create(TileWire::new, ModBlock.blockWire.get()).build(null));
    public static final RegistryObject<TileEntityType<TileTeaGenerator>> TEA_GENERATOR_TILE = RegistryUtil.registryTile("tea_generator", () -> TileEntityType.Builder.create(TileTeaGenerator::new, ModBlock.blockTeaGenerator.get()).build(null));
    public static final RegistryObject<TileEntityType<TileBookConverter>> TEA_BOOK_CONVERTER_TILE = RegistryUtil.registryTile("tea_book_converter", () -> TileEntityType.Builder.create(TileBookConverter::new, ModBlock.blockTeaBookConverter.get()).build(null));
    public static final RegistryObject<TileEntityType<TileTeaDryer>> MANUAL_TEA_DRYER_TILE = RegistryUtil.registryTile("manual_tea_dryer", () -> TileEntityType.Builder.create(TileTeaDryer::new, ModBlock.blockManualTeaDryer.get()).build(null));
    public static final RegistryObject<TileEntityType<TileTeaMixer>> MANUAL_TEA_MIXER_TILE = RegistryUtil.registryTile("manual_tea_mixer", () -> TileEntityType.Builder.create(TileTeaMixer::new, ModBlock.blockManualTeaMixer.get()).build(null));
    public static final RegistryObject<TileEntityType<TileTeaStoneWorkstation>> TEA_STONE_WORKSTATION = RegistryUtil.registryTile("tea_stone_workstation", () -> TileEntityType.Builder.create(TileTeaStoneWorkstation::new, ModBlock.blockTeaStoneWorkStation.get()).build(null));
    public static final RegistryObject<TileEntityType<TileMachineDryer>> MACHINE_DRYER = RegistryUtil.registryTile("machine_dryer", () -> TileEntityType.Builder.create(TileMachineDryer::new, ModBlock.blockMachineDryer.get()).build(null));
    public static final RegistryObject<TileEntityType<TileMachineMixer>> MACHINE_MIXER = RegistryUtil.registryTile("machine_mixer", () -> TileEntityType.Builder.create(TileMachineMixer::new, ModBlock.blockMachineMixer.get()).build(null));
}
