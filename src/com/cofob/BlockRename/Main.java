package com.cofob.BlockRename;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static void main (String[] string){}

    @Override
    public void onEnable() {
        Logger log = getLogger();
        log.info("Plugin started.");
    }
}
