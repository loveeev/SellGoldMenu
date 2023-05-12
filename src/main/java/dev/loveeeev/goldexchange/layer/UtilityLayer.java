package dev.loveeeev.goldexchange.layer;

import com.samjakob.spigui.SpiGUI;
import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.command.impl.BankCommand;
import dev.loveeeev.goldexchange.configuration.PluginConfiguration;
import dev.loveeeev.goldexchange.util.ChatUtility;
import dev.loveeeev.goldexchange.util.semantics.Layer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.milkbowl.vault.economy.Economy;
import org.buktify.configurate.ConfigurationPool;
import org.buktify.configurate.ConfigurationService;

import java.util.Objects;

/**
 * @author temez
 * @since 0.1dev
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UtilityLayer extends Layer {

    ConfigurationPool configurationPool;
    ChatUtility chatUtility;
    SpiGUI spiGUI;
    Economy economy;

    public UtilityLayer(Main plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        super.enable();
        chatUtility = new ChatUtility("temez huilan");
        parseSettings();
        prepareGUI();
        registerCommands();
        setupEconomy();
    }

    private void parseSettings() {
        ConfigurationService configurationService = new ConfigurationService()
                .rootDirectory(plugin.getDataFolder())
                .registerConfigurations(PluginConfiguration.class);
        configurationService.apply();
        configurationPool = configurationService.getConfigurationPool();
    }

    private void prepareGUI() {
        spiGUI = new SpiGUI(plugin);
        spiGUI.setEnableAutomaticPagination(false);
    }

    private void registerCommands() {
        new BankCommand(plugin);
    }

    private void setupEconomy() {
        economy = Objects.requireNonNull(plugin.getServer().getServicesManager().getRegistration(Economy.class)).getProvider();
    }

}
