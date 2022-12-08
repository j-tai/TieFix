package ca.jtai.tiefix.mixin.mc89242;

import ca.jtai.tiefix.TieFix;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(value = AbstractSignEditScreen.class, priority = TieFix.MIXIN_PRIORITY)
public class AbstractSignEditScreenMixin {
    @Shadow
    @Final
    protected SignBlockEntity blockEntity;

    @ModifyArg(method = "init", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/util/SelectionManager;<init>(Ljava/util/function/Supplier;Ljava/util/function/Consumer;Ljava/util/function/Supplier;Ljava/util/function/Consumer;Ljava/util/function/Predicate;)V"
    ), index = 4)
    private Predicate<String> stringFilterProxy(Predicate<String> stringFilter) {
        if (!TieFix.getConfig().mc89242_fix) {
            return stringFilter;
        }

        return text -> TieFix.getSizemap().getWidth(text) <= blockEntity.getMaxTextWidth();
    }
}
