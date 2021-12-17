package ca.jtai.tiefix.mixin.mc140646;

import ca.jtai.tiefix.Fix;
import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TextFieldWidget.class, priority = TieFix.MIXIN_PRIORITY)
public abstract class TextFieldWidgetMixin {
    @Shadow
    private boolean selecting;
    @Shadow
    private int selectionEnd;

    @Shadow
    public abstract void setSelectionEnd(int index);

    @Inject(method = "setCursor", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setSelectionStart(I)V",
        shift = At.Shift.AFTER
    ))
    private void onSetCursor(int cursor, CallbackInfo ci) {
        if (TieFix.getConfig().isEnabled(Fix.MC140646) && selecting) {
            // The logic for scrolling is contained in setSelectionEnd, so
            // we call it without modifying the actual selectionEnd field
            int end = selectionEnd;
            setSelectionEnd(cursor);
            selectionEnd = end;
        }
    }
}
