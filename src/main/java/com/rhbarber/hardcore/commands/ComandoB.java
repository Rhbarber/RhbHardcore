package com.rhbarber.hardcore.commands;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoB implements CommandExecutor {
    private Hardcore plugin;

    public ComandoB(Hardcore plugin) {
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
                // Comando para TP
                else if(args[0].equalsIgnoreCase("zero"))
                {
                    Location loc = new Location(rPlayer.getWorld(),0, 90, 0, -90, 0); // World, x, y, z, yaw, pitch.
                    rPlayer.teleport(loc);
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

