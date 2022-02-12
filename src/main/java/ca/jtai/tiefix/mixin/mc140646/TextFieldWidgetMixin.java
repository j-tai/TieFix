package ca.jtai.tiefix.mixin.mc140646;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
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
        if (TieFix.getConfig().mc140646_fix && selecting) {
            // The logic for scrolling is contained in setSelectionEnd, so
            // we call it without modifying the actual selectionEnd field
            var end = selectionEnd;
            setSelectionEnd(cursor);
            selectionEnd = end;
        }
    }

    /**
     * Fixes the crash that occurs when the {@code selectionEnd} is outside the visible part of the text box.
     * <p>
     * Such a situation is normally impossible in the vanilla game, but is possible with this fix enabled.
     * <p>
     * See issue #7.
     */
    @ModifyArg(method = "renderButton", at = @At(
        value = "INVOKE",
        target = "Ljava/lang/String;substring(II)Ljava/lang/String;",
        ordinal = 1
    ), index = 1)
    private int boundSelectionEnd(int relativeSelectionEnd) {
        if (TieFix.getConfig().mc140646_fix) {
            return Math.max(0, relativeSelectionEnd);
        } else {
            return relativeSelectionEnd;
        }
    }
}
