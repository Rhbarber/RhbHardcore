package com.rhbarber.hardcore;

import com.rhbarber.hardcore.commands.SampleCommand;
import com.rhbarber.hardcore.commands.RhbCommand;
import com.rhbarber.hardcore.listeners.CreatureSpawns;
import com.rhbarber.hardcore.listeners.Login;
import com.rhbarber.hardcore.listeners.MobKills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Hardcore extends JavaPlugin
{
    public String header = ChatColor.YELLOW + "--------------------------------------------";
    public String configPath;
    PluginDescriptionFile pluginFile = getDescription();
    public String version = pluginFile.getVersion();
    public String name = ChatColor.GRAY + "[" + ChatColor.RED + pluginFile.getName() + ChatColor.GRAY + "] ";

    @Override
    public void onEnable()
    {
        Bukkit.getConsoleSender().sendMessage(header);
        Bukkit.getConsoleSender().sendMessage(name + version + " has started!");
        Bukkit.getConsoleSender().sendMessage(header);

        // Registrar Archivo Configuracion
        configFile();
        // Registrar Comandos
        eventoComandos();
        // Registrar Eventos/Listeners
        registrarListener();
    }

    @Override
    public void onDisable()
    {
        Bukkit.getConsoleSender().sendMessage(header);
        Bukkit.getConsoleSender().sendMessage(name + version + " has been disabled!");
        Bukkit.getConsoleSender().sendMessage(header);
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
        Objects.requireNonNull(this.getCommand("rhtest")).setExecutor(new SampleCommand(this));
        Objects.requireNonNull(this.getCommand("rhb")).setExecutor(new RhbCommand(this));
    }

    public void registrarListener()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CreatureSpawns(this), this);
        pm.registerEvents(new Login(this), this);
        pm.registerEvents(new MobKills(this), this);
    }
}
