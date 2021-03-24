package com.nicolorancan.Main.Commands;

import com.nicolorancan.Main.Commands.SubCommands.EventsList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor
{
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager()
    {
        subCommands.add(new EventsList());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 1 && args[0].equals("help"))
        {
            sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "HttpPoster command list:");

            for (SubCommand subCommand : subCommands)
                sender.sendMessage(ChatColor.AQUA + subCommand.getSyntax() + ChatColor.RESET + " - " + subCommand.getDescription());
        }
        else
        {
            if (args.length >= 1)
            {
                for (SubCommand subCommand : subCommands)
                {
                    if (args[0].equalsIgnoreCase(subCommand.getName()))
                    {
                        subCommand.perform((Player) sender, args);
                        return true;
                    }
                }
            }

            sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
        }
        return true;
    }
}
