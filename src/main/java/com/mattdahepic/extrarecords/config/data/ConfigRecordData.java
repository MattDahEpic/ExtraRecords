package com.mattdahepic.extrarecords.config.data;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class ConfigRecordData {
    public String name;
    public String sound;
    public RGBWrapper color;

    public ConfigRecordData (String name, String sound, RGBWrapper color) {
        this.name = name;
        this.sound = sound;
        this.color = color;
    }
    public InternalRecordData toInternal () {
        return new InternalRecordData(name,new ResourceLocation("extrarecords_sounds",sound),color.toColor());
    }

    public static class RGBWrapper {
        public int red;
        public int green;
        public int blue;

        private RGBWrapper () {}
        public RGBWrapper (int r, int g, int b) {
            this.red = r;
            this.green = g;
            this.blue = b;
        }
        public Color toColor () {
            return new Color(red,green,blue);
        }
    }
}
