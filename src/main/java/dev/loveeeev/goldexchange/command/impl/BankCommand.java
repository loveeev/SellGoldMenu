package dev.loveeeev.goldexchange.command.impl;

import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.command.BaseCommand;
import dev.loveeeev.goldexchange.gui.impl.ExchangeGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BankCommand extends BaseCommand {

    public BankCommand(Main plugin) {
        super(plugin);
        register("bank");
    }

    @Override
    public void handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            new ExchangeGUI(plugin).create(player).open(player);
        }
    }
}
