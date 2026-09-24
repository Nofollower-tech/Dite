package org.decrivee.dite.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.decrivee.dite.util.BossUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VertigoEffect extends MobEffect {

    private static final String VERTIGO = "64e9fbaf-8419-4268-b2f7-40b5cfd3b85b";
    public static final Map<UUID, Boolean> originalAiState = new HashMap<>();

    public VertigoEffect() {
        super(MobEffectCategory.HARMFUL, 0xCC9E00);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                VERTIGO,
                -1.0D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity target, int amplifier) {
        if (target instanceof Player player && !player.level().isClientSide()) {
            if (player.getDeltaMovement().y > 0) {
                player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
            }
        }
        //forbid move
        else if (target instanceof Mob mob && !mob.level().isClientSide()) {
            if (BossUtil.isBoss(mob)) {
                originalAiState.putIfAbsent(mob.getUUID(), mob.isNoAi());
                mob.setNoAi(true);
            }
        }
        //clear AI

        //添加眩晕粒子
        spawnVertigoParticles(target);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    private static void spawnVertigoParticles(LivingEntity entity) {
        final double BASE_RADIUS = 0.8;
        final double HEIGHT_OFFSET = 0.5;
        final int BASE_PARTICLE_COUNT = (int) (3 + entity.getBbWidth() * 2 * Math.PI);
        final double FLICKER_SPEED = 0.2;
        final double FLICKER_RANGE = 0.6;

        double centerX = entity.getX();
        double centerY = entity.getY() + entity.getBbHeight() + HEIGHT_OFFSET;
        double centerZ = entity.getZ();

        double radius = BASE_RADIUS + entity.getBbWidth() * 0.5;
        double time = entity.tickCount;

        double flickerFactor = 1.0 + Math.sin(time * FLICKER_SPEED) * FLICKER_RANGE;
        int particleCount = (int) (BASE_PARTICLE_COUNT * flickerFactor);

        for (int i = 0; i < particleCount; i++) {
            double angle = (2 * Math.PI / particleCount) * i + (time * 0.05);
            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;

            entity.level().addParticle(
                    ParticleTypes.CRIT,
                    centerX + offsetX,
                    centerY,
                    centerZ + offsetZ,
                    0, 0, 0
            );
        }
    }
}



