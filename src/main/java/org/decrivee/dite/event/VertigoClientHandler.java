package org.decrivee.dite.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.decrivee.dite.dite;
import org.decrivee.dite.init.ModEffects;

@Mod.EventBusSubscriber(modid = dite.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class VertigoClientHandler {

    @SubscribeEvent
    public static void onComputeFov(ComputeFovModifierEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        MobEffectInstance effect = mc.player.getEffect(ModEffects.VERTIGO.get());
        if (effect != null) {
            float zoom = 0.4f;
            //参数越小视角越大
            event.setNewFovModifier(zoom);
        }
    }

    @SubscribeEvent
    public static void onComputeCameraAngles(ViewportEvent.ComputeCameraAngles event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        MobEffectInstance effect = mc.player.getEffect(ModEffects.VERTIGO.get());
        if (effect != null) {
            long time = mc.level.getGameTime();
            float intensity = 0.5f;
            //参数越大抖动越厉害
            float yawShake = (float) Math.sin(time * 0.5) * intensity;
            float pitchShake = (float) Math.cos(time * 0.7) * intensity;

            event.setYaw(event.getYaw() + yawShake);
            event.setPitch(event.getPitch() + pitchShake);
        }
    }
}