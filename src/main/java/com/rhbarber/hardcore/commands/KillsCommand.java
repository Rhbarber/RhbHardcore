package com.rhbarber.hardcore.commands;

import com.rhbarber.hardcore.Hardcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class KillsCommand implements CommandExecutor {
    // Definir plugin
    private Hardcore plugin;

    public KillsCommand(Hardcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // Si es un jugador, ejecutamos la acción.
        if (sender instanceof Player) {
            // Definimos que es un jugador. Como el sender ya se hace pass con la función de onCommand, solo lo castearemos a player con los (), porque ya hemos hecho justo arriba un instanceof para asegurarnos que tiene que ser un jugador quien lo ejecuta.
            Player player = (Player) sender;
            // Tenemos que asegurarnos que el comando no está siendo ejecutado con menos argumentos.
            if (args.length > 0) {
                // Comando "kills". Tenemos que comprobar que el argumento 0 es kills, e ignoramos si es mayúscula.
                if (args[0].equalsIgnoreCase("kills")) {
                    // Ejecutamos nuestra acción. Definimos la config
                    FileConfiguration config = plugin.getConfig();
                    // Enviamos el mensaje siempre al principio para no hacerlo en cada condición
                    player.sendMessage(ChatColor.BLUE + "--------------Kills--------------");
                    // Si la config no contiene "Players"...
                    if (!config.contains("Players." + player.getUniqueId())) {
                        player.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                        player.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                        player.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        return false;
                    }
                    // Si la config contiene el jugador con su uid y zombiekills...
                    if (config.contains("Players." + player.getUniqueId() + ".ZombieKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + player.getUniqueId() + ".ZombieKills") != null) {
                            player.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + config.getInt("Players." + player.getUniqueId() + ".ZombieKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            player.sendMessage(ChatColor.DARK_AQUA + "Zombies Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }

                    // Si la config contiene el jugador con su uid y skeletonkills...
                    if (config.contains("Players." + player.getUniqueId() + ".SkeletonKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + player.getUniqueId() + ".SkeletonKills") != null) {
                            player.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + config.getInt("Players." + player.getUniqueId() + ".SkeletonKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            player.sendMessage(ChatColor.DARK_AQUA + "Esqueletos Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }

                    // Si la config contiene el jugador con su uid y creeperkills...
                    if (config.contains("Players." + player.getUniqueId() + ".CreeperKills")) {
                        // Si no es nula la string se ejecutará la función.
                        if (config.getString("Players." + player.getUniqueId() + ".CreeperKills") != null) {
                            player.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + config.getInt("Players." + player.getUniqueId() + ".CreeperKills"));
                        } else { // Si no, si la string es nula, es decir, no tiene número o no existe se hará lo siguiente.
                            player.sendMessage(ChatColor.DARK_AQUA + "Creepers Asesinados: " + ChatColor.GRAY + "Ninguno");
                        }
                    }

                }
            } else { // Si no tiene los argumentos que queremos
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bRHBHardcore plugin made by Esteban M."));
                return false;
            }
        } else { // Si no es un jugador, ejecutamos esta acción.
            Bukkit.getConsoleSender().sendMessage("[" + plugin.name + "]" + ChatColor.RED + " Este comando no se puede ejecutar desde la consola.");
            return false;
        }
        return false;
    }
}

