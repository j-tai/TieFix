package ca.jtai.tiefix.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * MC-122477: Linux/GNU: Opening chat sometimes writes 't'
 */
@Mixin(TextFieldWidget.class)
public class FixMC122477Mixin {
    @Shadow
    private int focusedTicks;

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void interceptCharTyped(char chr, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        // Deny typing any character on the very first tick
        if (focusedTicks == 0) {
            // Return from the function early
            cir.setReturnValue(true);
        }
    }
}
