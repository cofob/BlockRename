package ru.cofob.BlockRename.listener;

import ru.cofob.BlockRename.BlockRename;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            System.out.println("§a[BlockRename] registered strings: "+BlockRename.names);
        }

        @EventHandler
        public void onPrepareAnvil(PrepareAnvilEvent e) {
            ItemStack result = e.getResult();
            assert result != null;
            ItemMeta meta = result.getItemMeta();
            assert meta != null;

            Permission perms = BlockRename.getPermissions();
            Player player = getPlayer(e.getViewers().get(0).getName());

            String n = meta.getDisplayName().replace("&0", "").replace("&1", "")
                    .replace("&2", "").replace("&3", "").replace("&4", "")
                    .replace("&5", "").replace("&6", "").replace("&7", "")
                    .replace("&8", "").replace("&9", "").replace("&a", "")
                    .replace("&b", "").replace("&c", "").replace("&d", "")
                    .replace("&e", "").replace("&f", "").replace("&g", "")
                    .replace("&k", "").replace("&l", "").replace("&m", "")
                    .replace("&n", "").replace("&o", "").replace("&r", "");


            if (BlockRename.names.contains(n.toLowerCase()) && !perms.has(player, "cofob.blockrename.bypass")) {
                assert player != null;
                System.out.println("§a[BlockRename] §cBlocked rename to '"+ meta.getDisplayName()+"', by player '"+player.getName()+"'!");
                meta.setDisplayName(BlockRename.blocked_text);
            }

            if (perms.has(player, "cofob.blockrename.colored")) {
                meta.setDisplayName(meta.getDisplayName().replace("&", "§"));
            }

            result.setItemMeta(meta);
            e.setResult(result);
        }
    }
}
