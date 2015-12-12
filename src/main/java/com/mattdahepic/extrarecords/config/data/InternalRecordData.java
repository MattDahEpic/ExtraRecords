package com.mattdahepic.extrarecords.config.data;

import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class InternalRecordData {
    public String name;
    public ResourceLocation sound;
    public Color color;

    private InternalRecordData() {}
    public InternalRecordData(String name, ResourceLocation sound, Color color) {
        this.name = name;
        this.sound = sound;
        this.color = color;
    }
}
