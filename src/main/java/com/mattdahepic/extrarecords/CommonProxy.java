package com.mattdahepic.extrarecords;

import com.mattdahepic.extrarecords.config.ERConfig;
import com.mattdahepic.extrarecords.config.data.InternalRecordData;
import com.mattdahepic.extrarecords.item.ItemExtraRecord;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void registerItems () {
        int recordNum = 0;
        for (InternalRecordData record : ERConfig.records) {
            GameRegistry.registerItem(new ItemExtraRecord(record,recordNum),"record"+recordNum,ExtraRecords.MODID);
            recordNum++;
        }
    }
}
