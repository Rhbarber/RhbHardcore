package com.rhbarber.hardcore.listeners;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class DeathListener implements Listener {
    private final Hardcore plugin;

    public DeathListener(Hardcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        EntityType entity = event.getEntityType();

        // Zombie Kill Counter
        if (killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.ZOMBIE)) {
            FileConfiguration config = plugin.getConfig();
            config.set("Players." + killer.getUniqueId() + ".nick", killer.getName());

            if (config.contains("Players." + killer.getUniqueId() + ".ZombieKills")) {
                int zombieCounter = Integer.parseInt(Objects.requireNonNull(config.getString("Players." + killer.getUniqueId() + ".ZombieKills")));
                config.set("Players." + killer.getUniqueId() + ".ZombieKills", zombieCounter + 1);
                plugin.saveConfig();
                return;
            } else {
                config.set("Players." + killer.getUniqueId() + ".ZombieKills", 1);
                plugin.saveConfig();
                return;
            }
        }

        // Skeleton Kill Counter
        if (killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.SKELETON)) {
            FileConfiguration config = plugin.getConfig();
            config.set("Players." + killer.getUniqueId() + ".nick", killer.getName());

            if (config.contains("Players." + killer.getUniqueId() + ".SkeletonKills")) {
                int zombieCounter = Integer.parseInt(Objects.requireNonNull(config.getString("Players." + killer.getUniqueId() + ".SkeletonKills")));
                config.set("Players." + killer.getUniqueId() + ".SkeletonKills", zombieCounter + 1);
                plugin.saveConfig();
                return;
            } else {
                config.set("Players." + killer.getUniqueId() + ".SkeletonKills", 1);
                plugin.saveConfig();
                return;
            }
        }

        // Creeper Kill Counter
        if (killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.CREEPER)) {
            FileConfiguration config = plugin.getConfig();
            config.set("Players." + killer.getUniqueId() + ".nick", killer.getName());

            if (config.contains("Players." + killer.getUniqueId() + ".CreeperKills")) {
                int zombieCounter = Integer.parseInt(Objects.requireNonNull(config.getString("Players." + killer.getUniqueId() + ".CreeperKills")));
                config.set("Players." + killer.getUniqueId() + ".CreeperKills", zombieCounter + 1);
                plugin.saveConfig();
            } else {
                config.set("Players." + killer.getUniqueId() + ".CreeperKills", 1);
                plugin.saveConfig();
            }
        }
    }
}
