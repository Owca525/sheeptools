package com.owcadev.tools.mixin;

import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.owcadev.tools.SheepTools.SheepConfig;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {
    @Shadow
    private boolean hasPreviouslyKilledDragon;

    @Inject(method = "setDragonKilled", at = @At("TAIL"))
    private void AlwaysSpawnEnderDragonEgg(EnderDragon dragon, CallbackInfo ci) {
        if (!SheepConfig.DragonDropEgg) return;
        this.hasPreviouslyKilledDragon = false;
    }
}
