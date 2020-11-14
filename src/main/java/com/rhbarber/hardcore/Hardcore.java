package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.SampleCommand;
import com.rhbarber.hardcore.commands.RhbCommand;
import com.rhbarber.hardcore.listeners.CreatureSpawns;
import com.rhbarber.hardcore.listeners.Login;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Hardcore extends JavaPlugin
{
    public String configPath;
    PluginDescriptionFile pluginFile = getDescription();
    public String version = pluginFile.getVersion();
    public String name = ChatColor.RED + "[" + pluginFile.getName() + "] ";

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
        this.getCommand("rhtest").setExecutor(new SampleCommand(this));
        this.getCommand("rhb").setExecutor(new RhbCommand(this));
    }

    public void registrarListener()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawns(this), this);
        pm.registerEvents(new Login(this), this);
    }
}
