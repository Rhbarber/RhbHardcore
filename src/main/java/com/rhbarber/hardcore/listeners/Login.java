package com.rhbarber.hardcore.listeners;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Objects;

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
        event.setJoinMessage("Welcome to hell!!");

        FileConfiguration config = plugin.getConfig();
        if (config.contains("Spawn"))
        {
            double x = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.x")));
            double y = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.y")));
            double z = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.z")));
            float yaw = Float.parseFloat(Objects.requireNonNull(config.getString("Spawn.yaw")));
            float pitch = Float.parseFloat(Objects.requireNonNull(config.getString("Spawn.pitch")));
            World world = plugin.getServer().getWorld(Objects.requireNonNull(config.getString("Spawn.world")));

            Location loc = new Location(world, x, y, z, yaw, pitch); // World, x, y, z, yaw, pitch.
            rPlayer.teleport(loc);
        }

        String path = "Config.welcome-msg";

        if(Objects.equals(config.getString(path), "true"))
        {
            List<String> msg = config.getStringList("Config.welcome-msg-text");
            for (String text : msg) {
                rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replace("%player%", rPlayer.getName())));
            }
        }
    }
}
