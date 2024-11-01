package rhyhe.config_despawn.mixin;

import net.minecraft.client.option.FloatOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.Option;
import net.minecraft.core.lang.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rhyhe.config_despawn.IGameSettingsMixin;

@Mixin(value = GameSettings.class, remap = false)
public abstract class GameSettingsMixin implements IGameSettingsMixin
{
    @Unique
    public FloatOption configDespawnTimer = new FloatOption((GameSettings) ((Object) this), "despawntimer", 0.18310546875f); // 0.18... about 5min

    // age == ticks since item was dropped
    @Inject(method = "getDisplayString", at = @At(value = "HEAD"), remap = false, cancellable = true)
    public void configDespawn_getDisplayString(Option<?> option, CallbackInfoReturnable<String> cir)
    {
        I18n trans = I18n.getInstance();
        if (option instanceof FloatOption)
        {
            if (option == configDespawnTimer)
            {
                FloatOption floatOption = (FloatOption) option;
                float value = floatOption.value;
                if (value == 0)
                {
                    cir.setReturnValue(trans.translateKey("options.despawnTimer.min"));
                } else if (value == 1)
                {
                    cir.setReturnValue(trans.translateKey("options.despawnTimer.max"));
                } else
                {
                    int totalSeconds = (int) ((value * Short.MAX_VALUE) / 20);
                    String time = (totalSeconds / 60) + "m" + (totalSeconds % 60) + "s";
                    cir.setReturnValue(time);
                }
            }
        }
    }

    @Override
    public FloatOption getConfigDespawnTimer()
    {
        return configDespawnTimer;
    }
}
