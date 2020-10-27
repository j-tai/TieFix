package ca.jtai.tiefix.mixin.mc122477;

import ca.jtai.tiefix.TieFixClient;
import ca.jtai.tiefix.config.Config;
import ca.jtai.tiefix.fixes.mc122477.PollCounter;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    private long tiefix_initialPoll = -1;

    @Inject(method = "<init>(Lnet/minecraft/client/font/TextRenderer;IIIILnet/minecraft/client/gui/widget/TextFieldWidget;Lnet/minecraft/text/Text;)V", at = @At("RETURN"))
    private void onInit(TextRenderer textRenderer, int x, int y, int width, int height, TextFieldWidget copyFrom, Text text, CallbackInfo ci) {
        tiefix_initialPoll = PollCounter.get();
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void onCharTyped(char chr, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        Config config = TieFixClient.getConfig();
        if (!config.mc122477_fix)
            return;
        // Deny typing blacklisted characters on the very first poll after initialization
        if (tiefix_initialPoll + 1 == PollCounter.get()) {
            if (config.mc122477_keys == null || config.mc122477_keys.isEmpty() || config.mc122477_keys.contains(Character.toString(chr))) {
                // Return from the function early; ignore the key press
                cir.setReturnValue(true);
            }
        }
    }
}
