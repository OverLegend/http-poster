package com.nicolorancan.main.commands;

import com.nicolorancan.main.Main;
import com.nicolorancan.main.commands.subcommands.EventsList;
import com.nicolorancan.main.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final Main plugin;

    public CommandManager(ConfigManager configManager, Main plugin) {
        subCommands.add(new EventsList(configManager));
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0) {
            sender.sendMessage("You need to specify a sub-command.");
            sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
            return true;
        }

        for (SubCommand value : subCommands) {
            if (args[0].equalsIgnoreCase(value.getName())) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    value.performForPlayer(player, args);
                    return true;
                }
                value.performForConsole(sender, args);
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfiguration();
            sender.sendMessage("Plugin reloaded.");
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {

            sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "HttpPoster command list:" + ChatColor.RESET + "");
            subCommands.forEach(subCommand ->
                    sender.sendMessage(ChatColor.AQUA + "" + subCommand.getSyntax() + ChatColor.RESET + " - " + subCommand.getDescription()));

            return true;
        }

        sender.sendMessage("Command not found.");
        sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
        return true;

    }
}