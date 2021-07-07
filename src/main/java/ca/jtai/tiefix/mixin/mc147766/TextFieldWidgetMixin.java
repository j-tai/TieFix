package ca.jtai.tiefix.mixin.mc147766;

import ca.jtai.tiefix.TieFixClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Shadow
    private boolean selecting;

    @Inject(method = "mouseClicked", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setCursor(I)V"
    ))
    private void onKeyPressed(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (TieFixClient.getConfig().mc147766_fix) {
            this.selecting = Screen.hasShiftDown();
        }
    }
}
