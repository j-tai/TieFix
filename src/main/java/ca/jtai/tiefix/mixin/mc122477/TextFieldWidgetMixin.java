package ca.jtai.tiefix.mixin.mc122477;

import ca.jtai.tiefix.TieFix;
import ca.jtai.tiefix.fixes.mc122477.PollCounter;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TextFieldWidget.class, priority = TieFix.MIXIN_PRIORITY)
public class TextFieldWidgetMixin {
    private long tiefix_initialPoll = -1;

    @Inject(method = "<init>(Lnet/minecraft/client/font/TextRenderer;IIIILnet/minecraft/client/gui/widget/TextFieldWidget;Lnet/minecraft/text/Text;)V", at = @At("RETURN"))
    private void onInit(TextRenderer textRenderer, int x, int y, int width, int height, TextFieldWidget copyFrom, Text text, CallbackInfo ci) {
        tiefix_initialPoll = PollCounter.get();
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void onCharTyped(char chr, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        var config = TieFix.getConfig();
        if (!config.mc122477_fix) {
            return;
        }

        // Deny typing blacklisted characters on the very first poll after initialization
        if (tiefix_initialPoll + 1 == PollCounter.get()) {
            var keys = config.mc122477_keys;
            if (keys.isEmpty() || keys.toLowerCase().indexOf(Character.toLowerCase(chr)) != -1) {
                // Return from the function early; ignore the key press
                cir.setReturnValue(true);
            }
        }
    }
}
