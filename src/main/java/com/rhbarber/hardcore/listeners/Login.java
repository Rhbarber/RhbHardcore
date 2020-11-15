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
        }

        String path = "Config.welcome-msg";

        if(config.getString(path).equals("true"))
        {
            List<String> msg = config.getStringList("Config.welcome-msg-text");
            for(int i = 0; i < msg.size(); i++)
            {
                String text = msg.get(i);
                rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%player%", rPlayer.getName())));
            }
        }
    }
}
