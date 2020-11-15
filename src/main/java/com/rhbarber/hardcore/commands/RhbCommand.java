package com.rhbarber.hardcore.commands;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class RhbCommand implements CommandExecutor {
    private Hardcore plugin;

    public RhbCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            Bukkit.getConsoleSender().sendMessage("[" + plugin.name + "]" + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        else {
            Player rPlayer = (Player) sender;
            if (args.length > 0) {
                // Comando "a"
                if (args[0].equalsIgnoreCase("a")) {
                    rPlayer.sendMessage(plugin.name + ChatColor.LIGHT_PURPLE + "AAAAAAAAAAAAA" + plugin.version);
                    return true;
                }
                // Comando "kills"
                if (args[0].equalsIgnoreCase("kills")) {
                    FileConfiguration config = plugin.getConfig();
                    if(!config.contains("Players"))
                    {
                        rPlayer.sendMessage(ChatColor.BLUE + "--------------Kills--------------");
                        rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                        return true;
                    }else{
                        if(config.contains("Players." + rPlayer.getUniqueId() + ".ZombieKills"))
                        {
                            int counterZombies = Integer.valueOf(config.getString("Players." + rPlayer.getUniqueId() + ".ZombieKills"));
                            rPlayer.sendMessage(ChatColor.BLUE + "--------------Kills--------------");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + counterZombies);
                            return true;
                        }else{
                            rPlayer.sendMessage(ChatColor.BLUE + "--------------Kills--------------");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                            return true;
                        }
                    }
                }
                // Set Coords para TP
                else if(args[0].equalsIgnoreCase("setspawn"))
                {
                    Location loc = rPlayer.getLocation();
                    // Variables
                    FileConfiguration config = plugin.getConfig();
                    config.set("Spawn.x", loc.getX());
                    config.set("Spawn.y", loc.getY());
                    config.set("Spawn.z", loc.getZ());
                    config.set("Spawn.world", loc.getWorld().getName());
                    config.set("Spawn.yaw", loc.getYaw());
                    config.set("Spawn.pitch", loc.getPitch());
                    plugin.saveConfig();
                    rPlayer.sendMessage(plugin.name + ChatColor.RED + " Spawn configurado correctamente.");
                    return true;
                }
                // Comando para TP
                else if(args[0].equalsIgnoreCase("spawn"))
                {
                    FileConfiguration config = plugin.getConfig();
                    if (config.contains("Spawn"))
                    {
                        double x = Double.valueOf(config.getString("Spawn.x"));
                        double y = Double.valueOf(config.getString("Spawn.y"));
                        double z = Double.valueOf(config.getString("Spawn.z"));
                        float yaw = Float.valueOf(config.getString("Spawn.yaw"));
                        float pitch = Float.valueOf(config.getString("Spawn.pitch"));
                        World world = plugin.getServer().getWorld(config.getString("Spawn.world"));

                        Location loc = new Location(world, x, y, z, yaw, pitch); // World, x, y, z, yaw, pitch.
                        rPlayer.teleport(loc);
                        return true;
                    }
                    else{
                        rPlayer.sendMessage(plugin.name + ChatColor.RED + " El Spawn no ha sido configurado.");
                    }
                    return true;
                }
                // Comando Reload
                else if(args[0].equalsIgnoreCase("reload"))
                {
                    plugin.reloadConfig();
                    rPlayer.sendMessage(plugin.name + ChatColor.RED + " recargado.");
                    return true;
                }
                // Comando Inexistente
                else {
                    rPlayer.sendMessage(plugin.name + ChatColor.RED + "Ese comando no existe.");
                    return true;
                }
            } else {
                rPlayer.sendMessage(plugin.name + ChatColor.AQUA + "Usa /a a para AAAAAAA.");
                return true;
            }
        }
    }
}

