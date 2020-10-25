package ca.jtai.tiefix.mixin;

import ca.jtai.tiefix.TieFixClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * MC-127970: Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your
 * offhand
 */
@Mixin(HeldItemRenderer.class)
public class FixMC127970Mixin {
    @Redirect(
            method = "renderFirstPersonItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingRiptide()Z")
    )
    private boolean isUsingRiptideProxy(AbstractClientPlayerEntity obj) {
        if (!TieFixClient.getConfig().mc127970_fix)
            return obj.isUsingRiptide();
        // Assume the player is not using riptide for the purpose of rendering the first person held item
        return false;
    }
}
