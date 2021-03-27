package com.nicolorancan.Main.Commands;

import com.nicolorancan.Main.Commands.SubCommands.EventsList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new EventsList());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2 && args[1] == "help") {
            sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "HttpPoster command list:");
            for (int i = 0; i < subCommands.size(); i++) {
                sender.sendMessage(ChatColor.AQUA + subCommands.get(i).getSyntax() + ChatColor.RESET + " - " + subCommands.get(i).getDescription());
            }
        } else {
            if (args.length > 1) {
                for (int i = 0; i < subCommands.size(); i++) {
                    if (args[1].equalsIgnoreCase(subCommands.get(i).getName())) {
                        subCommands.get(i).perform((Player) sender, args);
                    }
                }
            } else {
                sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
            }
        }
        return true;
    }
}