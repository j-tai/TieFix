package ca.jtai.tiefix.mixin.mc12829;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = TieFix.MIXIN_PRIORITY)
public class LivingEntityMixin {
    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void onIsClimbing(CallbackInfoReturnable<Boolean> cir) {
        if (TieFix.getConfig().mc12829_fix
                && (TieFix.getConfig().gameplayAllowMultiplayer || MinecraftClient.getInstance().isInSingleplayer())) {
            //noinspection ConstantConditions
            if ((Object) this instanceof PlayerEntity player && player.getAbilities().flying) {
                cir.setReturnValue(false);
            }
        }
    }
}
