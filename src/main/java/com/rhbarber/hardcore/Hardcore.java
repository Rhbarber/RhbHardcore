package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.ComandoA;
import com.rhbarber.hardcore.commands.ComandoB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.rhbarber.hardcore.listeners.Listeners;

public class Hardcore extends JavaPlugin
{
    private static Hardcore instance;
    PluginDescriptionFile pluginFile = getDescription();
    public String version = pluginFile.getVersion();
    public String name = ChatColor.RED + pluginFile.getName();

    public static Hardcore getInstance()
    {
        return instance;
    }

    public void onEnable()
    {
        instance = this;
        getServer().getPluginManager().registerEvents((Listener)new Listeners(), (Plugin)this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + version + " has started!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");

        // Registrar Comandos
        eventoComandos();
    }

    public void onDisable()
    {
        instance = null;
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + version + " has been disabled!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
    }

    public void eventoComandos()
    {
        this.getCommand("rhtest").setExecutor(new ComandoA(this));
        this.getCommand("a").setExecutor(new ComandoB(this));
    }
}
