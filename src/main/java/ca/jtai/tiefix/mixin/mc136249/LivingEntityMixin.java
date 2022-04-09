package ca.jtai.tiefix.mixin.mc136249;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LivingEntity.class, priority = TieFix.MIXIN_PRIORITY)
public class LivingEntityMixin {
    @Shadow
    protected int riptideTicks;

    @Redirect(method = "travel", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/enchantment/EnchantmentHelper;getDepthStrider(Lnet/minecraft/entity/LivingEntity;)I"
    ))
    private int getDepthStriderProxy(LivingEntity entity) {
        if (riptideTicks == 0) {
            return EnchantmentHelper.getDepthStrider(entity);
        }

        var config = TieFix.getConfig();
        var fixIsEnabled = config.mc136249_fix
            && (config.gameplayAllowMultiplayer || MinecraftClient.getInstance().isInSingleplayer());
        if (fixIsEnabled) {
            return 0;
        } else {
            return EnchantmentHelper.getDepthStrider(entity);
        }
    }
}
