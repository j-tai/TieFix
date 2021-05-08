package ca.jtai.tiefix.mixin.mc177664;

import ca.jtai.tiefix.TieFixClient;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.sound.SoundEngine$SourceSetImpl")
public class SoundEngineMixin {
    @Redirect(
        method = "createSource()Lnet/minecraft/client/sound/Source;",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V")
    )
    private void warnProxy(Logger logger, String message, Object p0) {
        if (message.equals("Maximum sound pool size {} reached") && TieFixClient.getConfig().mc177664_fix) {
            return;
        }
        logger.warn(message, p0);
    }
}
