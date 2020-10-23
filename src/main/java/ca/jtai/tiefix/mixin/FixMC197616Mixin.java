package ca.jtai.tiefix.mixin;

import net.minecraft.world.biome.source.BiomeArray;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * MC-197616: Certain custom biome settings cause game to spam "Received invalid biome id: -1" in the console, causing
 * major lag or freeze
 */
@Mixin(BiomeArray.class)
public class FixMC197616Mixin {
    @Redirect(
            method = "<init>(Lnet/minecraft/util/collection/IndexedIterable;[I)V",
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V")
    )
    private void warnProxy(Logger obj, String value) {
        // Do nothing; i.e. remove the warning
    }
}
