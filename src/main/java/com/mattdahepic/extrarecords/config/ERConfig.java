package com.mattdahepic.extrarecords.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mattdahepic.extrarecords.config.data.ConfigRecordData;
import com.mattdahepic.extrarecords.config.data.ConfigRecordData.RGBWrapper;
import com.mattdahepic.extrarecords.config.data.InternalRecordData;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ERConfig {
    public static File configDir;
    public static List<InternalRecordData> records = new ArrayList<InternalRecordData>();
    public static void init (FMLPreInitializationEvent e) {
        configDir = new File(e.getModConfigurationDirectory()+File.separator+"mattdahepic"+File.separator+"records"+File.separator);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            readConfig(configDir, gson);
        } catch (IOException ex) {
            throw new RuntimeException("Error parsing config. Cause: ",ex);
        }
    }
    private static void readConfig (File configDir, Gson gson) throws IOException {
        if (!configDir.exists()) {
            configDir.mkdirs();
            FileWriter out = new FileWriter(new File(configDir,"example_record.json"));
            //BEGIN DEFAULT RECORD
            ConfigRecordData defaultRecord = new ConfigRecordData("Example Record","records/example_record",new RGBWrapper(255,255,255));
            //END DEFAULT RECORD
            out.write(gson.toJson(defaultRecord));
            out.close();
        }
        for (File file : configDir.listFiles()) {
            if (file != null) {
                FileReader in = new FileReader(file);
                //PARSE FILES
                ConfigRecordData config = gson.fromJson(in,ConfigRecordData.class);
                records.add(config.toInternal());
                in.close();
            }
        }
    }
}
