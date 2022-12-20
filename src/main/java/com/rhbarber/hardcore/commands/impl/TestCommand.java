package com.rhbarber.hardcore.commands.impl;

import com.rhbarber.hardcore.Hardcore;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCommand implements CommandExecutor, TabCompleter {
    private final Hardcore plugin;

    public TestCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        String replaced = PlaceholderAPI.setPlaceholders(player, "%player_name%");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + replaced + " has ran this command!");

        if (args[0].equalsIgnoreCase("test")) {
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, "%rhbexpansion_test1%"));
        }

        if (args[0].equalsIgnoreCase("test2")) {
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, "%rhbexpansion_test2%"));
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("test")) {
            return null;
        }
        switch (args.length) {
            case 1 -> {
                return Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            }
            case 2 -> {
                List<String> arguments = new ArrayList<>();
                arguments.add("A1");
                arguments.add("B2");
                return arguments;
            }
            default -> {
                return null;
            }
        }
    }
}
