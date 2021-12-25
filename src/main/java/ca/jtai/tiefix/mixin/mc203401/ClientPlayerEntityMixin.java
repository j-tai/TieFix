package ca.jtai.tiefix.mixin.mc203401;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientPlayerEntity.class, priority = TieFix.MIXIN_PRIORITY)
public class ClientPlayerEntityMixin {
    @Shadow
    protected int ticksLeftToDoubleTapSprint;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void onTickMovement(CallbackInfo ci) {
        if (TieFix.getConfig().mc203401_fix) {
            ticksLeftToDoubleTapSprint = 0;
        }
    }
}
