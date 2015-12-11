package com.mattdahepic.extrarecords.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.*;

public class ERConfig {
    public transient static File configDir;

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
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(configDir,"example_record.json")));
            //BEGIN DEFAULT RECORD

            //END DEFAULT RECORD
            out.write(gson.toJson(defaultRecord));
        }
        for (File file : configDir.listFiles()) {
            if (file != null) {
                FileReader in = new FileReader(file);
                //PARSE FILES
                RecordData data = gson.fromJson(in,RecordData.class);
            }
        }
    }
}
