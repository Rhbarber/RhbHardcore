package com.rhbarber.hardcore.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RhbExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "RhbHardcore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Rhbarber";
    }

    @Override
    public @NotNull String getVersion() {
        return "${project.version}";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if(params.equalsIgnoreCase("name")) {
            return player == null ? null : player.getName(); // "name" requires the player to be valid
        }

        if(params.equalsIgnoreCase("test1")) {
            return "Placeholder Text 1";
        }

        if(params.equalsIgnoreCase("test2")) {
            return "Placeholder Text 2";
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
