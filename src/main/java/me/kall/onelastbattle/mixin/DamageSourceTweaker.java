package me.kall.onelastbattle.mixin;

import me.kall.onelastbattle.ext.IDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(DamageSource.class)
public class DamageSourceTweaker implements IDamageSource {
    @Mutable @Shadow @Final @Nullable private Entity causingEntity;

    @Mutable @Shadow @Final @Nullable private Entity directEntity;

    @Override
    public void oneLastBattle$setCausingEntity(Entity causingEntity) {
        this.causingEntity = causingEntity;
    }

    @Override
    public void oneLastBattle$setDirectEntity(Entity directEntity) {
        this.directEntity = directEntity;
    }

    @Inject(method = "getLocalizedDeathMessage", at = @At("HEAD"), cancellable = true)
    private void onDeath(LivingEntity suicider, CallbackInfoReturnable<Component> cir) {
        if (this.causingEntity == null || this.directEntity == null) return;
        if (this.directEntity.getId() != suicider.getId() || this.causingEntity.getId() != suicider.getId()) return;
        cir.setReturnValue(Component.translatable("info.onelastbattle.death", suicider.getDisplayName().getString()));
    }
}
