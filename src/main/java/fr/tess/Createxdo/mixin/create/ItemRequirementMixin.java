package fr.tess.Createxdo.mixin.create;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRequirement.class)
public class ItemRequirementMixin {
    @Inject(remap = false, method = "defaultOf", at = @At(value = "TAIL"), cancellable = true)
    private static void fixDONotHavingData(BlockState state, BlockEntity be, CallbackInfoReturnable<ItemRequirement> cir) {
        if (be != null && be.getLevel() != null) {
            try {
                cir.setReturnValue(new ItemRequirement(new ItemRequirement.StrictNbtStackRequirement(state.getCloneItemStack(null, be.getLevel(), be.getBlockPos(), null), ItemRequirement.ItemUseType.CONSUME)));
            } catch (Exception ignored) {
            }
        }
    }
}
