package com.rhbarber.hardcore.commands.impl;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SampleCommand implements CommandExecutor {
    private final Hardcore plugin;

    public SampleCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player rPlayer)) {
            Bukkit.getConsoleSender().sendMessage(plugin.getName() + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        rPlayer.sendMessage(ChatColor.DARK_RED + "Sample Text XD");
        rPlayer.sendMessage(ChatColor.YELLOW + "a");
        return true;
    }
}
