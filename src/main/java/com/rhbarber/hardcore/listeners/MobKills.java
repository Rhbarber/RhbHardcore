package com.rhbarber.hardcore.listeners;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKills implements Listener
{
    private Hardcore plugin;

    public MobKills(Hardcore plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void ZombieKills(EntityDeathEvent event)
    {
        Player killer = event.getEntity().getKiller();
        EntityType entity = event.getEntityType();

        // Zombie Kill Counter
        if(killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.ZOMBIE))
        {
            FileConfiguration config = plugin.getConfig();
            config.set("Players." + killer.getUniqueId() + "name", killer.getName());

            if(config.contains("Players." + killer.getUniqueId() + "ZombieKills"))
            {
                int zombieCounter = Integer.valueOf(config.getString("Players." + killer.getUniqueId() + "ZombieKills"));
                config.set("Players." + killer.getUniqueId() + "ZombieKills", zombieCounter + 1);
                plugin.saveConfig();
                return;
            }
            else
            {
                config.set("Players." + killer.getUniqueId() + "ZombieKills", 1);
                plugin.saveConfig();
                return;
            }
        }

        // Skeleton Kill Counter
        if(killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.SKELETON))
        {
            FileConfiguration config = plugin.getConfig();
            config.set("Players." + killer.getUniqueId() + "name", killer.getName());

            if(config.contains("Players." + killer.getUniqueId() + "SkeletonKills"))
            {
                int zombieCounter = Integer.valueOf(config.getString("Players." + killer.getUniqueId() + "SkeletonKills"));
                config.set("Players." + killer.getUniqueId() + "SkeletonKills", zombieCounter + 1);
                plugin.saveConfig();
                return;
            }
            else
            {
                config.set("Players." + killer.getUniqueId() + "SkeletonKills", 1);
                plugin.saveConfig();
                return;
            }
        }
    }
}
