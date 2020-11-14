package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.ComandoA;
import com.rhbarber.hardcore.commands.ComandoB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.rhbarber.hardcore.listeners.Listeners;

import java.io.File;

public class Hardcore extends JavaPlugin
{
    public String configPath;
    PluginDescriptionFile pluginFile = getDescription();
    public String version = pluginFile.getVersion();
    public String name = ChatColor.RED + pluginFile.getName();

    public void onEnable()
    {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + version + " has started!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");

        // Registrar Archivo Configuracion
        configFile();
        // Registrar Comandos
        eventoComandos();
        // Registrar Eventos/Listeners
        registrarListener();
    }

    public void onDisable()
    {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + version + " has been disabled!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------------------");
    }

    public void configFile()
    {
        File config = new File(this.getDataFolder(), "config.yml");
        configPath = config.getPath();

        if (!config.exists())
        {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void eventoComandos()
    {
        this.getCommand("rhtest").setExecutor(new ComandoA(this));
        this.getCommand("a").setExecutor(new ComandoB(this));
    }

    public void registrarListener()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Listeners(this), this);
    }
}
