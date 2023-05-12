package dev.loveeeev.goldexchange.command;

import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.util.Logging;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@Getter
@RequiredArgsConstructor

public abstract class BaseCommand implements CommandExecutor {

    Main plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            handle(sender, command, label, args);
        } catch (Exception exception) {
            UUID errorUUID = UUID.randomUUID();
            if (sender instanceof Player player) {
                plugin.getChatUtility().sendErrorNotification(player, "Во время выполнения комманды произошла непредвиденная ошибка. UUID ошибки: &#f2edaa" + errorUUID);
            }
            Logging.error("Во время выполнения комманды произошла непредвиденная ошибка. UUID ошибки: " + errorUUID);
            Logging.error(exception.toString());
        }
        return true;
    }

    public void sendHelpMessage(Player player) {

    }

    protected void register(String command) {
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(this);
    }

    public abstract void handle(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}