package ca.jtai.tiefix.mixin.mc127970;

import ca.jtai.tiefix.Fix;
import ca.jtai.tiefix.TieFix;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = HeldItemRenderer.class, priority = TieFix.MIXIN_PRIORITY)
public class HeldItemRendererMixin {
    @Redirect(
        method = "renderFirstPersonItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingRiptide()Z")
    )
    private boolean isUsingRiptideProxy(AbstractClientPlayerEntity obj) {
        if (!TieFix.getConfig().isEnabled(Fix.MC127970))
            return obj.isUsingRiptide();
        // Assume the player is not using riptide for the purpose of rendering the first person held item
        return false;
    }
}
