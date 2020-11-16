package com.rhbarber.hardcore.commands;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SampleCommand implements CommandExecutor
{
    private Hardcore plugin;

    public SampleCommand(Hardcore plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        else
        {
            Player rPlayer = (Player) sender;
            rPlayer.sendMessage(ChatColor.DARK_RED + "Sample Text XD");
            rPlayer.sendMessage(ChatColor.YELLOW + "a");
            return true;
        }
    }
}
