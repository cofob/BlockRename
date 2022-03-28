package ru.cofob.BlockRename.listener;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.cofob.BlockRename.BlockRename;
import ru.cofob.BlockRename.utils.ChatUtils;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class AnvilRename {
    private final BlockRename plugin;

    public AnvilRename(BlockRename plugin) {
        this.plugin = plugin;
    }

    public void register() {
        getServer().getPluginManager().registerEvents(new EventsListener(), this.plugin);
    }

    private static class EventsListener implements Listener {
        public EventsListener() {
            Bukkit.getLogger().info("§a[BlockRename] registered strings: "+BlockRename.names);
        }

        @EventHandler
        public void onPrepareAnvil(PrepareAnvilEvent e) {
            ItemStack result = e.getResult();
            if (result == null) return;
            ItemMeta meta = result.getItemMeta();
            if (meta == null) return;

            Permission perms = BlockRename.getPermissions();
            Player player = getPlayer(e.getViewers().get(0).getName());
            if (player == null) return;

            String n = ChatColor.stripColor(meta.getDisplayName());

            if (BlockRename.names.contains(n.toLowerCase()) && !perms.has(player, "cofob.blockrename.bypass")) {
                ItemStack original = e.getInventory().getItem(0);
                if (original != null) {
                    ItemMeta original_meta = original.getItemMeta();
                    if (original_meta != null) {
                        String name = original_meta.getDisplayName();
                        if (BlockRename.names.contains(ChatColor.stripColor(name).toLowerCase())) {
                            Bukkit.getLogger().info("§a[BlockRename] §aRename '"+
                                    meta.getDisplayName()+"', by player '"+player.getName()+"' already renamed!");
                            return;
                        }
                    }
                }

                Bukkit.getLogger().info("§a[BlockRename] §cBlocked rename to '"+
                        meta.getDisplayName()+"', by player '"+player.getName()+"'!");
                meta.setDisplayName(BlockRename.blocked_text);
            }

            if (perms.has(player, "cofob.blockrename.colored")) {
                meta.setDisplayName(ChatUtils.translate(meta.getDisplayName()));
            }

            result.setItemMeta(meta);
            e.setResult(result);
        }
    }
}
