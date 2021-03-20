package com.nicolorancan.Main.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HttpPost implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("Coded by Rancan Nicolò");
        commandSender.sendMessage("Repository: ");
        return false;
    }
}
