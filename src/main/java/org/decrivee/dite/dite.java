package org.decrivee.dite;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.decrivee.dite.init.ModBlocks;
import org.decrivee.dite.init.ModCreativeModeTabs;
import org.decrivee.dite.init.ModEffects;
import org.decrivee.dite.init.ModItems;
import org.slf4j.Logger;

@Mod(dite.MODID)
public class dite
{
    public static final String MODID = "dite";
    private static final Logger LOGGER = LogUtils.getLogger();

    public dite() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //region ModEventBus
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        //end region

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

}
