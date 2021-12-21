package ca.jtai.tiefix.mixin.mc145929;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = InGameHud.class, priority = TieFix.MIXIN_PRIORITY)
public class InGameHudMixin {
    @Redirect(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I")
    )
    private int renderProxy(TextRenderer textRenderer, MatrixStack matrices, Text text, float x, float y, int color) {
        // Change to drawWithShadow if the fix is enabled
        if (TieFix.getConfig().mc145929_fix) {
            return textRenderer.drawWithShadow(matrices, text, x, y, color);
        } else {
            return textRenderer.draw(matrices, text, x, y, color);
        }
    }
}
