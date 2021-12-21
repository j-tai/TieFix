package ca.jtai.tiefix.mixin.mc89242;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(value = SignEditScreen.class, priority = TieFix.MIXIN_PRIORITY)
public class SignEditScreenMixin {
    @ModifyArg(method = "init", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/util/SelectionManager;<init>(Ljava/util/function/Supplier;Ljava/util/function/Consumer;Ljava/util/function/Supplier;Ljava/util/function/Consumer;Ljava/util/function/Predicate;)V"
    ), index = 4)
    private Predicate<String> stringFilterProxy(Predicate<String> stringFilter) {
        if (!TieFix.getConfig().mc89242_fix) {
            return stringFilter;
        }

        return text -> TieFix.getSizemap().getWidth(text) <= 90;
    }
}
