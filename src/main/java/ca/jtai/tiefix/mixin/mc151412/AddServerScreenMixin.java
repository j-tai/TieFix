package ca.jtai.tiefix.mixin.mc151412;

import ca.jtai.tiefix.TieFixClient;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AddServerScreen.class)
public class AddServerScreenMixin extends Screen {
    // Dummy constructor required to compile
    protected AddServerScreenMixin(Text title) {
        super(title);
    }

    @Shadow private TextFieldWidget serverNameField;

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (TieFixClient.getConfig().mc151412_fix) {
            setInitialFocus(serverNameField);
        }
    }

    @Redirect(method = "init", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setTextFieldFocused(Z)V"
    ))
    private void setTextFieldFocusedProxy(TextFieldWidget textFieldWidget, boolean focused) {
        if (!TieFixClient.getConfig().mc151412_fix) {
            textFieldWidget.setTextFieldFocused(focused);
        }
    }
}
