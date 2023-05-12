package dev.loveeeev.goldexchange.gui.impl;

import com.samjakob.spigui.buttons.SGButton;
import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.gui.BaseGUI;
import dev.loveeeev.goldexchange.module.impl.ExchangeService;
import dev.loveeeev.goldexchange.util.ItemBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExchangeGUI extends BaseGUI {

    ExchangeService exchangeService;

    public ExchangeGUI(Main plugin) {
        super(plugin);
        exchangeService = plugin.getServiceLayer().get(ExchangeService.class);
    }

    @Override
    public BaseGUI create(Player player) {
        gui = plugin.getSpiGui().create(getCenteredTitle("Обмен"), 6);
        gui.setAutomaticPaginationEnabled(false);
        for (int i = 0; i < 54; i++) {
            gui.addButton(new SGButton(ItemBuilder
                    .fromMaterial(Material.BLACK_STAINED_GLASS_PANE)
                    .setName(" ")
                    .build()
            ));
        }
        gui.setButton(29, new SGButton(ItemBuilder
                .fromMaterial(Material.GOLD_INGOT)
                .setName("&#a7fcabПродать 1 золота")
                .setLore("", "&7Нажмите ПКМ")
                .build())
                .withListener((event -> exchangeService.exchange(1, (Player) event.getWhoClicked()))));
        gui.setButton(30, new SGButton(ItemBuilder
                .fromMaterial(Material.GOLD_INGOT)
                .setAmount(4)
                .setName("&#a7fcabПродать 4 золота")
                .setLore("", "&7Нажмите ПКМ")
                .build())
                .withListener((event -> exchangeService.exchange(4, (Player) event.getWhoClicked()))));
        gui.setButton(31, new SGButton(ItemBuilder
                .fromMaterial(Material.GOLD_INGOT)
                .setAmount(16)
                .setName("&#a7fcabПродать 16 золота")
                .setLore("", "&7Нажмите ПКМ")
                .build())
                .withListener((event -> exchangeService.exchange(16, (Player) event.getWhoClicked()))));
        gui.setButton(32, new SGButton(ItemBuilder
                .fromMaterial(Material.GOLD_INGOT)
                .setAmount(64)
                .setName("&#a7fcabПродать 64 золота")
                .setLore("", "&7Нажмите ПКМ")
                .build())
                .withListener((event -> exchangeService.exchange(64, (Player) event.getWhoClicked()))));
        gui.setButton(33, new SGButton(ItemBuilder
                .fromMaterial(Material.GOLD_BLOCK)
                .setName("&#a7fcabПродать всё золото")
                .setLore("", "&7Нажмите ПКМ")
                .build())
                .withListener((event -> exchangeService.exchange(exchangeService.getGoldAmount((Player) event.getWhoClicked()), (Player) event.getWhoClicked()))));
        return this;
    }


}
