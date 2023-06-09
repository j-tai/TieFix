package ca.jtai.tiefix.mixin.mc147766;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TextFieldWidget.class, priority = TieFix.MIXIN_PRIORITY)
public class TextFieldWidgetMixin {
    @Shadow
    private boolean selecting;

    @Inject(method = "onClick", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setCursor(I)V"
    ))
    private void onKeyPressed(double mouseX, double mouseY, CallbackInfo ci) {
        if (TieFix.getConfig().mc147766_fix) {
            this.selecting = Screen.hasShiftDown();
        }
    }
}
