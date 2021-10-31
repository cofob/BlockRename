package ru.cofob.BlockRename;

import ru.cofob.BlockRename.listener.AnvilRename;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getPlayer;


public class BlockRename extends JavaPlugin {
    FileConfiguration config = null;
    public String version;
    private static Permission perms = null;
    public static ArrayList<?> names;
    public static String blocked_text;

    @Override
    public void onEnable() {
//        config
        setupConfig();

//        perms
        setupPermissions();

//        defining variables
        version = "v0.0.1";
        System.out.println("§a[BlockRename] §rLoaded §6"+version+"§r!");
        new AnvilRename(this).register();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }

    public static Permission getPermissions() {
        return perms;
    }

    public void setupConfig() {
        config = getConfig();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("some names");
        strings.add("to block here");
        List<String> permissions = new ArrayList<>();
        permissions.add("cofob.blockrename.colored");
        permissions.add("cofob.blockrename.bypass");
        permissions.add("cofob.blockrename.reload");
        config.addDefault("block", strings);
        config.addDefault("permissions", permissions);
        config.addDefault("no_permission", "&4You don''t have the permission to perform this action!");
        config.addDefault("blocked_text", "BLOCKED!");
        config.options().copyDefaults(true);
        saveConfig();
        names = new ArrayList<>(Objects.requireNonNull(config.getList("block")));
        blocked_text = config.getString("blocked_text");
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player player = getPlayer(s.getName());
        if(cmd.getName().equalsIgnoreCase("blockrename") && perms.has(player, "cofob.blockrename.reload")) {
            setupConfig();
            s.sendMessage("§a[BlockRename] §rReloaded §6"+version+"§r!");
            System.out.println("§a[BlockRename] §rReloaded §6"+version+"§r!");
            return true;
        } else {
            s.sendMessage(Objects.requireNonNull(config.getString("no_permission")));
        }
        return false;
    }
}
