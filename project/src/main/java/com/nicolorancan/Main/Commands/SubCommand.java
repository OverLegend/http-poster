package com.nicolorancan.Main.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void performForPlayer(Player player, String args[]);

    public abstract void performForConsole(CommandSender sender, String args[]);
}
