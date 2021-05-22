package ca.jtai.tiefix.mixin.mc197616;

import ca.jtai.tiefix.TieFixClient;
import net.minecraft.world.biome.source.BiomeArray;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BiomeArray.class)
public class BiomeArrayMixin {
    @Redirect(
        method = "<init>(Lnet/minecraft/util/collection/IndexedIterable;[I)V",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V")
    )
    private void warnProxy(Logger obj, String value) {
        // Warn only if the fix is not enabled
        if (!TieFixClient.getConfig().mc197616_fix) {
            obj.warn(value);
        }
    }
}
