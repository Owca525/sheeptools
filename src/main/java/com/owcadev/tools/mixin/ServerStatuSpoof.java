package com.owcadev.tools.mixin;

import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static com.owcadev.tools.SheepTools.SheepConfig;

@Mixin(ServerStatusPacketListenerImpl.class)
public class ServerStatuSpoof {
    @Shadow
    @Final
    @Mutable
    private ServerStatus status;

    @Inject(method = "handleStatusRequest", at = @At("HEAD"))
    private void SpoofStatus(net.minecraft.network.protocol.status.ServerboundStatusRequestPacket packet, CallbackInfo ci) {
        if (!SheepConfig.SpoofPlayerCount) return;

        this.status = new ServerStatus(
                status.description(),
                Optional.empty(),
                status.version(),
                status.favicon(),
                status.enforcesSecureChat()
        );
    }
}
