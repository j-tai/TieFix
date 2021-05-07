package ca.jtai.tiefix.mixin.mc177664;

import ca.jtai.tiefix.TieFixClient;
import net.minecraft.client.sound.SoundSystem;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {
    @Redirect(
        method = "play(Lnet/minecraft/client/sound/SoundInstance;)V",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V")
    )
    private void warnProxy(Logger logger, String message) {
        if (message.equals("Failed to create new sound handle") && TieFixClient.getConfig().mc177664_fix) {
            return;
        }
        logger.warn(message);
    }
}
