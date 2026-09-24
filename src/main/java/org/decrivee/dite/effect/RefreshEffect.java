package org.decrivee.dite.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class RefreshEffect extends InstantenousMobEffect {

    public RefreshEffect() {
        super(MobEffectCategory.NEUTRAL, 0xBABB44);
    }

    @Override
    public void applyInstantenousEffect(Entity source, Entity indirectSource,
                                        @NotNull LivingEntity target, int amplifier, double effectiveness) {
        if (target instanceof Player) {
            if (!target.level().isClientSide()) {
                target.removeAllEffects();
            }
        }
    }
    //Only clear player's effect
}
