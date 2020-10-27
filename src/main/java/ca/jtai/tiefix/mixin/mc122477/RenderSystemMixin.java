package ca.jtai.tiefix.mixin.mc122477;

import ca.jtai.tiefix.fixes.mc122477.PollCounter;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwPollEvents()V"))
    private static void onPollEvents(long window, CallbackInfo ci) {
        PollCounter.increment();
    }
}
