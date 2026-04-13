package fr.tess.Createxdo.mixin.create;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import com.simibubi.create.content.schematics.SchematicPrinter;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SchematicPrinter.class)
public class schematicPrinterMixin {

    @Inject(remap = false, method = "getCurrentRequirement", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/schematics/requirement/ItemRequirement;of(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;)Lcom/simibubi/create/content/schematics/requirement/ItemRequirement;"))
    private void setLevelBE(CallbackInfoReturnable<ItemRequirement> cir, @Local(ordinal = 0) BlockEntity e) {
        try {
            LogUtils.getLogger().warn("aaa: {}", SchematicPrinter.class.getDeclaredField("blockReader").get(this));
        if (e != null)
            e.setLevel((Level) SchematicPrinter.class.getDeclaredField("blockReader").get(this));
        }catch (Exception ignored) {}
    }
}
