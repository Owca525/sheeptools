package com.owcadev.tools.mixin.client;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    // For user to not showing Too Expensive
    @ModifyConstant(
            method = "extractLabels",
            constant = @Constant(intValue = 40)
    )
    private int IgnoreExpense(int original) {
        return Integer.MAX_VALUE;
    }
}
