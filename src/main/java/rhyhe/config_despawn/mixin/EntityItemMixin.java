package rhyhe.config_despawn.mixin;

import net.minecraft.core.entity.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin
{
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private static int getCustomMaxAge(int old)
    {
        return itemMaxAge;
    }

    private static final short itemMaxAge = 3;

}
