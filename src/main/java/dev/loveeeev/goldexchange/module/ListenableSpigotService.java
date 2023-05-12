package dev.loveeeev.goldexchange.module;

import dev.loveeeev.goldexchange.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author temez
 * @since 0.1dev
 */
public abstract class ListenableSpigotService extends SpigotService implements Listener {

    public ListenableSpigotService(Main plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        super.enable();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
