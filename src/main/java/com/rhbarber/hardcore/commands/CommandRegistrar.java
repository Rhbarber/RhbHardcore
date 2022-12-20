package com.rhbarber.hardcore.commands;

import com.rhbarber.hardcore.Hardcore;
import com.rhbarber.hardcore.commands.impl.RhbCommand;
import com.rhbarber.hardcore.commands.impl.SampleCommand;
import com.rhbarber.hardcore.commands.impl.TestCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

public class CommandRegistrar {
    private final Hardcore hardcore;

    public CommandRegistrar(Hardcore hardcore) {
        this.hardcore = hardcore;

        register("rhtest", new SampleCommand(hardcore), false);
        register("rhb", new RhbCommand(hardcore), false);
        register("test", new TestCommand(hardcore), true);
    }

    public void register(String command, CommandExecutor commandExecutor, boolean tabReady) {
        PluginCommand pluginCommand = hardcore.getCommand(command);
        if (pluginCommand != null) {
            hardcore.getLogger().info("Loaded " + commandExecutor.getClass().getSimpleName() + " as " + command);
            pluginCommand.setExecutor(commandExecutor);
            if (tabReady) {
                pluginCommand.setTabCompleter((TabCompleter) commandExecutor);
            }
        } else {
            hardcore.getLogger().warning(
                    "Command \"" + command + "\" isn't on the \"commands\" section on plugin.yml." +
                            "Therefor, " + commandExecutor.getClass().getSimpleName() + " will be not loaded"
            );
        }
    }
}