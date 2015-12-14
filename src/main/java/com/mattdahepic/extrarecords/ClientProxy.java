package com.mattdahepic.extrarecords;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenders () {
        for (Item record : ExtraRecords.records) {
            ModelLoader.setCustomModelResourceLocation(record, 0, new ModelResourceLocation("extrarecords:record", "inventory"));
            ModelBakery.addVariantName(record, "extrarecords:record");
        }
    }
}
