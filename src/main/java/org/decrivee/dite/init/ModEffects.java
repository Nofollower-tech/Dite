package org.decrivee.dite.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.decrivee.dite.dite;
import org.decrivee.dite.effect.RefreshEffect;
import org.decrivee.dite.effect.VertigoEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, dite.MODID);

    public static final RegistryObject<MobEffect> REFRESH =
            EFFECTS.register("refresh", RefreshEffect::new);

    public static final RegistryObject<MobEffect> VERTIGO =
            EFFECTS.register("vertigo", VertigoEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
