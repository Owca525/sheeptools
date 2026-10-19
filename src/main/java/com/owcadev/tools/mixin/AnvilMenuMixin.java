package com.owcadev.tools.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static com.owcadev.tools.SheepTools.SheepConfig;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
    public AnvilMenuMixin(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, ContainerLevelAccess access, ItemCombinerMenuSlotDefinition itemInputSlots) {
        super(menuType, containerId, inventory, access, itemInputSlots);
    }
    
    // Change everyting as Compatible
    @ModifyVariable(
            method = "createResult",
            at = @At("STORE"),
            name = "compatible")
    private static boolean isCompatible(boolean compatible) {
        if (SheepConfig.OverWriteCompatible) return true;
        return compatible;
    }

    // Change Max Enchange level Allow
    @ModifyExpressionValue(
            method = "createResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"
            )
    )
    private int MaxEnchantLevel(int original) {
        if (SheepConfig.MaxEnchatmentLevel > -1) return SheepConfig.MaxEnchatmentLevel;
        return original;
    }

    // Ignore Expense
    @ModifyConstant(
            method = "createResult",
            constant = @Constant(intValue = 40)
    )
    private int IgnoreExpense(int original) {
        if (SheepConfig.IgnoreExpense) return Integer.MAX_VALUE;
        return original;
    }
}
