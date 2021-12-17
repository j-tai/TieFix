package ca.jtai.tiefix.mixin.mc147766;

import ca.jtai.tiefix.Fix;
import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TextFieldWidget.class, priority = TieFix.MIXIN_PRIORITY)
public class TextFieldWidgetMixin {
    @Shadow
    private boolean selecting;

    @Inject(method = "mouseClicked", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setCursor(I)V"
    ))
    private void onKeyPressed(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (TieFix.getConfig().isEnabled(Fix.MC147766)) {
            this.selecting = Screen.hasShiftDown();
        }
    }
}
