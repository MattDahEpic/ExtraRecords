package com.mattdahepic.extrarecords.item;

import com.mattdahepic.extrarecords.config.data.InternalRecordData;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExtraRecord extends ItemRecord {
    private final InternalRecordData data;
    public ItemExtraRecord (InternalRecordData data, int recordNum) {
        super("extrarecords_record"+recordNum);
        this.data = data;
    }
    @Override
    public ResourceLocation getRecordResource(String name) {
        return data.sound;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return data.name;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return renderPass == 1 ? data.color.getRGB() : 16777215;
    }
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return StatCollector.translateToLocal("item.record.name");
    }
}
