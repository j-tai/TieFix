package ca.jtai.tiefix.mixin.mc237493;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.util.telemetry.TelemetryManager;
import net.minecraft.client.util.telemetry.TelemetrySender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TelemetryManager.class, priority = TieFix.MIXIN_PRIORITY)
public class TelemetrySenderMixin {
    @Inject(method = "getSender", at = @At("HEAD"), cancellable = true)
    private void onGetSender(CallbackInfoReturnable<TelemetrySender> cir) {
        if (TieFix.getConfig().mc237493_fix) {
            cir.setReturnValue(TelemetrySender.NOOP);
        }
    }
}
