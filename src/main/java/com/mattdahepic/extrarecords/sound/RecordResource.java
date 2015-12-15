package com.mattdahepic.extrarecords.sound;

import com.google.common.collect.Sets;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;

public class RecordResource implements IResourcePack {
    public static final String PACK_NAME = "extrarecords_sounds";
    private static Map<String,String> sound_map = new HashMap<String,String>();
    private static File mc_dir = Minecraft.getMinecraft().mcDataDir;

    public InputStream getInputStream(ResourceLocation l) throws IOException {
        if (l.getResourcePath().equals("sounds.json")) return generateSoundsJSON();
        return new FileInputStream(new File(mc_dir,sound_map.get(l.getResourcePath())));
    }
    public boolean resourceExists(ResourceLocation location) {
        return isResourceFromThisPack(location) && new File(mc_dir,location.getResourcePath()).exists();
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
    private static InputStream generateSoundsJSON () {
        
        ByteArrayOutputStream mediator = new ByteArrayOutputStream()
	    ObjectOutputStream stream = new ObjectOutputStream(mediator);

        JsonObject root = new JsonObject();
        for (Map.Entry<String,String> entry: sound_map.entrySet()) {
            JsonObject event = new JsonObject();
            event.addProperty("category", "record"); // put under the "record" category for sound options
            JsonArray sounds = new JsonArray(); // array of sounds (will only ever be one)
            JsonObject sound = new JsonObject(); // sound object (instead of primitive to use 'stream' flag)
            sound.addProperty("name", entry.getValue()); // path to file
            sound.addProperty("stream", true); // prevents lag for large files
            sounds.add(sound);
            event.add("sounds", sounds);
            root.add("extrarecords."+entry.getKey(), event); // event name (same as name sent to ItemCustomRecord)
        }
        
	    stream.writeUTF(root.toString());
	    stream.flush();
	    IOUtils.closeQuietly(stream);
        
        return new ByteArrayInputStream(mediator.toByteArray[]);
    }

    public static void addSoundReferenceMapping (int recordNum, String pathToSound) {
        sound_map.put("record"+recordNum,pathToSound);
    }

    public static void registerAsResourceLocation () {
        List<IResourcePack> values = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
        values.add(new RecordResource());
        ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), values, "defaultResourcePacks", "field_110449_ao");
    }
}
