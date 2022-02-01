package com.rhbarber.hardcore.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor
{
    private Hardcore plugin;

    public Test(Hardcore plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player player)
        {
            String replaced = PlaceholderAPI.setPlaceholders(player, "%player_name%");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + replaced + " has ran this command!");

            if(args[0].equalsIgnoreCase("test"))
            {
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, "%rhbexpansion_test1%"));
            }

            if(args[0].equalsIgnoreCase("test2"))
            {
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, "%rhbexpansion_test2%"));
            }
        }
        return false;
    }
}
