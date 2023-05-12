package dev.loveeeev.goldexchange;

import com.samjakob.spigui.SpiGUI;
import dev.loveeeev.goldexchange.configuration.PluginConfiguration;
import dev.loveeeev.goldexchange.layer.SpigotServiceLayer;
import dev.loveeeev.goldexchange.layer.UtilityLayer;
import dev.loveeeev.goldexchange.util.ChatUtility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;
import org.buktify.configurate.exception.ConfigurationException;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class Main extends JavaPlugin {

    UtilityLayer utilityLayer;
    SpigotServiceLayer serviceLayer;

    @Override
    public void onEnable() {
        utilityLayer = new UtilityLayer(this);
        utilityLayer.enable();
        serviceLayer = new SpigotServiceLayer(this);
        serviceLayer.enable();
    }

    @Override
    public void onDisable() {
        serviceLayer.disable();
        utilityLayer.disable();
    }

    /**
     * Шорткаты для утилит
     */

    public ChatUtility getChatUtility() {
        return utilityLayer.getChatUtility();
    }

    public SpiGUI getSpiGui() {
        return utilityLayer.getSpiGUI();
    }

    public Economy getEconomy() {
        return utilityLayer.getEconomy();
    }

    @SneakyThrows(ConfigurationException.class)
    public PluginConfiguration getPluginConfiguration() {
        return utilityLayer.getConfigurationPool().get(PluginConfiguration.class);
    }

}
