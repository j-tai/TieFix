package ca.jtai.tiefix.mixin.mc79545;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = InGameHud.class, priority = TieFix.MIXIN_PRIORITY)
public class InGameHudMixin {
    @Redirect(
        method = "renderExperienceBar",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getNextLevelExperience()I")
    )
    private int getNextLevelExperienceProxy(ClientPlayerEntity instance) {
        if (!TieFix.getConfig().mc79545_fix) {
            return instance.getNextLevelExperience();
        }
        // The game renders the XP bar if this value is positive. The value is not used for anything else, so we can
        // really return whatever we want here. I'm still keeping the call to getNextLevelExperience in case another
        // mod relies on that.
        return Math.max(1, instance.getNextLevelExperience());
    }
}
