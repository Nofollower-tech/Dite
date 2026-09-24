package org.decrivee.dite.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.decrivee.dite.dite;
import org.decrivee.dite.init.ModEffects;
import org.decrivee.dite.util.BossUtil;
import org.decrivee.dite.effect.VertigoEffect;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = dite.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VertigoEventHandler {

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffect() != ModEffects.VERTIGO.get()) return;

        LivingEntity target = event.getEntity();
        if (target instanceof Mob mob && !mob.level().isClientSide()) {
            if (BossUtil.isBoss(mob)) {
                Boolean original = VertigoEffect.originalAiState.remove(mob.getUUID());
                mob.setNoAi(Objects.requireNonNullElse(original, false));
            }
        }
    }
}