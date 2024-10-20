package rhyhe.config_despawn.mixin;

import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.IntegerOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rhyhe.config_despawn.IGameSettingsMixin;

@Mixin(value = GameSettings.class, remap = false)
public abstract class GameSettingsMixin implements IGameSettingsMixin
{
    @Unique
    public IntegerOption configDespawnTimer = new IntegerOption((GameSettings) ((Object) this), "despawnTimer", 6000);

    @Override
    public IntegerOption getConfigDespawnTimer()
    {
        return configDespawnTimer;
    }
}
