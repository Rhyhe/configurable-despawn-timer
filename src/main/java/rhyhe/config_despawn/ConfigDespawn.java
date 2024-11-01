package rhyhe.config_despawn;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.FloatOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ConfigDespawn implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint
{
    public static final String MOD_ID = "config_despawn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static FloatOption configDespawnTimer;

    @Override
    public void onInitialize()
    {
        LOGGER.info("Config_Despawn initialized.");
    }

    @Override
    public void beforeGameStart()
    {
    }

    @Override
    public void afterGameStart()
    {
        Minecraft mc = Minecraft.getMinecraft(this);
        GameSettings gameSettings = mc.gameSettings;
        FloatOption configDespawnTimer_local = ((IGameSettingsMixin) gameSettings).getConfigDespawnTimer();
        // just make sure local version stays local and couldn't somehow be changed by something else
        configDespawnTimer = configDespawnTimer_local;
        FloatOptionComponent comp = new FloatOptionComponent(configDespawnTimer_local);
        OptionsCategory category = new OptionsCategory("gui.options.page.general.category.config_despawn").withComponent(comp);
        OptionsPages.register(new OptionsPage("gui.options.page.configurable_item_despawn.title", new ItemStack(Block.sand))).withComponent(category);
    }

    @Override
    public void onRecipesReady()
    {
    }

    @Override
    public void initNamespaces()
    {
    }
}
