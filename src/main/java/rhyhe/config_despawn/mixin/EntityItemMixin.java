package rhyhe.config_despawn.mixin;

import net.minecraft.core.entity.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rhyhe.config_despawn.ConfigDespawn;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin
{

    @Shadow
    public int age;

//    @Inject(method = "<init>(Lnet/minecraft/core/world/World;DDDLnet/minecraft/core/item/ItemStack;)V", at = @At(value = "TAIL"), remap = false)
//    public void configDespawn_init(CallbackInfo ci)
//    {
//        age = Short.MAX_VALUE - 20;
//    }

    @Inject(method = "tick", at = @At(value = "TAIL"), remap = false)
    public void configDespawn_tick(CallbackInfo ci)
    {
        if (ConfigDespawn.configDespawnTimer.value == 1)
        {
            //need to keep age near short limit of 2^15 - 1 (32,767) since it is saved as a short
            //not actually sure what happens if age is saved as a number > Short.MAX.
            if (age >= Short.MAX_VALUE - 1)
            {
                age -= 7192;
                /*
                    The doRender() method in the ItemEntityRenderer class has these two lines:
                    float bobbingOffset = MathHelper.sin(((float)entity.age + partialTick) / 10.0f + entity.initialRotation) * 0.1f + 0.1f;
                    float f3 = (float)Math.toDegrees(((float)entity.age + partialTick) / 20.0f + entity.initialRotation);

                    bobbing Offset has a period of 20pi, or ~62 age ticks
                    f3 has the longer period of 7200 age ticks to go from 0 to 360 degrees

                    Since the closest multiple of 62 to 7200 is 7192 I just need to subtract age by that amount
                    so there is as minimal a "jump" in rendering as possible

                    aren't hacks the best?
               */
            }
        }
    }

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private int configDespawn_getCustomMaxAge(int old)
    {
        return (short) (ConfigDespawn.configDespawnTimer.value * Short.MAX_VALUE);
    }

}
