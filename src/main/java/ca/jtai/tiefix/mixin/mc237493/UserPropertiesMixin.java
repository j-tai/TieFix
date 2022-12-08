package ca.jtai.tiefix.mixin.mc237493;

import ca.jtai.tiefix.TieFix;
import com.mojang.authlib.minecraft.UserApiService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = UserApiService.UserProperties.class, priority = TieFix.MIXIN_PRIORITY)
public class UserPropertiesMixin {
    @Inject(method = "flag", at = @At("HEAD"), cancellable = true, remap = false)
    private void onFlag(UserApiService.UserFlag flag, CallbackInfoReturnable<Boolean> cir) {
        if (TieFix.getConfig().mc237493_fix && flag == UserApiService.UserFlag.TELEMETRY_ENABLED) {
            cir.setReturnValue(false);
        }
    }
}
