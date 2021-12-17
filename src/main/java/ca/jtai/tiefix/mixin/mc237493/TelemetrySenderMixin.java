package ca.jtai.tiefix.mixin.mc237493;

import ca.jtai.tiefix.Fix;
import ca.jtai.tiefix.TieFix;
import com.mojang.authlib.minecraft.TelemetrySession;
import com.mojang.authlib.minecraft.UserApiService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.telemetry.TelemetrySender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.UUID;

@Mixin(value = TelemetrySender.class, priority = TieFix.MIXIN_PRIORITY)
public class TelemetrySenderMixin {
    @Shadow
    @Final
    @Mutable
    private TelemetrySession session;

    @Shadow
    private boolean sent;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(
        MinecraftClient client,
        UserApiService userApiService,
        Optional<String> userId,
        Optional<String> clientId,
        UUID deviceSessionId,
        CallbackInfo ci
    ) {
        if (TieFix.getConfig().isEnabled(Fix.MC237493)) {
            session = TelemetrySession.DISABLED;
            sent = true;
        }
    }
}
