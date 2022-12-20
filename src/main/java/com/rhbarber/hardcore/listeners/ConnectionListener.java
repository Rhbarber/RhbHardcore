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

public class ConnectionListener implements Listener {
    private Hardcore plugin;

    public ConnectionListener(Hardcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player rPlayer = event.getPlayer();
        event.setJoinMessage(null); // don't send the message to everyone (this is the "XXXX joined the game" message)
        rPlayer.sendMessage(ChatColor.DARK_RED + "Alo");

        FileConfiguration config = plugin.getConfig();
        if (config.contains("Spawn")) {
            double x = config.getDouble("Spawn.x", 0.0);
            double y = config.getDouble("Spawn.y", 100.0);
            double z = config.getDouble("Spawn.z", 0.0);
            float yaw = (float) config.getDouble("Spawn.yaw", 0.0);
            float pitch = (float) config.getDouble("Spawn.pitch", 0.0);
            World world = plugin.getServer().getWorld(config.getString("Spawn.world", "world"));

            Location loc = new Location(world, x, y, z, yaw, pitch); // World, x, y, z, yaw, pitch.
            rPlayer.teleport(loc);
        }

        if (config.getBoolean("welcome-msg.enabled")) {
            List<String> msg = config.getStringList("welcome-msg.text");
            for (String text : msg) {
                rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replace("%player%", rPlayer.getName())));
            }
        }
    }
}