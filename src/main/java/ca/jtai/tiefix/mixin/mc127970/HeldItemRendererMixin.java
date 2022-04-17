package ca.jtai.tiefix.mixin.mc127970;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = HeldItemRenderer.class, priority = TieFix.MIXIN_PRIORITY)
public class HeldItemRendererMixin {
    @Redirect(
        method = "renderFirstPersonItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingRiptide()Z")
    )
    private boolean isUsingRiptideProxy(
        AbstractClientPlayerEntity obj,
        AbstractClientPlayerEntity player,
        float tickDelta,
        float pitch,
        Hand hand,
        float swingProgress,
        ItemStack item
    ) {
        if (!TieFix.getConfig().mc127970_fix || item.isOf(Items.TRIDENT)) {
            return obj.isUsingRiptide();
        }
        // Assume the player is not using riptide for the purpose of rendering the first person held item
        return false;
    }
}
