package com.mattdahepic.extrarecords;

import com.mattdahepic.extrarecords.item.RisenYeastRecordModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenders () {
        for (Item record : ExtraRecords.records) {
            ModelLoader.setCustomModelResourceLocation(record,ExtraRecords.records.indexOf(record),new RisenYeastRecordModel());
        }
    }
}
