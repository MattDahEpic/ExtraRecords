package com.mattdahepic.extrarecords.sound;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class RecordResource implements IResourcePack {
    public static final String PACK_NAME = "extrarecords_sounds";
    private static Map<String,String> sound_map = new HashMap<String,String>();
    private static File mc_dir = Minecraft.getMinecraft().mcDataDir;

    public InputStream getInputStream(ResourceLocation l) throws IOException {
        if (l.getResourcePath().equals("sounds.json")) return generateSoundsJSON();
        return new FileInputStream(getRealPathBecauseMojangLiterallyCantEvenCodeOutsideTheirUsageScenario(l));
    }
    public boolean resourceExists(ResourceLocation l) {
        return isResourceFromThisPack(l) && (l.getResourcePath().equals("sounds.json") || getRealPathBecauseMojangLiterallyCantEvenCodeOutsideTheirUsageScenario(l).exists());
    }
    public Set<String> getResourceDomains() {
        return Sets.newHashSet(getPackName());
    }
    public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer p1, String p2) throws IOException {
        return null; //too hacky for this
    }
    public BufferedImage getPackImage() throws IOException {
        return null; //don't need no image for this shit
    }
    public String getPackName() {
        return PACK_NAME;
    }

   /** HELPERS */

    private boolean isResourceFromThisPack (ResourceLocation l) {
        return l.getResourceDomain().equals(getPackName());
    }
    private File getRealPathBecauseMojangLiterallyCantEvenCodeOutsideTheirUsageScenario (ResourceLocation l) {
        String altPath = l.getResourcePath();
        altPath = altPath.substring(7); //omit the leading "sounds/"
        System.out.println("Final path: "+new File(mc_dir.getAbsolutePath(),altPath).getAbsolutePath());
        return new File(mc_dir.getAbsolutePath(),altPath);
    }
    private static InputStream generateSoundsJSON () throws IOException {
        JsonObject root = new JsonObject();
        for (Map.Entry<String, String> entry : sound_map.entrySet()) {
            JsonObject event = new JsonObject();
            event.addProperty("category", "record"); // put under the "record" category for sound options
            JsonArray sounds = new JsonArray(); // array of sounds (will only ever be one)
            JsonObject sound = new JsonObject(); // sound object (instead of primitive to use 'stream' flag)
            sound.addProperty("name", entry.getValue()); // path to file
            sound.addProperty("stream", true); // prevents lag for large files
            sounds.add(sound);
            event.add("sounds", sounds);
            root.add("extrarecords." + entry.getKey(), event); // event name (same as name sent to ItemCustomRecord)
        }
        return new ByteArrayInputStream(new Gson().toJson(root).getBytes());
    }

    public void addSoundReferenceMapping (int recordNum, String pathToSound) {
        sound_map.put("record"+recordNum,pathToSound);
    }

    public void registerAsResourceLocation () {
        List<IResourcePack> values = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
        values.add(this);
        ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), values, "defaultResourcePacks", "field_110449_ao");
    }
}
