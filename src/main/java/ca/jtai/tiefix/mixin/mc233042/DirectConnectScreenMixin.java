package ca.jtai.tiefix.mixin.mc233042;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.screen.DirectConnectScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DirectConnectScreen.class, priority = TieFix.MIXIN_PRIORITY)
public class DirectConnectScreenMixin {
    @Redirect(method = "init", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setTextFieldFocused(Z)V"
    ))
    private void setTextFieldFocusedProxy(TextFieldWidget instance, boolean focused) {
        if (!TieFix.getConfig().mc233042_fix) {
            instance.setTextFieldFocused(focused);
        }
    }
}
