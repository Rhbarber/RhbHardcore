package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.RhbCommand;
import com.rhbarber.hardcore.commands.SampleCommand;
import com.rhbarber.hardcore.commands.Test;
import com.rhbarber.hardcore.listeners.CreatureSpawns;
import com.rhbarber.hardcore.listeners.Login;
import com.rhbarber.hardcore.listeners.MobKills;
import com.rhbarber.hardcore.placeholders.RhbExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Hardcore extends JavaPlugin {
    private static final String HEADER = ChatColor.YELLOW + "--------------------------------------------";
    PluginDescriptionFile pluginFile = getDescription();
    public final String version = pluginFile.getVersion();
    public final String name = ChatColor.GRAY + "[" + ChatColor.RED + pluginFile.getName() + ChatColor.GRAY + "] ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(HEADER);
        Bukkit.getConsoleSender().sendMessage(name + version + " has started!");
        Bukkit.getConsoleSender().sendMessage(HEADER);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RhbExpansion().register();
            Bukkit.getConsoleSender().sendMessage(name + "PlaceholderAPI support loaded!");
        }

        // Config File Register
        configFile();
        // Command Register
        cmdEvent();
        // Events and Listener Register
        listenerReg();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(HEADER);
        Bukkit.getConsoleSender().sendMessage(name + version + " has been disabled!");
        Bukkit.getConsoleSender().sendMessage(HEADER);
    }

    public void configFile()
    {
        File config = new File(this.getDataFolder(), "config.yml");

        if (!config.exists())
        {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void cmdEvent() {
        Objects.requireNonNull(this.getCommand("rhtest")).setExecutor(new SampleCommand(this));
        Objects.requireNonNull(this.getCommand("rhb")).setExecutor(new RhbCommand(this));
        Objects.requireNonNull(this.getCommand("test")).setExecutor(new Test(this));
        Objects.requireNonNull(this.getCommand("test")).setTabCompleter(new Test(this));
    }

    public void listenerReg() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawns(this), this);
        pm.registerEvents(new Login(this), this);
        pm.registerEvents(new MobKills(this), this);
    }
}
