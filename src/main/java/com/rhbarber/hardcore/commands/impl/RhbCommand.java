package com.rhbarber.hardcore.commands.impl;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RhbCommand implements CommandExecutor {
    private final Hardcore plugin;

    public RhbCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player rPlayer)) {
            Bukkit.getConsoleSender().sendMessage(plugin.getName() + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        if (args.length == 0) {
            rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bRhbHardcore plugin made by Rhbarber"));
            return true;
        }
        // Comando "a"
        if (args[0].equalsIgnoreCase("a")) {
            rPlayer.sendMessage(plugin.getName() + ChatColor.LIGHT_PURPLE + "AAAAAAAAAAAAA" + plugin.getDescription().getVersion());
            return true;
        }
        // Set Coords TP
        if (args[0].equalsIgnoreCase("setspawn")) {
            Location loc = rPlayer.getLocation();
            // Variables
            FileConfiguration config = plugin.getConfig();
            config.set("Spawn.x", loc.getX());
            config.set("Spawn.y", loc.getY());
            config.set("Spawn.z", loc.getZ());
            config.set("Spawn.world", Objects.requireNonNull(loc.getWorld()).getName());
            config.set("Spawn.yaw", loc.getYaw());
            config.set("Spawn.pitch", loc.getPitch());
            plugin.saveConfig();
            rPlayer.sendMessage(plugin.getName() + ChatColor.RED + " Spawn configurado correctamente.");
            return true;
        }
        // Comando para TP
        if (args[0].equalsIgnoreCase("spawn")) {
            FileConfiguration config = plugin.getConfig();
            if (config.contains("Spawn")) {
                double x = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.x")));
                double y = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.y")));
                double z = Double.parseDouble(Objects.requireNonNull(config.getString("Spawn.z")));
                float yaw = Float.parseFloat(Objects.requireNonNull(config.getString("Spawn.yaw")));
                float pitch = Float.parseFloat(Objects.requireNonNull(config.getString("Spawn.pitch")));
                World world = plugin.getServer().getWorld(Objects.requireNonNull(config.getString("Spawn.world")));

                Location loc = new Location(world, x, y, z, yaw, pitch); // World, x, y, z, yaw, pitch.
                rPlayer.teleport(loc);
            } else {
                rPlayer.sendMessage(plugin.getName() + ChatColor.RED + " El Spawn no ha sido configurado.");
            }
            return true;
        }
        // Comando Kills by NotEugenio_
        if (args[0].equalsIgnoreCase("kills")) {
            // Ejecutamos nuestra acción. Definimos la config
            FileConfiguration config = plugin.getConfig();
            // Enviamos el mensaje siempre al principio para no hacerlo en cada condición
            rPlayer.sendMessage(ChatColor.BLUE + "--------------Kills--------------");

            // Variables de String porque SonarLint jode por cualquier cosa
            String kZombies = "Zombies Asesinados: ";
            String kSkeleton = "Esqueletos Asesinados: ";
            String kCreeper = "Creepers Asesinados: ";
            String none = "Ninguno";
            String cPlayers = "Players.";
            String cZombie = ".ZombieKills";
            String cSkeleton = ".SkeletonKills";
            String cCreeper = ".CreeperKills";

            // Si la config no contiene "Players"...
            if (!config.contains(cPlayers + rPlayer.getUniqueId())) {
                rPlayer.sendMessage(ChatColor.DARK_AQUA + kZombies + ChatColor.GRAY + none);
                rPlayer.sendMessage(ChatColor.DARK_AQUA + kSkeleton + ChatColor.GRAY + none);
                rPlayer.sendMessage(ChatColor.DARK_AQUA + kCreeper + ChatColor.GRAY + none);
                return false;
            }
            // Si la config contiene el jugador con su uid y zombiekills...
            if (config.contains(cPlayers + rPlayer.getUniqueId() + cZombie)) {
                // Si no es nula la string se ejecutará la función.
                if (config.getString(cPlayers + rPlayer.getUniqueId() + cZombie) != null) {
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kZombies + config.getInt(cPlayers + rPlayer.getUniqueId() + cZombie));
                } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kZombies + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kSkeleton + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kCreeper + ChatColor.GRAY + none);
                }
            }

            // Si la config contiene el jugador con su uid y skeletonkills.
            if (config.contains(cPlayers + rPlayer.getUniqueId() + cSkeleton)) {
                // Si no es nula la string se ejecutará la función.
                if (config.getString(cPlayers + rPlayer.getUniqueId() + cSkeleton) != null) {
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kSkeleton + config.getInt(cPlayers + rPlayer.getUniqueId() + cSkeleton));
                } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kZombies + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kSkeleton + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kCreeper + ChatColor.GRAY + none);
                }
            }


            // Si la config contiene el jugador con su uid y creeperkills...
            if (config.contains(cPlayers + rPlayer.getUniqueId() + cCreeper)) {
                // Si no es nula la string se ejecutará la función.
                if (config.getString(cPlayers + rPlayer.getUniqueId() + cCreeper) != null) {
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kCreeper + config.getInt(cPlayers + rPlayer.getUniqueId() + cCreeper));
                } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kZombies + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kSkeleton + ChatColor.GRAY + none);
                    rPlayer.sendMessage(ChatColor.DARK_AQUA + kCreeper + ChatColor.GRAY + none);
                }
            }
        }
        // Comando Reload
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            rPlayer.sendMessage(plugin.getName() + ChatColor.DARK_RED + " El plugin ha sido recargado.");
            return true;
        }
        // Comando Report
        if (args[0].equalsIgnoreCase("report")) {
            if (args.length == 1) {
                rPlayer.sendMessage(plugin.getName() + ChatColor.LIGHT_PURPLE + " Para poder hacer un reporte utiliza: " + ChatColor.GRAY + "/rhb report <usuario>");
                return true;
            }
            String user = args[1];
            if (Bukkit.getPlayer(user) != null) {
                String reports = "reported-players";
                FileConfiguration config = plugin.getConfig();
                if (config.contains(reports)) {
                    List<String> reported = config.getStringList(reports);

                    if (reported.contains(user)) {
                        rPlayer.sendMessage(plugin.getName() + ChatColor.DARK_RED + " El jugador ya esta reportado.");
                    } else {
                        reported.add(user);
                        config.set(reports, reported);
                        plugin.saveConfig();
                        rPlayer.sendMessage(plugin.getName() + ChatColor.AQUA + " Usuario reportado correctamente.");
                    }
                    return true;
                } else {
                    List<String> reported = new ArrayList<>();
                    reported.add(user);
                    config.set(reports, reported);
                    plugin.saveConfig();
                    rPlayer.sendMessage(plugin.getName() + ChatColor.AQUA + " Usuario reportado correctamente.");
                    return true;
                }
            } else {
                rPlayer.sendMessage(plugin.getName() + ChatColor.YELLOW + " El jugador no esta Online");
                return true;
            }
        }
        // Comando Inexistente
        rPlayer.sendMessage(plugin.getName() + ChatColor.RED + " Ese comando no existe.");
        return true;
    }
}