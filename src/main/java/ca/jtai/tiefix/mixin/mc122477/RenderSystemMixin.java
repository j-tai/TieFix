package ca.jtai.tiefix.mixin.mc122477;

import ca.jtai.tiefix.TieFix;
import ca.jtai.tiefix.fixes.mc122477.PollCounter;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderSystem.class, priority = TieFix.MIXIN_PRIORITY)
public class RenderSystemMixin {
    @Inject(method = "pollEvents", at = @At("HEAD"), remap = false)
    private static void onPollEvents(CallbackInfo ci) {
        PollCounter.increment();
    }
}
