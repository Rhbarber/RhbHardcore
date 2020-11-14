package com.rhbarber.hardcore.listeners;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Login implements Listener {
    private Hardcore plugin;

    public Login(Hardcore plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void Login(PlayerJoinEvent event)
    {
        Player rPlayer = event.getPlayer();
        rPlayer.sendMessage(ChatColor.DARK_RED + "Alo");

        FileConfiguration config = plugin.getConfig();
        String path = "Config.welcome-msg";

        if(config.getString(path).equals("true"))
        {
            String text = "Config.welcome-msg-text";
            rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(text)).replaceAll("%player%", rPlayer.getName()));
        }
    }
}
