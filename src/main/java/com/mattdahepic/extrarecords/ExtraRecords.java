package com.mattdahepic.extrarecords;

import com.mattdahepic.extrarecords.config.ERConfig;
import com.mattdahepic.mdecore.update.UpdateChecker;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = ExtraRecords.MODID,name = ExtraRecords.NAME,version = ExtraRecords.VERSION,dependencies = ExtraRecords.DEPENDENCIES)
public class ExtraRecords {
    @Mod.Instance(ExtraRecords.MODID)
    public static ExtraRecords instance;

    public static final String MODID = "extrarecords";
    public static final String NAME = "Extra Records";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.8.8-1.6.1,);";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+ MinecraftForge.MC_VERSION+"/"+MODID+".txt";
    public static final String CLIENT_PROXY = "com.mattdahepic.extrarecords.ClientProxy";
    public static final String COMMON_PROXY = "com.mattdahepic.extrarecords.CommonProxy";

    public static final Logger logger = LogManager.getLogger(MODID);

    public static List<Item> records = new ArrayList<Item>();

    @SidedProxy(clientSide = CLIENT_PROXY,serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(instance);
        ERConfig.init(e);
        proxy.registerItems();
    }
    @Mod.EventHandler
    public void init (FMLInitializationEvent e) {
        UpdateChecker.checkRemote(MODID, UPDATE_URL);
        proxy.registerRenders();
    }
    @SubscribeEvent
    public void onJoin (PlayerEvent.PlayerLoggedInEvent e) {
        UpdateChecker.printMessageToPlayer(MODID,e.player);
    }
}
