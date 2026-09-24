package org.decrivee.dite.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.decrivee.dite.dite;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, dite.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
