package ca.jtai.tiefix.mixin.mc80859;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(value = HandledScreen.class, priority = TieFix.MIXIN_PRIORITY)
public class HandledScreenMixin {
    @Shadow
    protected boolean cursorDragging;
    @Shadow
    @Final
    protected Set<Slot> cursorDragSlots;

    @Redirect(method = "drawSlot", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;cursorDragging:Z"))
    private boolean cursorDraggingProxy(HandledScreen<?> instance) {
        assert instance == (Object) this;
        if (!TieFix.getConfig().mc80859_fix) {
            return cursorDragging;
        }
        return cursorDragging && cursorDragSlots.size() != 1;
    }
}
