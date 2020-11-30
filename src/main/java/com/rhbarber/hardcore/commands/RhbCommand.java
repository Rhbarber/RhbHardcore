package com.rhbarber.hardcore.commands;

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

import java.util.ArrayList;
import java.util.List;

public class RhbCommand implements CommandExecutor {
    private Hardcore plugin;

    public RhbCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        else {
            Player rPlayer = (Player) sender;
            if (args.length > 0) {
                // Comando "a"
                if (args[0].equalsIgnoreCase("a")) {
                    rPlayer.sendMessage(plugin.name + ChatColor.LIGHT_PURPLE + "AAAAAAAAAAAAA" + plugin.version);
                    return true;
                }
                // Set Coords para TP
                else if(args[0].equalsIgnoreCase("setspawn"))
                {
                    Location loc = rPlayer.getLocation();
                    // Variables
                    FileConfiguration config = plugin.getConfig();
                    config.set("Spawn.x", loc.getX());
                    config.set("Spawn.y", loc.getY());
                    config.set("Spawn.z", loc.getZ());
                    config.set("Spawn.world", loc.getWorld().getName());
                    config.set("Spawn.yaw", loc.getYaw());
                    config.set("Spawn.pitch", loc.getPitch());
                    plugin.saveConfig();
                    rPlayer.sendMessage(plugin.name + ChatColor.RED + " Spawn configurado correctamente.");
                    return true;
                }
                // Comando para TP
                else if(args[0].equalsIgnoreCase("spawn"))
                {
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
                        return true;
                    }
                    else{
                        rPlayer.sendMessage(plugin.name + ChatColor.RED + " El Spawn no ha sido configurado.");
                    }
                    return true;
                }
                // Comando Kills by NotEugenio_
                else if (args[0].equalsIgnoreCase("kills")) {
                    // Ejecutamos nuestra acción. Definimos la config
                    FileConfiguration config = plugin.getConfig();
                    // Enviamos el mensaje siempre al principio para no hacerlo en cada condición
                    rPlayer.sendMessage(ChatColor.BLUE + "--------------Kills--------------");
                    // Si la config no contiene "Players"...
                    if (!config.contains("Players." + rPlayer.getUniqueId())) {
                        rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                        rPlayer.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                        rPlayer.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        return false;
                    }
                    // Si la config contiene el jugador con su uid y zombiekills...
                    if (config.contains("Players." + rPlayer.getUniqueId() + ".ZombieKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + rPlayer.getUniqueId() + ".ZombieKills") != null) {
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + config.getInt("Players." + rPlayer.getUniqueId() + ".ZombieKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }

                    // Si la config contiene el jugador con su uid y skeletonkills...
                    if (config.contains("Players." + rPlayer.getUniqueId() + ".SkeletonKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + rPlayer.getUniqueId() + ".SkeletonKills") != null) {
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + config.getInt("Players." + rPlayer.getUniqueId() + ".SkeletonKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }


                    // Si la config contiene el jugador con su uid y creeperkills...
                    if (config.contains("Players." + rPlayer.getUniqueId() + ".CreeperKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + rPlayer.getUniqueId() + ".CreeperKills") != null) {
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + config.getInt("Players." + rPlayer.getUniqueId() + ".CreeperKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                            rPlayer.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }
                }
                // Comando Reload
                else if(args[0].equalsIgnoreCase("reload"))
                {
                    plugin.reloadConfig();
                    rPlayer.sendMessage(plugin.name + ChatColor.DARK_RED + " El plugin ha sido recargado.");
                    return true;
                }
                // Comando Report
                else if(args[0].equalsIgnoreCase("report"))
                {
                    if(args.length == 1)
                    {
                        rPlayer.sendMessage(plugin.name + ChatColor.LIGHT_PURPLE + " Para poder hacer un reporte utiliza: " + ChatColor.GRAY + "/rhb report <usuario>");
                        return true;
                    }else{
                        String user = args[1];
                        if(Bukkit.getPlayer(user) != null)
                        {
                            FileConfiguration config = plugin.getConfig();
                            if(config.contains("reported-players"))
                            {
                                List<String> reported = config.getStringList("reported-players");

                                if(reported.contains(user))
                                {
                                    rPlayer.sendMessage(plugin.name + ChatColor.DARK_RED + " El jugador ya esta reportado.");
                                    return true;
                                }else{
                                    reported.add(user);
                                    config.set("reported-players", reported);
                                    plugin.saveConfig();
                                    rPlayer.sendMessage(plugin.name + ChatColor.AQUA + " Usuario reportado correctamente.");
                                    return true;
                                }
                            }else{
                                List<String> reported = new ArrayList<String>();
                                reported.add(user);
                                config.set("reported-players", reported);
                                plugin.saveConfig();
                                rPlayer.sendMessage(plugin.name + ChatColor.AQUA + " Usuario reportado correctamente.");
                                return true;
                            }
                        }else{
                            rPlayer.sendMessage(plugin.name + ChatColor.YELLOW + " El jugador no esta Online");
                            return true;
                        }
                    }
                }
                // Comando Inexistente
                else {
                    rPlayer.sendMessage(plugin.name + ChatColor.RED + " Ese comando no existe.");
                    return true;
                }
            } else {
                rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bRhbHardcore plugin made by Rhbarber"));
                rPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cContributors: NotEugenio_"));
                return true;
            }
        }
        return true;
    }
}

