package ca.jtai.tiefix.mixin.mc89242;

import ca.jtai.tiefix.TieFix;
import ca.jtai.tiefix.fixes.mc89242.SizemapDumper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Keyboard.class, priority = TieFix.MIXIN_PRIORITY)
public class KeyboardMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    /**
     * Dumps the font sizemap when F3 + X is pressed and the {@code debug} option in the config is {@code true}.
     */
    @Inject(method = "processF3", at = @At("TAIL"), cancellable = true)
    private void onProcessF3(int key, CallbackInfoReturnable<Boolean> cir) {
        if (TieFix.getConfig().debug && key == GLFW.GLFW_KEY_X) {
            SizemapDumper.dump(client);
            cir.setReturnValue(true);
        }
    }
}
