package rhyhe.config_despawn;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ConfigDespawn implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint
{
    public static final String MOD_ID = "config_despawn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
