package me.kall.onelastbattle.ext;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public interface IDamageSource {
    void oneLastBattle$setCausingEntity(Entity causingEntity);
    void oneLastBattle$setDirectEntity(Entity directEntity);

    static void setSuicide(Entity entity, DamageSource damageSource) {
        ((IDamageSource)damageSource).oneLastBattle$setCausingEntity(entity);
        ((IDamageSource)damageSource).oneLastBattle$setDirectEntity(entity);
    }
}
