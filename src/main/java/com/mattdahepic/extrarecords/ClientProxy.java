package com.mattdahepic.extrarecords;

import com.mattdahepic.extrarecords.item.ItemExtraRecord;
import com.mattdahepic.extrarecords.sound.RecordResource;
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
    @Override
    public void registerSounds () {
        RecordResource r = new RecordResource();
        for (ItemExtraRecord record : ExtraRecords.records) {
            r.addSoundReferenceMapping(record.data.recordNum, record.data.sound.getResourcePath()); //add map soundlocation -> recordX
        }
        r.registerAsResourceLocation(); //finalise IResourcePack
    }
}
