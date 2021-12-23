package ca.jtai.tiefix.mixin.mc122645;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Keyboard.class, priority = TieFix.MIXIN_PRIORITY)
public class KeyboardMixin {
    @Redirect(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/NarratorManager;isActive()Z"))
    private boolean isActiveProxy(NarratorManager instance) {
        if (TieFix.getConfig().mc122645_fix) {
            return false;
        } else {
            return instance.isActive();
        }
    }
}
