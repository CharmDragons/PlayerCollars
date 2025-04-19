package org.jlortiz.playercollars.leash.mixin;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import org.jlortiz.playercollars.PlayerCollarsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "jump", at = @At("HEAD"))
    private void leashplayers$jump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!entity.getWorld().isClient()) {
            if (!entity.isSprinting() && TrinketsApi.getTrinketComponent(entity).get().isEquipped(PlayerCollarsMod.COLLAR_ITEM)) {
                entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(), PlayerCollarsMod.COLLAR_BELL, entity.getSoundCategory(), 1f, 1f);
            }
        }
    }
}
