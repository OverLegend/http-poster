package com.nicolorancan.main.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SubCommand {
    String getName();

    String getDescription();

    String getSyntax();

    void performForPlayer(Player player, String[] args);

    void performForConsole(CommandSender sender, String[] args);
}
