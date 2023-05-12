package dev.loveeeev.goldexchange.module.impl;

import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.module.SpigotService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangeService extends SpigotService {

    public ExchangeService(Main plugin) {
        super(plugin);
    }

    public void exchange(int amount, Player player) {
        if (getGoldAmount(player) < amount) {
            plugin.getChatUtility().sendErrorNotification(player, "У вас недостаточно золота!");
            return;
        }
        if (amount <= 0) {
            plugin.getChatUtility().sendErrorNotification(player, "У вас нет золота в инвентаре!");
            return;
        }
        removeGold(player, amount);
        int exchangeRate = plugin.getPluginConfiguration().getExchangeRate();
        int totalMoney = exchangeRate * amount;
        plugin.getEconomy().depositPlayer(player, totalMoney);
        plugin.getChatUtility().sendSuccessNotification(player, "Вы обменяли " + amount + " золота на " + totalMoney + " денег.");
    }


    private void removeGold(Player player, int amount) {
        for (ItemStack itemStack : player.getInventory()) {
            if (amount == 0) {
                break;
            }
            if (itemStack != null && itemStack.getType() == Material.GOLD_INGOT) {
                if (amount > itemStack.getAmount()) {
                    amount -= itemStack.getAmount();
                    itemStack.setAmount(0);
                    itemStack.setType(Material.AIR);
                } else {
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    amount = 0;
                }
            }
        }
    }

    public int getGoldAmount(Player player) {
        AtomicInteger out = new AtomicInteger();
        Arrays.stream(player.getInventory().getContents()).forEach(itemStack -> {
            if (itemStack != null && itemStack.getType() == Material.GOLD_INGOT) out.addAndGet(itemStack.getAmount());
        });
        return out.get();
    }
}
