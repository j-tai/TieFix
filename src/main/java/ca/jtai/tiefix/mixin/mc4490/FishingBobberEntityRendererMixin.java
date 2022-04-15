package ca.jtai.tiefix.mixin.mc4490;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = FishingBobberEntityRenderer.class, priority = TieFix.MIXIN_PRIORITY)
public abstract class FishingBobberEntityRendererMixin {
    /**
     * When sneaking, the fishing rod is pulled inward slightly. Make the fishing line should respect that.
     */
    @ModifyConstant(
        method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        constant = @Constant(doubleValue = 0.8)
    )
    private double applySneakContraction(double constant, FishingBobberEntity fishingBobberEntity) {
        if (!TieFix.getConfig().mc4490_fix) {
            return constant;
        }
        var player = fishingBobberEntity.getPlayerOwner();
        var isSneaking = player != null && player.isInSneakingPose();
        if (!isSneaking) {
            return constant;
        }
        return constant - 0.05;
    }

    /**
     * Move the fishing line down a bit more when sneaking.
     */
    @ModifyConstant(
        method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        constant = @Constant(floatValue = -0.1875f)
    )
    private float applySneakYOffset(float constant) {
        if (!TieFix.getConfig().mc4490_fix) {
            return constant;
        }
        // No need to check for sneaking here, since this constant is only used when sneaking.
        return constant - 0.1f;
    }
}
