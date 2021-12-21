package ca.jtai.tiefix.mixin.mc2071;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ClientPlayerEntity.class, priority = TieFix.MIXIN_PRIORITY)
public class ClientPlayerEntityMixin {
    @Redirect(method = "updateNausea", at = @At(
        value = "FIELD",
        target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;",
        ordinal = 0
    ))
    private Screen onUpdateNausea(MinecraftClient instance) {
        if (TieFix.getConfig().mc2071_fix) {
            return null; // Pretend that there's no screen open
        } else {
            return instance.currentScreen;
        }
    }
}
