package rhyhe.config_despawn.mixin;

import net.minecraft.core.entity.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import rhyhe.config_despawn.ConfigDespawn;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin
{

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private int getCustomMaxAge(int old)
    {
        System.out.println("Max Age Ticks: " + (short) (ConfigDespawn.configDespawnTimer.value * Short.MAX_VALUE));
        return (short) (ConfigDespawn.configDespawnTimer.value * Short.MAX_VALUE);
    }

}
