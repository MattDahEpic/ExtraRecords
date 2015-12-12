package com.mattdahepic.extrarecords.config;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class RecordResource implements IResourcePack {
    private static File mc_dir = Minecraft.getMinecraft().mcDataDir;
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        return new FileInputStream(mc_dir);
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
        return "extrarecords_sounds";
    }
    //HELPERS
    private boolean isResourceFromThisPack (ResourceLocation l) {
        return l.getResourceDomain().equals(getPackName());
    }
}
