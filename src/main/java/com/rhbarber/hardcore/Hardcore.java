package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.CommandRegistrar;
import com.rhbarber.hardcore.listeners.ConnectionListener;
import com.rhbarber.hardcore.listeners.CreatureListener;
import com.rhbarber.hardcore.listeners.DeathListener;
import com.rhbarber.hardcore.placeholders.RhbExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Hardcore extends JavaPlugin {
    private static final String HEADER = ChatColor.YELLOW + "--------------------------------------------";

    @Override
    public void onEnable() {
        saveDefaultConfig();

        long start = System.currentTimeMillis();

        getLogger().info(HEADER);
        getLogger().info("Loading Hardcore v"+getDescription().getVersion()+"...");
        getLogger().info(HEADER);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RhbExpansion().register();
            getLogger().info("PlaceholderAPI support loaded!");
        }

        new CommandRegistrar(this);
        Arrays.asList(
                new CreatureListener(this),
                new ConnectionListener(this),
                new DeathListener(this)
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
        getLogger().info("Hardcore v"+getDescription().getVersion()+" has been bootstrapped in "+(System.currentTimeMillis() - start)+ "ms");
    }

    @Override
    public void onDisable() {
        getLogger().info(HEADER);
        getLogger().info("The plugin has been disabled!");
        getLogger().info(HEADER);
    }
}