package com.mattdahepic.extrarecords;

import com.mattdahepic.extrarecords.config.ERConfig;
import com.mattdahepic.extrarecords.config.data.InternalRecordData;
import com.mattdahepic.extrarecords.item.ItemExtraRecord;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void registerRenders () {}
    public void registerSounds () {}
    public void registerItems () {
        int recordNum = 0;
        for (InternalRecordData record : ERConfig.records) {
            record.setRecordNum(recordNum);
            ExtraRecords.records.add(recordNum, new ItemExtraRecord(record,recordNum));
            GameRegistry.registerItem(ExtraRecords.records.get(recordNum),"record"+recordNum);
            recordNum++;
        }
    }
}
