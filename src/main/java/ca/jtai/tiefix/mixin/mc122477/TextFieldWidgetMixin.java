package ca.jtai.tiefix.mixin.mc122477;

import ca.jtai.tiefix.TieFixClient;
import ca.jtai.tiefix.config.Config;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Shadow
    private int focusedTicks;

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void interceptCharTyped(char chr, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        Config config = TieFixClient.getConfig();
        if (!config.mc122477_fix)
            return;
        // Deny typing blacklisted characters on the very first tick
        if (focusedTicks == 0) {
            if (config.mc122477_keys != null && config.mc122477_keys.contains(Character.toString(chr))) {
                // Return from the function early; ignore the key press
                cir.setReturnValue(true);
            }
        }
    }
}
