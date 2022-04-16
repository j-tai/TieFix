package ca.jtai.tiefix.mixin.mc53312;

import ca.jtai.tiefix.TieFix;
import net.minecraft.client.render.entity.model.ZombieVillagerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ZombieVillagerEntityModel.class, priority = TieFix.MIXIN_PRIORITY)
public class ZombieVillagerEntityModelMixin {
    @ModifyArg(
        method = "getTexturedModelData",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/model/ModelPartBuilder;cuboid(FFFFFFLnet/minecraft/client/model/Dilation;)Lnet/minecraft/client/model/ModelPartBuilder;",
            ordinal = 1
        ),
        index = 4
    )
    private static float overrideSizeY(float sizeY) {
        if (!TieFix.getConfig().mc53312_fix) {
            return sizeY;
        }
        assert sizeY == 18f;
        return 20f;
    }
}
