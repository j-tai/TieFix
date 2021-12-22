package ca.jtai.tiefix.mixin.mc127970;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HeldItemRenderer.class, priority = TieFix.MIXIN_PRIORITY)
public class HeldItemRendererMixin {
    /**
     * Whether the item in question is a trident.
     * <p>
     * This is a bit of a hack, since this should really be a local variable in {@code renderFirstPersonItem}. However,
     * {@link Redirect}s cannot access local variables or arguments, so this is the best alternative.
     */
    private boolean tiefix_renderFirstPersonItemIsTrident;

    /**
     * At the head of {@code renderFirstPersonItem}, record whether the item is a trident.
     */
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void onRenderFirstPersonItem(
        AbstractClientPlayerEntity player,
        float tickDelta,
        float pitch,
        Hand hand,
        float swingProgress,
        ItemStack item,
        float equipProgress,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        CallbackInfo ci
    ) {
        tiefix_renderFirstPersonItemIsTrident = item.isOf(Items.TRIDENT);
    }

    @Redirect(
        method = "renderFirstPersonItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingRiptide()Z")
    )
    private boolean isUsingRiptideProxy(AbstractClientPlayerEntity obj) {
        if (!TieFix.getConfig().mc127970_fix || tiefix_renderFirstPersonItemIsTrident) {
            return obj.isUsingRiptide();
        }
        // Assume the player is not using riptide for the purpose of rendering the first person held item
        return false;
    }
}
