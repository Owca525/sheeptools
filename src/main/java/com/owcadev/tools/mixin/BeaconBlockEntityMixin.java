package com.owcadev.tools.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.owcadev.tools.SheepTools.SheepConfig;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin extends BlockEntity {
    public BeaconBlockEntityMixin(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    // Setting Range of Beacon
    @ModifyVariable(
            method = "applyEffects",
            at = @At("STORE"),
            name = "range")
    private static double modifyRange(double range) {
        if (SheepConfig.MaxbeaconRange > -1) return SheepConfig.MaxbeaconRange;
        return range;
    }

}
