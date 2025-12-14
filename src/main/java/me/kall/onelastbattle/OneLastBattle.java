package me.kall.onelastbattle;

import me.kall.onelastbattle.ext.IDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(OneLastBattle.MOD_ID)
@Mod.EventBusSubscriber(modid = OneLastBattle.MOD_ID)
public final class OneLastBattle {
    public static final String MOD_ID = "onelastbattle";

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void reachDeath(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        float amount = event.getAmount();
        if (amount < entity.getHealth() || entity.level().isClientSide()) return;
        IDamageSource.setSuicide(entity, event.getSource());
    }
}
