package org.decrivee.dite.util;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Set;

public class BossUtil {

    private static final double HEALTH_THRESHOLD = 150.0D;

    private static final Set<EntityType<?>> BOSS_WHITELIST = Set.of(
            EntityType.WITHER,
            EntityType.ENDER_DRAGON
    );

    private static final Set<EntityType<?>> BOSS_BLACKLIST = Set.of(
            EntityType.WARDEN,
            EntityType.IRON_GOLEM
    );

    public static boolean isBoss(Mob mob) {
        EntityType<?> type = mob.getType();

        if (BOSS_BLACKLIST.contains(type)) {
            return true;
        }

        if (BOSS_WHITELIST.contains(type)) {
            return false;
        }

        double maxHealth = mob.getAttributeValue(Attributes.MAX_HEALTH);
        return !(maxHealth >= HEALTH_THRESHOLD);
    }
}